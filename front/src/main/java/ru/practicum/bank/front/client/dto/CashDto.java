package ru.practicum.bank.front.client.dto;

import ru.practicum.bank.front.domain.CashAction;

public class CashDto {
    Long account;
    Double amount;
    String currency;
    CashAction action;
    UserData user;

    public UserData getUser() {
        return user;
    }

    public CashDto setUser(UserData user) {
        this.user = user;
        return this;
    }

    public CashAction getAction() {
        return action;
    }

    public CashDto setAction(CashAction action) {
        this.action = action;
        return this;
    }

    public Long getAccount() {
        return account;
    }

    public CashDto setAccount(Long account) {
        this.account = account;
        return this;
    }

    public Double getAmount() {
        return amount;
    }

    public CashDto setAmount(Double amount) {
        this.amount = amount;
        return this;
    }

    public String getCurrency() {
        return currency;
    }

    public CashDto setCurrency(String currency) {
        this.currency = currency;
        return this;
    }
}
