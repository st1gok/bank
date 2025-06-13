package ru.practicum.bank.transfer.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.practicum.bank.transfer.models.Message;

@Component
@RefreshScope
public class NotificationClient {

    @Value("${notification.host}")
    private String host;

    RestTemplate restTemplate;
    public NotificationClient(RestTemplate restTemplate) {
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
