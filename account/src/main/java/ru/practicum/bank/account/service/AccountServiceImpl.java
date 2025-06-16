package ru.practicum.bank.account.service;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.practicum.bank.account.domain.BankAccount;
import ru.practicum.bank.account.repository.BankAccountRepository;
import ru.practicum.bank.account.repository.UserRepository;
import ru.practicum.bank.account.rest.dto.BankAccountDto;
import ru.practicum.bank.account.rest.mappers.BankAccountMapper;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class AccountServiceImpl implements AccountService {

    UserRepository userRepository;
    BankAccountRepository bankAccountRepository;
    private final BankAccountMapper bankAccountMapper;

    public AccountServiceImpl(UserRepository userRepository, BankAccountMapper bankAccountMapper, BankAccountRepository bankAccountRepository) {
        this.userRepository = userRepository;
        this.bankAccountMapper = bankAccountMapper;
        this.bankAccountRepository = bankAccountRepository;
    }

    @Override
    public List<BankAccountDto> getAccounts(String login) {
        var user = userRepository.findByLogin(login).orElseThrow(() -> new UsernameNotFoundException("User not found"));
       return  bankAccountMapper.toDto(user.getAccount().getBankAccounts());
    }

    @Override
    public void createAccount(String login, BankAccountDto account) {
        var user = userRepository.findByLogin(login).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        var accounts = user.getAccount().getBankAccounts();
        accounts.add(new BankAccount().setCurrency(account.getCurrency()).setAccount(user.getAccount()));
        userRepository.save(user);
    }

    @Override
    public void delete(Long accountId) {
        bankAccountRepository.deleteById(accountId);
    }

    @Override
    public Optional<BankAccountDto> getAccount(long id) {
        return bankAccountRepository.findById(id).map(bankAccount -> bankAccountMapper.toDto(bankAccount));
    }
}
