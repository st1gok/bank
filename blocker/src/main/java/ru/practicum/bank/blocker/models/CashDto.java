package ru.practicum.bank.blocker.models;

public class CashDto {
    private Long account;
    private Double amount;
    private String currency;
    private CashAction action;

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
