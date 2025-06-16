package ru.practicum.bank.front.service;

import org.springframework.stereotype.Service;
import ru.practicum.bank.front.client.CashClient;
import ru.practicum.bank.front.client.TransferClient;
import ru.practicum.bank.front.client.dto.OperationResult;
import ru.practicum.bank.front.domain.CashAction;

import java.util.Currency;

@Service
public class MoneyService {
    private final CashClient cashClient;
    private final TransferClient transferClient;
    private final AccountsService accountsService;
    private final UserService userService;

    public MoneyService(CashClient cashClient, TransferClient transferClient, AccountsService accountsService, UserService userService) {
        this.cashClient = cashClient;
        this.transferClient = transferClient;
        this.accountsService = accountsService;
        this.userService = userService;
    }


    public OperationResult cash(Long account, Double amount, CashAction action) {
        var user = userService.getCurrentUserData();
        return cashClient.cash(account, user, amount, action);
    }

    public OperationResult transfer(Long fromAccount, Long toAccount, Double amount) {
        var user = userService.getCurrentUserData();
        return transferClient.innerTransfer(fromAccount, user, toAccount, amount);
    }

    public OperationResult transfer(Long fromAccount, String username, Currency destCurrency, Double amount) {
        var accounts = accountsService.getUserAccounts(username);
        var sender = userService.getCurrentUserData();
        var receiver = userService.getUserData(username);
        var acc = accounts.stream().filter(accountModel -> accountModel.getCurrency().equals(destCurrency)).findFirst().orElseThrow(() -> new MoneyException("Пользователь не имеет открытого счета с данной валютой"));
        return transferClient.transfer(fromAccount,sender, receiver, acc.getId(), amount);
    }
}
