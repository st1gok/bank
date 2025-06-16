package ru.practicum.bank.cash.rest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.bank.cash.models.CashDto;
import ru.practicum.bank.cash.models.OperationResult;
import ru.practicum.bank.cash.service.CashProccesingService;

@RestController
public class CashRest {

    private final CashProccesingService cashProccesingService;

    public CashRest(CashProccesingService cashProccesingService) {
        this.cashProccesingService = cashProccesingService;
    }

    @PostMapping("/api/v1/cash")
    public OperationResult cashProcessing(@RequestBody CashDto cashDto) throws Exception {
        return cashProccesingService.cashProcessing(cashDto);
    }
}
