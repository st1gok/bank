package ru.practicum.bank.account.service;

import ru.practicum.bank.account.rest.dto.AccountDto;
import ru.practicum.bank.account.rest.dto.RegistrationRequest;
import ru.practicum.bank.account.rest.dto.UserDto;

import java.util.List;
import java.util.Optional;

public interface UserService {
    void register(RegistrationRequest request);
    Optional<UserDto> getUser(String login);

    void changePassword(UserDto userDto);
    void updateAccount(AccountDto accountDto, String login);

    AccountDto getUserData(String login);

    List<UserDto> getUsers();
}
