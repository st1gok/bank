package ru.practicum.bank.exchange.service;

import ru.practicum.bank.exchange.domain.Rate;

import java.util.List;

public interface ExchangeRateService {
    List<Rate> getRates();

    Rate getRate(String currency);

    void save(List<Rate> rates);
}

