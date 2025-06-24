package ru.practicum.bank.account.rest.dto;

public class CashDto {
    private Long account;
    private Double amount;
    private String currency;
    private CashAction action;
    private AccountDto user;

    public AccountDto getUser() {
        return user;
    }

    public CashDto setUser(AccountDto user) {
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
