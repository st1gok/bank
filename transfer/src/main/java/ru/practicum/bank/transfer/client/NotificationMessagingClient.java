package ru.practicum.bank.transfer.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import ru.practicum.bank.transfer.models.Message;


@Component
public class NotificationMessagingClient implements NotificationClient {

    private final String topic;
    private final KafkaTemplate<String, Message> kafkaTemplate;

    public NotificationMessagingClient(@Value("${topic.name}") String topic, KafkaTemplate<String, Message> kafkaTemplate) {
        this.topic = topic;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void notify(Message message) {
            kafkaTemplate.send(topic, message);
    }
}
