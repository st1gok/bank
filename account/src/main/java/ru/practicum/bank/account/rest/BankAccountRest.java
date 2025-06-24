package ru.practicum.bank.account.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.bank.account.rest.dto.BankAccountDto;
import ru.practicum.bank.account.service.AccountService;

@RestController
public class BankAccountRest {

    private final AccountService accountService;

    public BankAccountRest(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/api/v1/account/{id}/currency")
    ResponseEntity<BankAccountDto> getCurrency(@PathVariable("id") long id) {
        var acc = accountService.getAccount(id).orElseThrow(() -> new RuntimeException("Account not found"));
        return ResponseEntity.ok(new BankAccountDto().setCurrency(acc.getCurrency()));
    }
}
