package ru.practicum.bank.account.service;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.practicum.bank.account.client.NotificationClient;
import ru.practicum.bank.account.domain.Account;
import ru.practicum.bank.account.rest.dto.MessageDto;
import ru.practicum.bank.account.domain.User;
import ru.practicum.bank.account.repository.UserRepository;
import ru.practicum.bank.account.rest.dto.AccountDto;
import ru.practicum.bank.account.rest.dto.AuthenticationRequest;
import ru.practicum.bank.account.rest.dto.RegistrationRequest;
import ru.practicum.bank.account.rest.dto.UserDto;
import ru.practicum.bank.account.rest.mappers.AccountMapper;
import ru.practicum.bank.account.rest.mappers.UserMapper;

import java.util.List;
import java.util.Optional;

@Component
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final AccountMapper accountMapper;
    private final NotificationClient notificationClient;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(PasswordEncoder passwordEncoder,
                           UserRepository userRepository,
                           UserMapper userMapper,
                           AccountMapper accountMapper,
                           NotificationClient notificationClient) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.accountMapper = accountMapper;
        this.notificationClient = notificationClient;
    }

    public void register(RegistrationRequest request) {
        var user = new User()
                .setLogin(request.getLogin())
                .setPassword(request.getPassword());

        user.setAccount(new Account().setName(request.getName())
                .setBirthday(request.getBirthday())
                .setSurname(request.getSurname())
                .setEmail(request.getEmail()));

        userRepository.save(user);
        notificationClient.notify(new MessageDto()
                .setRecipient(new MessageDto.Recipient()
                        .setFirstName(request.getName())
                        .setLastName(request.getSurname())
                        .setEmail(request.getEmail())
                )
                .setCaption("Спасибо за регистрацию")
                .setMessage("Пользователь "+request.getLogin()+" был успешно создан!"));
    }

    public void updateAccount(AccountDto accountDto, String login) {
        var user = userRepository.findByLogin(login).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        var account = user.getAccount();
        account.setEmail(accountDto.getEmail());
        account.setBirthday(accountDto.getBirthday());
        account.setName(accountDto.getName());
        account.setSurname(accountDto.getSurname());
        userRepository.save(user);
    }


    public boolean authenticate(AuthenticationRequest request) {
        User user = userRepository.findByLogin(request.getLogin()).orElseThrow(() -> new UsernameNotFoundException("Authentication failed"));
        return passwordEncoder.matches(request.getPassword(), user.getPassword());
    }

    @Override
    public Optional<UserDto> getUser(String login) {
        return userRepository.findByLogin(login).map(userMapper::toDto);
    }

    @Override
    public void changePassword(UserDto userDto) {
        User user = userRepository.findByLogin(userDto.getLogin()).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        user.setPassword(userDto.getPassword());
        userRepository.save(user);
    }

    @Override
    public AccountDto getUserData(String login) {
        var account = userRepository.findByLogin(login).get().getAccount();
        return accountMapper.toDto(account);
    }

    @Override
    public List<UserDto> getUsers() {
        return userMapper.toDto(userRepository.findAll());
    }
}
