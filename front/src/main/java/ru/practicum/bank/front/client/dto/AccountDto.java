package ru.practicum.bank.front.client.dto;


public class AccountDto {
    private long id;

    private Double value;

    private String currency;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCurrency() {
        return currency;
    }

    public AccountDto setCurrency(String currency) {
        this.currency = currency;
        return this;
    }

    public Double getValue() {
        return value;
    }

    public AccountDto setValue(Double value) {
        this.value = value;
        return this;
    }


}
