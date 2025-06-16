package ru.practicum.bank.front.domain;

import jakarta.validation.constraints.Email;
import ru.practicum.bank.front.validation.AgeLimit;

import java.time.LocalDate;

public class SignupModel extends ChangePasswordModel {
    private String login;
    private String name;
    @Email
    private String email;

    @AgeLimit
    private LocalDate birthday;

    public String getEmail() {
        return email;
    }

    public SignupModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getLogin() {
        return login;
    }

    public SignupModel setLogin(String login) {
        this.login = login;
        return this;
    }

    public String getName() {
        return name;
    }

    public SignupModel setName(String name) {
        this.name = name;
        return this;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public SignupModel setBirthday(LocalDate birthday) {
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
