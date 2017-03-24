package demo.routing;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by sculli on 3/23/17.
 */
@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SessionDemo {
    private AtomicInteger counter = new AtomicInteger(0);

    public int getCounter() {
        return counter.incrementAndGet();
    }
}
