package ru.practicum.bank.front.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.practicum.bank.front.domain.ChangePasswordModel;
import ru.practicum.bank.front.domain.UserDataModel;
import ru.practicum.bank.front.service.AccountsService;
import ru.practicum.bank.front.service.UserService;

@Controller
@RefreshScope
public class UserController {

    @Value("${gateway.host}")
    private String gatewayHost;

    UserService userService;
    private final AccountsService accountService;

    public UserController(UserService userService, AccountsService accountService) {
        this.userService = userService;
        this.accountService = accountService;
    }

    @PostMapping("/user/{username}/editPassword")
    public String editPassword(@PathVariable String username, @Valid @ModelAttribute ChangePasswordModel passwordDto) {
        userService.changePassword(passwordDto.getPassword());
        return "redirect:"+gatewayHost+"/";
    }

    @PostMapping("/user/{username}/editUserAccounts")
    public String editUserInfo(@PathVariable String username, @ModelAttribute UserDataModel userDataModel) {
        userService.updateUserInfo(userDataModel);
        return "redirect:"+gatewayHost+"/";
    }

    @PostMapping("/user/{username}/createAccount")
    public String createAccount(@PathVariable String username, @RequestParam String currency) {
        accountService.createAccount(currency);
        return "redirect:"+gatewayHost+"/";
    }

    @PostMapping("/user/{username}/closeAccount/{id}")
    public String closeAccount(@PathVariable String username, @PathVariable Long id) {
        accountService.closeAccount(id);
        return "redirect:"+gatewayHost+"/";
    }



}
