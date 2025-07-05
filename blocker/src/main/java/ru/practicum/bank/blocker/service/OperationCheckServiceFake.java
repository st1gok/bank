package ru.practicum.bank.blocker.service;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Service;
import ru.practicum.bank.blocker.models.CashDto;
import ru.practicum.bank.blocker.models.Check;
import ru.practicum.bank.blocker.models.TransferDto;

import java.util.Random;

@Service
public class OperationCheckServiceFake implements OperationCheckService {

    private final Random random = new Random();
    private final MeterRegistry meterRegistry;

    public OperationCheckServiceFake(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }

    @Override
    public Check checkOperation(CashDto cashDto) {
        Boolean result = random.nextBoolean();
        if (!result) {
            meterRegistry.counter("blocks_total",
                    "operation", "cash",
                    "account_from", String.valueOf(cashDto.getAccount())
            ).increment();
        }
        return new Check().setBlockResult(result);
    }

    @Override
    public Check checkOperation(TransferDto transferDto) {
        Boolean result = random.nextBoolean();
        if (!result) {
            meterRegistry.counter("blocks_total",
                    "operation", "transfer",
                    "account_from", String.valueOf(transferDto.getFromAccount()),
                    "account_to", String.valueOf(transferDto.getToAccount()))
                    .increment();
        }
        return new Check().setBlockResult(result);
    }
}
