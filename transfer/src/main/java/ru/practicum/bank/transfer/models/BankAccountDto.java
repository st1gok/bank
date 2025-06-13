package ru.practicum.bank.transfer.models;


public class BankAccountDto {
    private Long id;

    private String currency;

    private String value;

    public String getCurrency() {
        return currency;
    }

    public Long getId() {
        return id;
    }

    public BankAccountDto setId(Long id) {
        this.id = id;
        return this;
    }

    public BankAccountDto setCurrency(String currency) {
        this.currency = currency;
        return this;
    }

    public String getValue() {
        return value;
    }

    public BankAccountDto setValue(String value) {
        this.value = value;
        return this;
    }
}
