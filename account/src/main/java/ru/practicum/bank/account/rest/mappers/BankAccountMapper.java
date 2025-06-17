package ru.practicum.bank.account.rest.mappers;

import org.mapstruct.Mapper;
import ru.practicum.bank.account.domain.BankAccount;
import ru.practicum.bank.account.rest.dto.BankAccountDto;

@Mapper(componentModel = "spring")
public interface BankAccountMapper extends EntityMapper<BankAccountDto, BankAccount> {}

