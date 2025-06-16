package ru.practicum.bank.blocker.service;

import ru.practicum.bank.blocker.models.CashDto;
import ru.practicum.bank.blocker.models.Check;
import ru.practicum.bank.blocker.models.TransferDto;

public interface OperationCheckService {
    Check checkOperation(CashDto cashDto);
    Check checkOperation(TransferDto transferDto);
}
