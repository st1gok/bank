package ru.practicum.bank.front.controller;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.util.UriUtils;
import ru.practicum.bank.front.service.UserService;
import ru.practicum.bank.front.domain.SignupModel;

import java.util.stream.Collectors;

@Controller()
public class SignupController {

    private final UserService userService;

    public SignupController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/signup")
    public String signup(Model model) {
        return "signup";
    }

    @PostMapping("/signup")
    public String signup(@Valid @ModelAttribute SignupModel signupModel, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) {
            String errorParamName = "errors";
            var errorString = bindingResult.getAllErrors().stream().map(error -> errorParamName+"="+error.getDefaultMessage()).collect(Collectors.joining("&"));
            return "redirect:/signup?"+ UriUtils.encodePath(errorString, "UTF-8");
        }
        userService.registration(signupModel);
        return "redirect:/";
    }
}
