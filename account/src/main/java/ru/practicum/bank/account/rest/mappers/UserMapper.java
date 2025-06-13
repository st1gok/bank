package ru.practicum.bank.account.rest.mappers;

import org.mapstruct.Mapper;
import ru.practicum.bank.account.domain.User;
import ru.practicum.bank.account.rest.dto.UserDto;

@Mapper(componentModel = "spring")
public interface UserMapper extends EntityMapper<UserDto, User> {}

