package ru.practicum.bank.exchange_generator.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.practicum.bank.exchange_generator.domain.Rate;

import java.util.List;

@Component
public class ExchangeClient {

    private final String host;

    private final RestTemplate restTemplate;

    public ExchangeClient(@Value("${exchange.host}") String host, RestTemplate restTemplate) {
        this.host = host;
        this.restTemplate = restTemplate;
    }

    public void publish(List<Rate> currencies) {
        restTemplate.postForLocation(host+"/api/v1/rates", currencies);
    }

}
