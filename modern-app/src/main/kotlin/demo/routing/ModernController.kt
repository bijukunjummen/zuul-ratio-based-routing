package demo.routing

import io.prometheus.client.Counter
import org.springframework.metrics.annotation.Timed
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ModernController {

    val requests:Counter = Counter.build()
            .name("requests_total_modern").help("Modern Total Requests.").register()
    
    @GetMapping("/routes/message")
    fun message(): String {
        requests.inc()
        return "modern"
    }
}
