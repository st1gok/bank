package ru.practicum.bank.cash.client;

import ru.practicum.bank.cash.models.MessageDto;

public interface NotificationClient {

     void notify(MessageDto messageDto);

}
