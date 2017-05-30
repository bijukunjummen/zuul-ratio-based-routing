package demo.routing;


import io.prometheus.client.Counter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LegacyController {

    private Counter requests = Counter.build()
            .name("requests_total_legacy").help("Legacy Total Requests.").register();

    @GetMapping("/routes/message")
    public String getMessage() {
        requests.inc();
        return "legacy";
    }
}
