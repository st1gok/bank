package ru.practicum.bank.front.service;

import ru.practicum.bank.front.domain.AccountModel;

import java.util.List;

public interface AccountsService {
    List<AccountModel> getUserAccounts();
    void createAccount(String currency);
    void closeAccount(Long id);
    List<AccountModel> getUserAccounts(String username);
}
