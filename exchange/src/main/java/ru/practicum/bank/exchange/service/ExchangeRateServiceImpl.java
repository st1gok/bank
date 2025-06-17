package ru.practicum.bank.exchange.service;

import org.springframework.stereotype.Service;
import ru.practicum.bank.exchange.domain.Rate;
import ru.practicum.bank.exchange.repository.ExchangeRateRepository;

import java.util.List;

@Service
public class ExchangeRateServiceImpl implements ExchangeRateService {

    private final ExchangeRateRepository exchangeRateRepository;

    public ExchangeRateServiceImpl(ExchangeRateRepository exchangeRateRepository) {
        this.exchangeRateRepository = exchangeRateRepository;
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
        exchangeRateRepository.saveAll(rates);
    }
}
