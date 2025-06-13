package ru.practicum.bank.front.service;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.practicum.bank.front.domain.RegistrationRequest;
import ru.practicum.bank.front.domain.SignupModel;
import ru.practicum.bank.front.domain.UserDataModel;
import ru.practicum.bank.front.client.AccountClient;
import ru.practicum.bank.front.client.dto.UserData;
import ru.practicum.bank.front.client.dto.Credentials;

import java.util.List;

@Service
public class UserServiceDefault implements UserService {

    private final AccountClient accountClient;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationConfiguration authenticationConfiguration;

    public UserServiceDefault(AccountClient accountClient, PasswordEncoder passwordEncoder, AuthenticationConfiguration authenticationConfiguration) {
        this.accountClient = accountClient;
        this.passwordEncoder = passwordEncoder;
        this.authenticationConfiguration = authenticationConfiguration;
    }

    public void changePassword(String password) {
        var user = getCurrentUser();
        var userDto = new Credentials()
                .setLogin(user.getUsername())
                .setPassword(passwordEncoder.encode(password));
        accountClient.updateUser(userDto);
    }

    @Override
    public void registration(SignupModel signupModel) throws Exception {
        RegistrationRequest registrationRequest = new RegistrationRequest()
                .setBirthday(signupModel.getBirthday())
                .setName(signupModel.getFirstName())
                .setSurname(signupModel.getLastName())
                .setLogin(signupModel.getLogin())
                .setEmail(signupModel.getEmail())
                .setPassword(passwordEncoder.encode(signupModel.getPassword()));
        accountClient.register(registrationRequest);
        var authenticationRequest = new UsernamePasswordAuthenticationToken(signupModel.getLogin(), signupModel.getPassword());
        var authentication = authenticationConfiguration.getAuthenticationManager().authenticate(authenticationRequest);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    @Override
    public UserDataModel getUserDataModel() {
        var userData = getCurrentUserData();
        return new UserDataModel().setBirthday(userData.getBirthday())
                .setName(userData.getSurname() + " " + userData.getName());
    }

    @Override
    public UserData getCurrentUserData() {
        var user = getCurrentUser();
        return getUserData(user.getUsername());
    }

    @Override
    public UserData getUserData(String login) {
        return accountClient.getUserData(login);
    }

    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (User) authentication.getPrincipal();
    }

    @Override
    public void updateUserInfo(UserDataModel userDataModel) {
        var user = getCurrentUser();
        UserData userData = new UserData()
                .setName(userDataModel.getFirstName())
                .setSurname(userDataModel.getLastName())
                .setEmail(userDataModel.getEmail())
                .setBirthday(userDataModel.getBirthday());
        accountClient.updateUserData(userData, user.getUsername());
    }

    @Override
    public List<String> getUsers() {
        return accountClient.getUsers();
    }
}
