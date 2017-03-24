package demo.routing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/routes")
public class SessionStoringController {

    @Autowired
    SessionDemo sessionDemo;

    @GetMapping("/withsession")
    public String store(HttpSession httpSession) {
        httpSession.setAttribute("from", "pcf");
        return "session-from-pcf: counter: " + sessionDemo.getCounter();
    }

    @GetMapping("/logoff")
    public void logoff(HttpSession httpSession){
        httpSession.invalidate();
    }
}
