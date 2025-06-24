package ru.practicum.bank.cash.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.practicum.bank.cash.models.CashDto;
import ru.practicum.bank.cash.models.OperationResult;

@Component
public class AccountClient {

    private final String host;

    private final RestTemplate restTemplate;

    public AccountClient(@Value("${account.host}") String host, RestTemplate restTemplate) {
        this.host = host;
        this.restTemplate = restTemplate;
    }

    public OperationResult cash(CashDto cashDto) {
        return restTemplate.postForObject(host+"/api/v1/money/cash", cashDto, OperationResult.class);
    }
}
