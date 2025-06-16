package ru.practicum.bank.exchange.repository;

import org.springframework.stereotype.Repository;
import ru.practicum.bank.exchange.domain.Rate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ExchangeRateRepositoryImpl implements ExchangeRateRepository {
    Map<String, Rate> exchangeRates = new HashMap<>();

    public void saveAll(List<Rate> rates) {
        rates.forEach(exchangeRate ->
                this.exchangeRates.put(exchangeRate.getName(), exchangeRate)
        );
    }

    @Override
    public List<Rate> getRates() {
        return exchangeRates.values().stream().toList();
    }

    public Rate getExchangeRate(String currency) {
        return exchangeRates.get(currency);
    }
}
