package ru.practicum.bank.cash.service;

import org.springframework.stereotype.Service;
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

    public CashProccesingService(AccountClient accountClient, BlockerClient blockerClient, NotificationClient notificationClient) {
        this.accountClient = accountClient;
        this.blockerClient = blockerClient;
        this.notificationClient = notificationClient;
    }

    public OperationResult cashProcessing(CashDto cashDto) {
        Check check = blockerClient.checkOperation(cashDto);
        if (check.getBlockResult()) {
            notificationClient.notify(new Message().setCaption("Операция была заблокирована").setMessage("Операция показалась подозрительной"));
            return new OperationResult().setErrorMessage("Операция была заблокирована").setStatus(400);
        } else {
            return accountClient.cash(cashDto);
        }
    }
}
