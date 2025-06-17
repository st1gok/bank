package ru.practicum.bank.cash.service;

import ru.practicum.bank.cash.models.CashDto;
import ru.practicum.bank.cash.models.OperationResult;

public interface CashProccesingService {
    OperationResult cashProcessing(CashDto cashDto) throws Exception;
}
