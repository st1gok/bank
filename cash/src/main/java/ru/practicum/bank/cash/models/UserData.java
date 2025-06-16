package ru.practicum.bank.cash.models;

import java.time.LocalDate;

public class UserData {
    private String name;

    private String surname;

    private LocalDate birthday;
    private String email;

    public String getEmail() {
        return email;
    }

    public UserData setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getName() {
        return name;
    }

    public UserData setName(String name) {
        this.name = name;
        return this;
    }

    public String getSurname() {
        return surname;
    }

    public UserData setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public UserData setBirthday(LocalDate birthday) {
        this.birthday = birthday;
        return this;
    }
}
