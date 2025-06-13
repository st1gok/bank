package ru.practicum.bank.exchange_generator.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.practicum.bank.exchange_generator.client.ExchangeClient;
import ru.practicum.bank.exchange_generator.domain.Rate;

import java.util.ArrayList;

import java.util.Currency;
import java.util.List;
import java.util.Random;

@Service
public class ExchangeGeneratorServiceDefault implements ExchangeGeneratorService {

    private final ExchangeClient client;

    public ExchangeGeneratorServiceDefault(ExchangeClient client) {
        this.client = client;
    }

    @Override
    public List<Rate> generate() {
        List<Rate> currencies = new ArrayList<>();
        currencies.add(new Rate().setName("RUB").setValue(1D).setTitle(Currency.getInstance("RUB").getDisplayName()));
        currencies.add(new Rate().setName("USD").setValue(Double.valueOf(getRandom(8000,10000)/100)).setTitle(Currency.getInstance("USD").getDisplayName()));
        currencies.add(new Rate().setName("CNY").setValue(Double.valueOf(getRandom(800,1300)/100)).setTitle(Currency.getInstance("CNY").getDisplayName()));
        return currencies;
    }

    private Integer getRandom(int min, int max) {
        return new Random().ints(min, max)
                .findFirst()
                .getAsInt();
    }

    @Scheduled(initialDelay = 5000, fixedDelay = 1000)
    public void publish() {
        var currencies = generate();
        client.publish(currencies);
    }
}
