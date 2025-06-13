package ru.practicum.bank.exchange_generator.service;

import ru.practicum.bank.exchange_generator.domain.Rate;

import java.util.List;

public interface ExchangeGeneratorService {
    List<Rate> generate();
}
