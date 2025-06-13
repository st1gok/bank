package ru.practicum.bank.front.domain;

import java.util.List;

public class UserAccountsModel extends SignupModel {
    private List<String> accounts;

    public List<String> getAccounts() {
        return accounts;
    }

    public UserAccountsModel setAccounts(List<String> accounts) {
        this.accounts = accounts;
        return this;
    }
}
