package demo.routing;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PcfController {

    @GetMapping("/routes/message")
    public String getMessage() {
        return "pcf";
    }
}
