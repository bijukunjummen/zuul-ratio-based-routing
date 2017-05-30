package demo.routing

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

import javax.servlet.http.HttpSession

@RestController
@RequestMapping("/routes")
class SessionStoringController {

    @Autowired
    private var sessionDemo: SessionDemo? = null

    @GetMapping("/withsession")
    fun store(httpSession: HttpSession): String {
        httpSession.setAttribute("from", "pcf")
        return "session-from-pcf: counter: " + sessionDemo?.getCounter()
    }

    @GetMapping("/logoff")
    fun logoff(httpSession: HttpSession) {
        httpSession.invalidate()
    }
}
