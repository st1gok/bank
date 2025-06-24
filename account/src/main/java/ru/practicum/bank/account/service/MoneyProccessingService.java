package ru.practicum.bank.account.service;

import ru.practicum.bank.account.rest.dto.CashDto;
import ru.practicum.bank.account.rest.dto.TransferDto;

public interface MoneyProccessingService {

    void proccessing(TransferDto transferDto);

    void proccessing(CashDto cashDto);
}
