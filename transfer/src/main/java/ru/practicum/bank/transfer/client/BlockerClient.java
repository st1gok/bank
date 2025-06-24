package ru.practicum.bank.transfer.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.practicum.bank.transfer.models.Check;
import ru.practicum.bank.transfer.models.TransferDto;

@Component
public class BlockerClient {

    RestTemplate restTemplate;
    @Value("${blocker.host}")
    private String host;

    public BlockerClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Retryable(maxAttempts = 3, backoff = @Backoff(delay = 5000))
    public Check checkOperation(TransferDto transferDto) {
        return restTemplate.postForObject(host+"/api/v1/check/transfer", transferDto, Check.class);
    }

}
