package ru.practicum.bank.account.rest.dto;

import java.time.LocalDate;

public class RegistrationRequest {
    private String login;
    private String password;
    private String name;
    private String email;

    private String surname;

    private LocalDate birthday;

    public String getEmail() {
        return email;
    }

    public RegistrationRequest setEmail(String email) {
        this.email = email;
        return this;
    }

    public RegistrationRequest setLogin(String login) {
        this.login = login;
        return this;
    }

    public RegistrationRequest setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public RegistrationRequest setName(String name) {
        this.name = name;
        return this;
    }

    public String getSurname() {
        return surname;
    }

    public RegistrationRequest setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public RegistrationRequest setBirthday(LocalDate birthday) {
        this.birthday = birthday;
        return this;
    }
}
