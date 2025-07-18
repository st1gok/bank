package ru.practicum.bank.transfer.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.practicum.bank.transfer.models.Rate;

import java.util.Arrays;
import java.util.List;

@Component
public class ExchangeClient {

    private final String host;

    private final RestTemplate restTemplate;
    public ExchangeClient(@Value("${exchange.host}") String host, RestTemplate restTemplate) {
        this.host = host;
        this.restTemplate = restTemplate;
    }

    @Retryable(maxAttempts = 3, backoff = @Backoff(delay = 5000))
    public List<Rate> getRates() {
        return Arrays.stream(restTemplate.getForObject(host+"/api/v1/rates", Rate[].class)).toList();
    }
}
