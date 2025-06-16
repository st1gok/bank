package ru.practicum.bank.notification.service;

import ru.practicum.bank.notification.domain.Message;

public interface NotificationService {
    void notify(Message message);
}
