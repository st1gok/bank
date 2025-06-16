package ru.practicum.bank.account.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import ru.practicum.bank.account.repository.BankAccountRepository;
import ru.practicum.bank.account.rest.dto.CashAction;
import ru.practicum.bank.account.rest.dto.CashDto;
import ru.practicum.bank.account.rest.dto.TransferDto;

@Service
public class MoneyProccessingService {

    private final BankAccountRepository bankAccountRepository;

    public MoneyProccessingService(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    @Transactional
    public void proccessing(TransferDto transferDto) {
        var from = bankAccountRepository.findById(transferDto.getFromAccount()).orElseThrow(() -> new RuntimeException("Счет не найден"));
        var to = bankAccountRepository.findById(transferDto.getToAccount()).orElseThrow(() -> new RuntimeException("Счет не найден"));
        if (from.getValue().compareTo(transferDto.getAmount()) < 0) {
            throw new RuntimeException("Не достаточно средств");
        }
        if (from.equals(to)) {
            return;
        }
        from.setValue(from.getValue() - transferDto.getAmount());
        to.setValue(to.getValue() + transferDto.getDestAmount());
        bankAccountRepository.save(from);
        bankAccountRepository.save(to);
    }

    @Transactional
    public void proccessing(CashDto cashDto) {
       var bankAccount = bankAccountRepository.findById(cashDto.getAccount()).orElseThrow(() -> new RuntimeException("Счет не найден"));
       var balance = bankAccount.getValue();
       if (CashAction.GET.equals(cashDto.getAction())) {
           if (balance.compareTo(cashDto.getAmount()) < 0) {
               throw new RuntimeException("Не достаточно средств");
           }
           bankAccount.setValue(balance - cashDto.getAmount());
       } else {
           bankAccount.setValue(balance + cashDto.getAmount());
       }
        bankAccountRepository.save(bankAccount);
    }
}
