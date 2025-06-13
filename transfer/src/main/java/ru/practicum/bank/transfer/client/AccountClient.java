package ru.practicum.bank.transfer.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.practicum.bank.transfer.models.BankAccountDto;
import ru.practicum.bank.transfer.models.OperationResult;
import ru.practicum.bank.transfer.models.TransferDto;

@Component
@RefreshScope
public class AccountClient {

    @Value("${account.host}")
    String host;

    RestTemplate restTemplate;
    public AccountClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public OperationResult transfer(TransferDto transferDto) {
        return restTemplate.postForObject(host+"/api/v1/money/transfer", transferDto, OperationResult.class);
    }

    public String getBankAccountCurrency(Long id) {
        return restTemplate.getForObject(host+"/api/v1/account/"+id+"/currency", BankAccountDto.class).getCurrency();
    }
}
