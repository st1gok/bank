package ru.practicum.bank.front.service;

import ru.practicum.bank.front.client.dto.OperationResult;
import ru.practicum.bank.front.domain.CashAction;

import java.util.Currency;

public interface MoneyService {
    OperationResult cash(Long account, Double amount, CashAction action);

    OperationResult transfer(Long fromAccount, Long toAccount, Double amount);

    OperationResult transfer(Long fromAccount, String username, Currency destCurrency, Double amount);
}
