package demo.routing

import org.springframework.context.annotation.Scope
import org.springframework.context.annotation.ScopedProxyMode
import org.springframework.stereotype.Component
import org.springframework.web.context.WebApplicationContext

import java.util.concurrent.atomic.AtomicInteger

@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
class SessionDemo {
    private val counter = AtomicInteger(0)

    fun getCounter(): Int {
        return counter.incrementAndGet()
    }
}
