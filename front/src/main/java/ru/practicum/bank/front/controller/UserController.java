package ru.practicum.bank.front.controller;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriUtils;
import ru.practicum.bank.front.domain.ChangePasswordModel;
import ru.practicum.bank.front.domain.UserDataModel;
import ru.practicum.bank.front.service.AccountsService;
import ru.practicum.bank.front.service.UserService;

import java.util.stream.Collectors;

@Controller
public class UserController {

    UserService userService;
    private final AccountsService accountService;

    public UserController(UserService userService, AccountsService accountService) {
        this.userService = userService;
        this.accountService = accountService;
    }

    @PostMapping("/user/{username}/editPassword")
    public String editPassword(@PathVariable String username, @Valid @ModelAttribute ChangePasswordModel passwordDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String errorParamName = "passwordErrors";
            var errorString = bindingResult.getAllErrors().stream().map(error -> errorParamName+"="+error.getDefaultMessage()).collect(Collectors.joining("&"));
            return "redirect:/?"+UriUtils.encodePath(errorString, "UTF-8");
        }
        userService.changePassword(passwordDto.getPassword());
        return "redirect:/";
    }

    @PostMapping("/user/{username}/editUserInfo")
    public String editUserInfo(@PathVariable String username,@Valid @ModelAttribute UserDataModel userDataModel, BindingResult bindingResult ) {
        if (bindingResult.hasErrors()) {
            String errorParamName = "userAccountsErrors";
            var errorString = bindingResult.getAllErrors().stream().map(error -> errorParamName+"="+error.getDefaultMessage()).collect(Collectors.joining("&"));
            return "redirect:/?"+UriUtils.encodePath(errorString, "UTF-8");
        }
        userService.updateUserInfo(userDataModel);
        return "redirect:/";
    }

    @PostMapping("/user/{username}/createAccount")
    public String createAccount(@PathVariable String username,@RequestParam String currency) {
        accountService.createAccount(currency);
        return "redirect:/";
    }

    @PostMapping("/user/{username}/closeAccount/{id}")
    public String closeAccount(@PathVariable String username, @PathVariable Long id) {
        accountService.closeAccount(id);
        return "redirect:/";
    }



}
