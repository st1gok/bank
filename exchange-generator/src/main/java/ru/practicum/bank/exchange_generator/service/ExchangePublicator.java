package ru.practicum.bank.exchange_generator.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.practicum.bank.exchange_generator.domain.Rate;

import java.time.Instant;
import java.util.List;

@Component
public class ExchangePublicator {

    private final String topic;
    private final ExchangeGeneratorService exchangeGeneratorService;
    private final KafkaTemplate<String, Rate> kafkaTemplate;
//    private final ExchangeClient client;

    public ExchangePublicator(@Value("${topic.name}") String topic, ExchangeGeneratorService exchangeGeneratorService, KafkaTemplate<String, Rate> kafkaTemplate) {
        this.topic = topic;
        this.exchangeGeneratorService = exchangeGeneratorService;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Scheduled(initialDelay = 5000, fixedDelay = 1000)
    public void publish() {
        var timestampKey = String.valueOf(Instant.now().getEpochSecond());
        var currencies = exchangeGeneratorService.generate();
        for (var rate : currencies) {
            kafkaTemplate.send(topic, rate.getName(), rate);
        }
    }
}
