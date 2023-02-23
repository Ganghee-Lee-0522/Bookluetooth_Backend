package cotato.Bookluetooth.users.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class LogController {

    @GetMapping("/")
    public String login() {
        return "login";
    }

    @GetMapping("/logoutSuccess")
    public String logout() {
        return "logout";
    }
}
