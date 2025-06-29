package ru.practicum.bank.account.client;

import ru.practicum.bank.account.rest.dto.MessageDto;

public interface NotificationClient {

     void notify(MessageDto message);
}
