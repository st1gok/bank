package ru.practicum.bank.transfer.service;

import ru.practicum.bank.transfer.models.OperationResult;
import ru.practicum.bank.transfer.models.TransferDto;

public interface TransferProccesingService {
    OperationResult transferProcessing(TransferDto transferDto) throws Exception;
}
