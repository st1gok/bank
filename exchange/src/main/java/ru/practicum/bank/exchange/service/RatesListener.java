package ru.practicum.bank.exchange.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.practicum.bank.exchange.domain.Rate;

@Service
public class RatesListener {
    private static final Logger log = LoggerFactory.getLogger(RatesListener.class);

    private final ExchangeRateService exchangeRateService;

    public RatesListener(ExchangeRateService exchangeRateService) {
        this.exchangeRateService = exchangeRateService;
    }

    @KafkaListener(topics = "${topic.name}")
    public void listenOrder(ConsumerRecord<String, Rate> record) {
        log.info("Получено новое сообщение: {}", record.key());
        Rate rate = record.value();
        exchangeRateService.save(rate);
    }

}
