package ru.practicum.bank.notification.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;
import ru.practicum.bank.notification.domain.Message;

@Service
public class MessageListener {
    private static final Logger log = LoggerFactory.getLogger(MessageListener.class);
    private final NotificationService notificationService;

    public MessageListener(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @KafkaListener(topics = "${topic.name}")
    public void listenOrder(ConsumerRecord<String, Message> record,  Acknowledgment acknowledgment) {
        log.info("Получено новое сообщение: {}", record.key());
        Message message = record.value();
        notificationService.notify(message);
        acknowledgment.acknowledge();
    }
}
