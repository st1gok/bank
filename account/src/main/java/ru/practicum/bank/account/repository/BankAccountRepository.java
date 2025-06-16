package ru.practicum.bank.account.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.practicum.bank.account.domain.BankAccount;
import ru.practicum.bank.account.domain.User;

import java.util.Optional;

@Repository
public interface BankAccountRepository extends CrudRepository<BankAccount, Long> {
    Optional<BankAccount> findByAccount_IdAndCurrency(Long accountId, String currency);
}
