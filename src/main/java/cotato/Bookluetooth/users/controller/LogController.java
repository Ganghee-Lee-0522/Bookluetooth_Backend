package cotato.Bookluetooth.users.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
public class LogController {

    @RequestMapping("/")
    public String loginSuccess() {
        return "loginSuccess";
    }

    @RequestMapping("/logoutSuccess")
    public String logoutSuccess() {
        return "logoutSuccess";
    }
}
