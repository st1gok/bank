package ru.practicum.bank.exchange.rest;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import ru.practicum.bank.exchange.domain.Rate;
import ru.practicum.bank.exchange.service.ExchangeRateService;

import java.util.List;

@RestController()
@RequestMapping("/api/v1")
public class ExchangeRateRest {

    private final ExchangeRateService exchangeRateService;

    public ExchangeRateRest(ExchangeRateService exchangeRateService) {
        this.exchangeRateService = exchangeRateService;
    }

    @GetMapping("/rates")
    public List<Rate> getRates() {
        return exchangeRateService.getRates();
    }

    @GetMapping("/rate/{currency}")
    public Rate getRate(@PathVariable String currency) {
        return exchangeRateService.getRate(currency);
    }

    @PostMapping("/rates")
    @Secured({})
    public void postRate(@RequestBody List<Rate> rates) {
        exchangeRateService.save(rates);
    }
}
