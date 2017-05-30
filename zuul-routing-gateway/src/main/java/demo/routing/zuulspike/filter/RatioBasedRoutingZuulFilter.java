package demo.routing.zuulspike.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import demo.routing.zuulspike.RatioRoutingProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import java.util.Random;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.SERVICE_ID_KEY;

@Service
public class RatioBasedRoutingZuulFilter extends ZuulFilter {

    public static final String LEGACY_APP = "legacy";
    public static final String MODERN_APP = "modern";
    
    private Random random = new Random();
    
    @Autowired
    private RatioRoutingProperties ratioRoutingProperties;

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return FilterConstants.PRE_DECORATION_FILTER_ORDER + 1;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext ctx = RequestContext.getCurrentContext();
        return ctx.containsKey(SERVICE_ID_KEY)
                && ctx.get(SERVICE_ID_KEY).equals("ratio-route");
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();

        if (!isZuulCookiePresent(ctx)) {
            if (isTargetedToLegacy()) {
                ctx.put(SERVICE_ID_KEY, LEGACY_APP);
            } else {
                ctx.put(SERVICE_ID_KEY, MODERN_APP);
            }
        } else {
            String sessionServiceId = getZuulCookie(ctx).getValue();
            ctx.put(SERVICE_ID_KEY, sessionServiceId);
        }
        return null;
    }

    boolean isTargetedToLegacy() {
        return random.nextInt(100) < ratioRoutingProperties.getOldPercent();
    }


    private boolean isZuulCookiePresent(RequestContext ctx) {
        return getZuulCookie(ctx) != null;

    }

    private Cookie getZuulCookie(RequestContext ctx) {
        if (ctx.getRequest().getCookies() != null) {
            for (Cookie cookie : ctx.getRequest().getCookies()) {
                if (cookie.getName().equals(CustomCookieEnhancingZuulFilter.ZUUL_SERVICE_ID)) {
                    return cookie;
                }
            }
        }
        return null;
    }
}
