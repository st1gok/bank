package ru.practicum.bank.exchange.service;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Service;
import ru.practicum.bank.exchange.domain.Rate;
import ru.practicum.bank.exchange.repository.ExchangeRateRepository;

import java.time.Instant;
import java.util.List;

@Service
public class ExchangeRateServiceImpl implements ExchangeRateService {

    private final ExchangeRateRepository exchangeRateRepository;
    private final MeterRegistry meterRegistry;

    public ExchangeRateServiceImpl(ExchangeRateRepository exchangeRateRepository, MeterRegistry meterRegistry) {
        this.exchangeRateRepository = exchangeRateRepository;
        this.meterRegistry = meterRegistry;
    }

    @Override
    public List<Rate> getRates() {
        return exchangeRateRepository.getRates();
    }

    @Override
    public Rate getRate(String currency) {
        return exchangeRateRepository.getExchangeRate(currency);
    }

    @Override
    public void save(List<Rate> rates) {
        meterRegistry.gauge("rates_lastUpdateTime",  Instant.now().getEpochSecond());
        exchangeRateRepository.saveAll(rates);
    }

    @Override
    public void save(Rate rate) {
        meterRegistry.gauge("rates_lastUpdateTime",  Instant.now().getEpochSecond());
        exchangeRateRepository.save(rate);
    }
}
