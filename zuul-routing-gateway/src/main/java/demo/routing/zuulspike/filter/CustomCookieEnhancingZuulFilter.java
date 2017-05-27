package demo.routing.zuulspike.filter;

import com.netflix.util.Pair;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import java.util.List;

@Service
public class CustomCookieEnhancingZuulFilter extends ZuulFilter {

    public static final String ZUUL_SERVICE_ID = "ZUUL_SERVICE_ID";
    private static Logger LOGGER = LoggerFactory.getLogger(CustomCookieEnhancingZuulFilter.class);
    @Override
    public String filterType() {
        return "post";
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
        if (containsJSessionIdSettingCookie(ctx)) {
            ctx.getResponse().addCookie(new Cookie(ZUUL_SERVICE_ID, (String)ctx.get("serviceId")));
        }
        LOGGER.debug("filter called for {}", ctx.get("serviceId"));
        return null;
    }

    private boolean containsJSessionIdSettingCookie(RequestContext ctx) {
        List<Pair<String, String>> originResponseHeaders = ctx.getOriginResponseHeaders();
        if (originResponseHeaders != null) {
            for (Pair<String, String> responseHeader: originResponseHeaders) {
                if (responseHeader.first().equals("Set-Cookie") && responseHeader.second().contains("JSESSIONID")) {
                    return true;
                }
            }
        }
        return false;
    }


}
