package ru.practicum.bank.front.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.practicum.bank.front.client.dto.Credentials;
import ru.practicum.bank.front.domain.RegistrationRequest;
import ru.practicum.bank.front.client.dto.AccountDto;
import ru.practicum.bank.front.client.dto.UserData;

import java.util.Arrays;
import java.util.List;

@Component
public class AccountClient {

    @Value("${account.host}")
    String host;

    RestTemplate restTemplate;
    public AccountClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Retryable(maxAttempts = 3, backoff = @Backoff(delay = 5000))
    public Credentials getUser(String login) {
        return restTemplate.getForObject(host+"/api/v1/users/find/"+login, Credentials.class);
    }

    @Retryable(maxAttempts = 3, backoff = @Backoff(delay = 5000))
    public List<String> getUsers() {
        return Arrays.stream(restTemplate.getForObject(host+"/api/v1/users", Credentials[].class))
                .map(Credentials::getLogin).toList();
    }

    public void updateUser(Credentials user) {
        restTemplate.put(host+"/api/v1/users/" +user.getLogin(), user);
    }

    public void register(RegistrationRequest registrationRequest) {
        restTemplate.postForLocation(host+"/api/v1/users/registration", registrationRequest);
    }

    @Retryable(maxAttempts = 3, backoff = @Backoff(delay = 5000))
    public UserData getUserData(String login) {
        return restTemplate.getForObject(host+"/api/v1/users/"+login+"/data", UserData.class);
    }

    @Retryable(maxAttempts = 3, backoff = @Backoff(delay = 5000))
    public List<AccountDto> getAccounts(String login) {
        return Arrays.stream(restTemplate.getForObject(host+"/api/v1/users/"+login+"/accounts", AccountDto[].class)).toList();
    }

    public void updateUserData(UserData account, String login) {
        restTemplate.put(host+"/api/v1/users/"+login+"/data", account);
    }

    public void createAccount(String login, String currency) {
        restTemplate.postForLocation(host+"/api/v1/users/"+login+"/accounts", new AccountDto().setCurrency(currency));
    }

    public void closeAccount(String login, Long id) {
        restTemplate.delete(host+"/api/v1/users/"+login+"/accounts/"+id);
    }
}
