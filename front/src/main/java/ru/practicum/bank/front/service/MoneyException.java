package ru.practicum.bank.front.service;

public class MoneyException extends RuntimeException {
    public MoneyException(String message) {
        super(message);
    }
}
