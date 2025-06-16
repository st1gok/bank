package ru.practicum.bank.blocker.service;

import org.springframework.stereotype.Service;
import ru.practicum.bank.blocker.models.CashDto;
import ru.practicum.bank.blocker.models.Check;
import ru.practicum.bank.blocker.models.TransferDto;

import java.util.Random;

@Service
public class OperationCheckServiceFake implements OperationCheckService {

    Random random = new Random();

    @Override
    public Check checkOperation(CashDto cashDto) {
        return new Check().setBlockResult(random.nextBoolean());
    }

    @Override
    public Check checkOperation(TransferDto transferDto) {
        return new Check().setBlockResult(random.nextBoolean());
    }
}
