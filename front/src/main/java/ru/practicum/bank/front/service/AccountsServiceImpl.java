package ru.practicum.bank.front.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import ru.practicum.bank.front.client.AccountClient;
import ru.practicum.bank.front.client.dto.AccountDto;
import ru.practicum.bank.front.domain.AccountModel;

import java.util.Currency;
import java.util.List;

@Service
public class AccountsServiceImpl implements AccountsService {

    private final AccountClient accountClient;

    public AccountsServiceImpl(AccountClient accountClient) {
        this.accountClient = accountClient;
    }

    @Override
    public List<AccountModel> getUserAccounts() {
            var user = getCurrentUser();
            List<AccountDto> accounts = accountClient.getAccounts(user.getUsername());
            return accounts.stream().map(acc -> new AccountModel()
                            .setCurrency(Currency.getInstance(acc.getCurrency()))
                            .setValue(acc.getValue())
                            .setId(acc.getId()))
                    .toList();

    }

    @Override
    public List<AccountModel> getUserAccounts(String username) {
        List<AccountDto> accounts = accountClient.getAccounts(username);
        return accounts.stream().map(acc -> new AccountModel()
                        .setCurrency(Currency.getInstance(acc.getCurrency()))
                        .setValue(acc.getValue())
                        .setId(acc.getId()))
                .toList();

    }

    @Override
    public void createAccount(String currency) {
        var user = getCurrentUser();
        accountClient.createAccount(user.getUsername(), currency);
    }

    @Override
    public void closeAccount(Long id) {
        var user = getCurrentUser();
        accountClient.closeAccount(user.getUsername(), id);
    }

    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (User)authentication.getPrincipal();
    }



}
