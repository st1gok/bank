package ru.practicum.bank.account.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.practicum.bank.account.rest.dto.MessageDto;

@Component
@RefreshScope
public class NotificationClient {

//    @Value("${notification.host}")
    private final String host;

    private final RestTemplate restTemplate;

    public NotificationClient(@Value("${notification.host}")String host, RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        this.host = host;
    }

    public void notify(MessageDto messageDto) {
        restTemplate.postForLocation(host+"/api/v1/notify", messageDto);
    }
}
