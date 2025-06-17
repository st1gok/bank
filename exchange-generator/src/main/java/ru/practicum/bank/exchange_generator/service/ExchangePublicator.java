package ru.practicum.bank.exchange_generator.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.practicum.bank.exchange_generator.client.ExchangeClient;

@Component
public class ExchangePublicator {

    private final ExchangeGeneratorService exchangeGeneratorService;
    private final ExchangeClient client;

    public ExchangePublicator(ExchangeGeneratorService exchangeGeneratorService, ExchangeClient client) {
        this.exchangeGeneratorService = exchangeGeneratorService;
        this.client = client;
    }

    @Scheduled(initialDelay = 5000, fixedDelay = 1000)
    public void publish() {
        var currencies = exchangeGeneratorService.generate();
        client.publish(currencies);
    }
}
