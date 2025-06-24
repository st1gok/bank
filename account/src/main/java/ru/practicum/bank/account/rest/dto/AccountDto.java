package ru.practicum.bank.account.rest.dto;

import java.time.LocalDate;

public class AccountDto {
    private String name;

    private String surname;
    private String email;

    private LocalDate birthday;

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
}
