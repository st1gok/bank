package ru.practicum.bank.account.rest.mappers;

import org.mapstruct.Mapper;
import ru.practicum.bank.account.domain.Account;
import ru.practicum.bank.account.rest.dto.AccountDto;

@Mapper(componentModel = "spring", imports = BankAccountMapper.class)
public interface AccountMapper extends EntityMapper<AccountDto, Account> {}

