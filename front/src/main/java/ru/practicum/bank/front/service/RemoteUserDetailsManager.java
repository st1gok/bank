package ru.practicum.bank.front.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.practicum.bank.front.client.dto.Credentials;
import ru.practicum.bank.front.client.AccountClient;
//import ru.practicum.bank.account.repository.UserRepository;

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
        // Загружаем сущность User из базы данных
//        ru.practicum.bank.account.domain.User user = userRepository.findByLogin(login)
//                .orElseThrow(() -> new UsernameNotFoundException(login));

        // Используем класс Spring, который реализует интерфейс UserDetails
        return new User(credentials.getLogin(), credentials.getPassword(), new ArrayList<>());
    }

}