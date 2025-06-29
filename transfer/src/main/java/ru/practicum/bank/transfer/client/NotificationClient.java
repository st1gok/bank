package ru.practicum.bank.transfer.client;

import ru.practicum.bank.transfer.models.Message;

public interface NotificationClient {
    void notify(Message message);
}
