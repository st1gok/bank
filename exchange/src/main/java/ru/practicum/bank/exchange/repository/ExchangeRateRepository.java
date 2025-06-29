package ru.practicum.bank.exchange.repository;

import ru.practicum.bank.exchange.domain.Rate;

import java.util.List;

public interface ExchangeRateRepository {
    Rate getExchangeRate(String currency);
    void saveAll(List<Rate> rates);

    void save(Rate rate);

    List<Rate> getRates();
}
