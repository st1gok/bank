package ru.practicum.bank.transfer.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import ru.practicum.bank.transfer.client.*;
import ru.practicum.bank.transfer.models.*;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class TransferProccesingServiceDefault implements TransferProccesingService {

    private final AccountClient accountClient;
    private final BlockerClient blockerClient;
    private final NotificationClient notificationClient;
    private final ExchangeClient exchangeClient;
    private final ObjectMapper objectMapper;
    private final MeterRegistry meterRegistry;

    public TransferProccesingServiceDefault(AccountClient accountClient,
                                            BlockerClient blockerClient,
                                            NotificationClient notificationClient,
                                            ExchangeClient exchangeClient,
                                            ObjectMapper objectMapper,
                                            MeterRegistry meterRegistry) {
        this.accountClient = accountClient;
        this.blockerClient = blockerClient;
        this.notificationClient = notificationClient;
        this.exchangeClient = exchangeClient;
        this.objectMapper = objectMapper;
        this.meterRegistry = meterRegistry;
    }

    @Override
    public OperationResult transferProcessing(TransferDto transferDto) throws Exception {
        Check check = blockerClient.checkOperation(transferDto);
        String currentCurrency = accountClient.getBankAccountCurrency(transferDto.getFromAccount());
        String destCurrency = accountClient.getBankAccountCurrency(transferDto.getToAccount());
        transferDto.setDestAmount(transferDto.getAmount());
        if (!currentCurrency.equals(destCurrency)) {
            var value = convertValue(currentCurrency, destCurrency, transferDto.getAmount());
            transferDto.setDestAmount(value);
        }
        if (check.getBlockResult()) {
            var recipient = new Message.Recipient().setEmail(transferDto.getSender().getEmail());
            var message = new Message().setRecipient(recipient)
                    .setCaption("Операция была заблокирована")
                    .setMessage("Операция показалась подозрительной");
            notificationClient.notify(message);
            meterRegistry.counter("transfer_failure_total",
                            "account_from", String.valueOf(transferDto.getFromAccount()),
                            "account_to", String.valueOf(transferDto.getToAccount()))
                    .increment();
            return new OperationResult().setErrorMessage("Операция была заблокирована").setStatus(400);
        } else {
            try {
                OperationResult result = accountClient.transfer(transferDto);
                var recipient = new Message.Recipient().setEmail(transferDto.getReceiver().getEmail());
                var message = new Message().setRecipient(recipient)
                        .setCaption("Пополнение баланса счета")
                        .setMessage("Счет успешно пополнен");
                notificationClient.notify(message);
                return result;
            } catch (HttpClientErrorException e) {
                meterRegistry.counter("transfer_failure_total",
                                "account_from", String.valueOf(transferDto.getFromAccount()),
                                "account_to", String.valueOf(transferDto.getToAccount()))
                        .increment();
                return objectMapper.readValue(e.getResponseBodyAsString(), OperationResult.class);
            }
        }
    }

    private Double convertValue(String currentCurrency, String destCurrency, Double amount) {
        Map<String, Rate> rates = exchangeClient.getRates().stream().collect(Collectors.toMap(Rate::getName, Function.identity()));
        var value = amount;
        if (!currentCurrency.equals("RUB")) {
            value = value * rates.get(currentCurrency).getValue();
        }
        value = value / rates.get(destCurrency).getValue();
        return value;
    }
}
