package ru.practicum.bank.front.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.practicum.bank.front.client.dto.CashDto;
import ru.practicum.bank.front.client.dto.OperationResult;
import ru.practicum.bank.front.client.dto.UserData;
import ru.practicum.bank.front.domain.CashAction;

@Component
@RefreshScope
public class CashClient {

    @Value("${cash.host}")
    String host;

    RestTemplate restTemplate;

    public CashClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public OperationResult cash(Long fromAccount, UserData user , Double amount, CashAction action) {
        CashDto cashDto = new CashDto().setAction(action)
                .setAccount(fromAccount)
                .setAmount(amount)
                .setUser(user);
        return restTemplate.postForObject(host+"/api/v1/cash", cashDto, OperationResult.class);
    }


}
