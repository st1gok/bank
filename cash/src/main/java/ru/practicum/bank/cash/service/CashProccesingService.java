package ru.practicum.bank.cash.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import ru.practicum.bank.cash.client.AccountClient;
import ru.practicum.bank.cash.client.BlockerClient;
import ru.practicum.bank.cash.client.NotificationClient;
import ru.practicum.bank.cash.models.CashDto;
import ru.practicum.bank.cash.models.Check;
import ru.practicum.bank.cash.models.Message;
import ru.practicum.bank.cash.models.OperationResult;

@Service
public class CashProccesingService {

    AccountClient accountClient;
    BlockerClient blockerClient;
    NotificationClient  notificationClient;
    private final ObjectMapper objectMapper;

    public CashProccesingService(AccountClient accountClient, BlockerClient blockerClient, NotificationClient notificationClient, ObjectMapper objectMapper) {
        this.accountClient = accountClient;
        this.blockerClient = blockerClient;
        this.notificationClient = notificationClient;
        this.objectMapper = objectMapper;
    }

    public OperationResult cashProcessing(CashDto cashDto) throws Exception{
        Check check = blockerClient.checkOperation(cashDto);
        if (check.getBlockResult()) {
            notificationClient.notify(new Message().setRecipient(new Message.Recipient()
                            .setEmail(cashDto.getUser().getEmail())
                            .setFirstName(cashDto.getUser().getName())
                            .setLastName(cashDto.getUser().getSurname()))
                    .setCaption("Операция была заблокирована")
                    .setMessage("Операция показалась подозрительной"));
            return new OperationResult().setErrorMessage("Операция была заблокирована").setStatus(400);
        } else {
            try {
                return accountClient.cash(cashDto);
            } catch (HttpClientErrorException e) {

            return objectMapper.readValue(e.getResponseBodyAsString(), OperationResult.class);
        }

        }
    }
}
