package ru.practicum.bank.account.rest.dto;

import ru.practicum.bank.account.domain.BankAccount;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class AccountDto {
    private String name;

    private String surname;
    private String email;

    private LocalDate birthday;

//    private Set<BankAccountDto> bankAccounts = new HashSet<>();

    public String getEmail() {
        return email;
    }

    public AccountDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getName() {
        return name;
    }

    public AccountDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getSurname() {
        return surname;
    }

    public AccountDto setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public AccountDto setBirthday(LocalDate birthday) {
        this.birthday = birthday;
        return this;
    }

//    public Set<BankAccountDto> getBankAccounts() {
//        return bankAccounts;
//    }
//
//    public AccountDto setBankAccounts(Set<BankAccountDto> bankAccounts) {
//        this.bankAccounts = bankAccounts;
//        return this;
//    }
}
