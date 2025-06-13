package ru.practicum.bank.front.domain;

import java.time.LocalDate;

public class UserDataModel {

    private String name;

    private LocalDate birthday;

    private String email;

    public String getEmail() {
        return email;
    }

    public UserDataModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getName() {
        return name;
    }

    public UserDataModel setName(String name) {
        this.name = name;
        return this;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public UserDataModel setBirthday(LocalDate birthday) {
        this.birthday = birthday;
        return this;
    }

    public String getFirstName() {
       return name.split(" ")[1];
    }

    public String getLastName() {
        return name.split(" ")[0];
    }
}
