package demo.routing.zuulspike.filter;

import demo.routing.zuulspike.RatioRoutingProperties;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import java.util.Random;

@Service
@Order(1)
public class RatioBasedRoutingZuulFilter extends ZuulFilter {

    public static final String LEGACY_APP_NAME = "legacy";
    public static final String MODERN_APP_NAME = "modern";
    private Random random = new Random();
    @Autowired
    private RatioRoutingProperties ratioRoutingProperties;

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();

        if (!isZuulCookiePresent(ctx)) {
            if (random.nextInt(100) < ratioRoutingProperties.getOldPercent()) {
                ctx.put("serviceId", LEGACY_APP_NAME);
            } else {
                ctx.put("serviceId", MODERN_APP_NAME);
            }
        } else {
            String sessionServiceId = getZuulCookie(ctx).getValue();
            ctx.put("serviceId", sessionServiceId);
        }
        return null;
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
