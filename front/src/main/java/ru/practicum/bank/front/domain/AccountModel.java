package ru.practicum.bank.front.domain;

import java.util.Currency;

public class AccountModel {
    private long id;

    private Currency currency;

    private Double value;

    public long getId() {
        return id;
    }

    public AccountModel setId(long id) {
        this.id = id;
        return this;
    }

    public Currency getCurrency() {
        return currency;
    }

    public AccountModel setCurrency(Currency currency) {
        this.currency = currency;
        return this;
    }

    public Double getValue() {
        return value;
    }

    public AccountModel setValue(Double value) {
        this.value = value;
        return this;
    }

    public boolean isExists() {
        return getValue() != null;
    }


}
