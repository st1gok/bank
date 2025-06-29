package ru.practicum.bank.transfer.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.practicum.bank.transfer.models.Message;

@Component
public class NotificationRestClient {

    private final String host;

    private final RestTemplate restTemplate;
    public NotificationRestClient(@Value("${notification.host}") String host, RestTemplate restTemplate) {
        this.host = host;
        this.restTemplate = restTemplate;
    }

    public void notify(Message message) {
        try {
            restTemplate.postForLocation(host+"/api/v1/notify", message);
         } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
