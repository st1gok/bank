package ru.practicum.bank.account.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.practicum.bank.account.domain.Message;

@Component
public class NotificationClient {

    @Value("${notification.host}")
    private String host;

    RestTemplate restTemplate;
    public NotificationClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void notify(Message message) {
        restTemplate.postForLocation(host+"/api/v1/notify", message);
    }
}
