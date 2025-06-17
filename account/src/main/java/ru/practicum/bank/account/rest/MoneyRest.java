package ru.practicum.bank.account.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.practicum.bank.account.rest.dto.CashDto;
import ru.practicum.bank.account.rest.dto.OperationResult;
import ru.practicum.bank.account.rest.dto.TransferDto;
import ru.practicum.bank.account.service.MoneyProccessingService;

@RestController()
@RequestMapping("/api/v1/money")
public class MoneyRest {

    private final MoneyProccessingService moneyProccessingService;

    public MoneyRest(MoneyProccessingService moneyProccessingService) {
        this.moneyProccessingService = moneyProccessingService;
    }

    @PostMapping("/cash")
    public ResponseEntity cash(@RequestBody CashDto cashDto) {
        try {
            moneyProccessingService.proccessing(cashDto);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(new OperationResult().setStatus(400).setErrorMessage(e.getMessage()));
        }
        return ResponseEntity.ok(new OperationResult().setStatus(200));
    }

    @PostMapping("/transfer")
    public ResponseEntity transfer(@RequestBody TransferDto transferDto) {
        try {
            moneyProccessingService.proccessing(transferDto);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(new OperationResult().setStatus(400).setErrorMessage(e.getMessage()));
        }
        return ResponseEntity.ok(new OperationResult().setStatus(200));
    }

}
