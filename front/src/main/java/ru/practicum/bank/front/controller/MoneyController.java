package ru.practicum.bank.front.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriUtils;
import ru.practicum.bank.front.client.dto.OperationResult;
import ru.practicum.bank.front.domain.CashAction;
import ru.practicum.bank.front.service.MoneyException;
import ru.practicum.bank.front.service.MoneyService;

import java.util.Currency;

@Controller
@RefreshScope
public class MoneyController {

    @Value("${gateway.host}")
    private String gatewayHost;

    MoneyService moneyService;

    public MoneyController(MoneyService moneyService) {
        this.moneyService = moneyService;
    }

    @PostMapping("/user/{username}/cash")
    public String putCash(@PathVariable String username, @RequestParam Double amount, @RequestParam long accountId , @RequestParam CashAction action) {
        OperationResult result = moneyService.cash(accountId, amount, action);
        if (result.getErrorMessage() == null) {
            return "redirect:"+gatewayHost+"/";
        }
        return "redirect:"+gatewayHost+"/?cashErrors="+UriUtils.encodePath(result.getErrorMessage(), "UTF-8");
    }

    @PostMapping("/user/{login}/transfer")
    public String transfer(@PathVariable String login, @RequestParam Long fromAccountId, @RequestParam Currency toCurrency, @RequestParam String username,  @RequestParam Double amount) {
        try {
            OperationResult result = moneyService.transfer(fromAccountId,username, toCurrency, amount);
            if (result.getErrorMessage() == null) {
                return "redirect:" + gatewayHost + "/";
            }
                return "redirect:" + gatewayHost + "/?transferErrors=" + UriUtils.encodePath(result.getErrorMessage(), "UTF-8");
            } catch (MoneyException e) {
                return "redirect:" + gatewayHost + "/?transferErrors=" + UriUtils.encodePath(e.getMessage(), "UTF-8");
            }
    }

    @PostMapping("/user/{login}/self-transfer")
    public String selfTransfer(@PathVariable String login, @RequestParam Long fromAccountId, @RequestParam Long toAccountId,  @RequestParam Double value) {
        OperationResult result = moneyService.transfer(fromAccountId,toAccountId, value);
        if (result.getErrorMessage() == null) {
            return "redirect:"+gatewayHost+"/";
        }
        return "redirect:"+gatewayHost+"/?transferErrors="+ UriUtils.encodePath(result.getErrorMessage(), "UTF-8");
    }

}
