package ru.practicum.bank.blocker.rest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.bank.blocker.models.CashDto;
import ru.practicum.bank.blocker.models.Check;
import ru.practicum.bank.blocker.models.TransferDto;
import ru.practicum.bank.blocker.service.OperationCheckService;

@RestController()
@RequestMapping("/api/v1/check")
public class OperationCheckApi {

    private final OperationCheckService operationCheckService;

     public OperationCheckApi(OperationCheckService operationCheckService) {
         this.operationCheckService = operationCheckService;
     }

    @PostMapping("/cash")
    public Check operationCheck(@RequestBody CashDto cashDto) {
        return operationCheckService.checkOperation(cashDto);
    }

    @PostMapping("/transfer")
    public Check operationCheck(@RequestBody TransferDto transferDto) {
        return operationCheckService.checkOperation(transferDto);
    }
}
