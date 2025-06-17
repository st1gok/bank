package ru.practicum.bank.front.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.practicum.bank.front.client.dto.Credentials;
import ru.practicum.bank.front.client.AccountClient;

import java.util.ArrayList;

@Component("userDetailsService")
public class RemoteUserDetailsManager implements UserDetailsService {

    private final AccountClient accountClient;

    public RemoteUserDetailsManager(AccountClient accountClient) {
        this.accountClient = accountClient;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Credentials credentials = accountClient.getUser(login);
        return new User(credentials.getLogin(), credentials.getPassword(), new ArrayList<>());
    }

}