package ru.practicum.bank.front.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ru.practicum.bank.front.service.AccountsService;
import ru.practicum.bank.front.service.UserService;

import java.util.Currency;
import java.util.List;

@Controller
public class MainController {

    UserService userService;
    AccountsService accountsService;

    public MainController(UserService userService, AccountsService accountsService) {
        this.userService = userService;
        this.accountsService = accountsService;
    }

    @GetMapping
    public String main(Model model) {

        List<Currency> currency = List.of(Currency.getInstance("RUB"),
                Currency.getInstance("CNY"),
                Currency.getInstance("USD"));

        var userData = userService.getUserDataModel();
        var accounts = accountsService.getUserAccounts();
        var users = userService.getUsers();

        model.addAttribute("name", userData.getName());
        model.addAttribute("birthdate", userData.getBirthday());
        model.addAttribute("login", SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("username", SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("currency", currency);
        model.addAttribute("accounts", accounts);
        model.addAttribute("users", users);


        return "main";
    }
}
