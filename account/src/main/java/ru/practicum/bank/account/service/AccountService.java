package ru.practicum.bank.account.service;

import ru.practicum.bank.account.rest.dto.BankAccountDto;

import java.util.List;
import java.util.Optional;

public interface AccountService {

    List<BankAccountDto> getAccounts(String login);
    void createAccount(String login, BankAccountDto account);
    void delete(Long accountId);

    Optional<BankAccountDto> getAccount(long id);
}
