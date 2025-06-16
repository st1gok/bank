package ru.practicum.bank.account.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "bank_accounts")
public class BankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String currency;

    @ManyToOne(fetch = FetchType.LAZY)
    private Account account;

    @Column(name = "amount")
    private Double value = 0d;

    public long getId() {
        return id;
    }

    public BankAccount setId(long id) {
        this.id = id;
        return this;
    }

    public String getCurrency() {
        return currency;
    }

    public BankAccount setCurrency(String currency) {
        this.currency = currency;
        return this;
    }

    public Account getAccount() {
        return account;
    }

    public BankAccount setAccount(Account account) {
        this.account = account;
        return this;
    }

    public Double getValue() {
        return value;
    }

    public BankAccount setValue(Double value) {
        this.value = value;
        return this;
    }
}
