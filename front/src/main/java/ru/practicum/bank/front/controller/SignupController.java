package ru.practicum.bank.front.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.practicum.bank.front.service.UserService;
import ru.practicum.bank.front.domain.SignupModel;

@Controller()
@RefreshScope
public class SignupController {

    @Value("${gateway.host}")
    private String gatewayHost;

    UserService userService;

    public SignupController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/signup")
    public String signup(Model model) {
        return "signup";
    }

    @PostMapping("/signup")
    public String signup(@ModelAttribute SignupModel signupModel) throws Exception {
        userService.registration(signupModel);
        return "redirect:"+gatewayHost+"/";
    }
}
