package ru.practicum.bank.transfer.rest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.bank.transfer.models.OperationResult;
import ru.practicum.bank.transfer.models.TransferDto;
import ru.practicum.bank.transfer.service.TransferProccesingService;

@RestController
public class TransferRest {

    private final TransferProccesingService transferProccesingService;

    public TransferRest(TransferProccesingService transferProccesingService) {
        this.transferProccesingService = transferProccesingService;
    }

    @PostMapping("/api/v1/transfer")
    public OperationResult cashProcessing(@RequestBody TransferDto transferDto) throws Exception {
        return transferProccesingService.transferProcessing(transferDto);
    }
}
