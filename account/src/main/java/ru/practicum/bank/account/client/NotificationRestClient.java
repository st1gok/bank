package ru.practicum.bank.account.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.practicum.bank.account.rest.dto.MessageDto;

@Component
public class NotificationRestClient {

    private final String host;

    private final RestTemplate restTemplate;
    public NotificationRestClient(@Value("${notification.host}")String host, RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        this.host = host;
    }

    public void notify(MessageDto message) {
        restTemplate.postForLocation(host+"/api/v1/notify", message);
    }
}