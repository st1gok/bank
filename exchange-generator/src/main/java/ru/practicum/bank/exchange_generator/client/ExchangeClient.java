package ru.practicum.bank.exchange_generator.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.practicum.bank.exchange_generator.domain.Rate;

import java.util.List;

@Component
@RefreshScope
public class ExchangeClient {

    @Value("${exchange.host}")
    private String host;

    private final RestTemplate restTemplate;

    public ExchangeClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void publish(List<Rate> currencies) {
        restTemplate.postForLocation(host+"/api/v1/rates", currencies);
    }

}
