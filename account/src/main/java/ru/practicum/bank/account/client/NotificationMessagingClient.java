package ru.practicum.bank.account.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import ru.practicum.bank.account.rest.dto.MessageDto;

@Component
public class NotificationMessagingClient implements NotificationClient {

    private final String topic;
    private final KafkaTemplate<String, MessageDto> kafkaTemplate;

    public NotificationMessagingClient(@Value("${topic.name}") String topic, KafkaTemplate<String, MessageDto> kafkaTemplate) {
        this.topic = topic;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void notify(MessageDto message) {
            kafkaTemplate.send(topic, message);
    }
}
