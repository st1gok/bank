package ru.practicum.bank.cash.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.practicum.bank.cash.models.CashDto;
import ru.practicum.bank.cash.models.Check;

@Component
@RefreshScope
public class BlockerClient {

//    @Value("${blocker.host}")
    private final String host;

    private final RestTemplate restTemplate;
    public BlockerClient(@Value("${blocker.host}") String host, RestTemplate restTemplate) {
        this.host = host;
        this.restTemplate = restTemplate;
    }

    @Retryable(maxAttempts = 3, backoff = @Backoff(delay = 5000))
    public Check checkOperation(CashDto cashDto) {
        return restTemplate.postForObject(host+"/api/v1/check/cash", cashDto, Check.class);
    }

}
