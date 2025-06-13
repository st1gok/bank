package ru.practicum.bank.account.rest.dto;

public class AuthenticationRequest {
    private String login;
    private String password;

    public String getLogin() {
        return login;
    }

    public AuthenticationRequest setLogin(String login) {
        this.login = login;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public AuthenticationRequest setPassword(String password) {
        this.password = password;
        return this;
    }
}
