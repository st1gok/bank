package ru.practicum.bank.cash.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.practicum.bank.cash.models.CashDto;
import ru.practicum.bank.cash.models.OperationResult;

@Component
@RefreshScope
public class AccountClient {

    @Value("${account.host}")
    private String host;

    RestTemplate restTemplate;
    public AccountClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public OperationResult cash(CashDto cashDto) {
        return restTemplate.postForObject(host+"/api/v1/money/cash", cashDto, OperationResult.class);
    }
}
