package ru.practicum.bank.front.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.practicum.bank.front.client.dto.OperationResult;
import ru.practicum.bank.front.client.dto.TransferDto;
import ru.practicum.bank.front.client.dto.UserData;

@Component
public class TransferClient {

    private final String host;

    private final RestTemplate restTemplate;

    public TransferClient(@Value("${transfer.host}") String host, RestTemplate restTemplate) {
        this.host = host;
        this.restTemplate = restTemplate;
    }

    public OperationResult transfer(Long fromAccount, UserData sender, UserData receiver, long toAccount, Double amount) {
        TransferDto transferDto = new TransferDto(fromAccount,sender, receiver,  toAccount, amount);
        return restTemplate.postForObject(host+"/api/v1/transfer",transferDto, OperationResult.class);
    }

    public OperationResult innerTransfer(Long fromAccount, UserData user, Long toAccount, Double amount) {
        TransferDto transferDto = new TransferDto(fromAccount,user, user, toAccount, amount);
        return restTemplate.postForObject(host+"/api/v1/transfer",transferDto, OperationResult.class);
    }
}
