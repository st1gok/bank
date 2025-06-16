package ru.practicum.bank.notification.rest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.bank.notification.domain.Message;
import ru.practicum.bank.notification.service.NotificationService;

@RestController
public class NotificationRest {

    private final NotificationService notificationService;

    public NotificationRest(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping("/api/v1/notify")
    public void notify(@RequestBody Message message) {
        notificationService.notify(message);
    }
}
