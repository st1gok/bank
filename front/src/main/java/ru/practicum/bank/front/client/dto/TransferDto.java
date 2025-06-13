package ru.practicum.bank.front.client.dto;

public class TransferDto {
    private Long fromAccount;
    private  UserData sender;
    private  UserData receiver;
    private Long toAccount;
    private Double amount;

    public TransferDto() {
    }

    public TransferDto(Long fromAccount, UserData sender, UserData receiver, Long toAccount, Double amount) {
        this.fromAccount = fromAccount;
        this.sender = sender;
        this.receiver = receiver;
        this.toAccount = toAccount;
        this.amount = amount;
    }

    public TransferDto(Long fromAccount, UserData user, Long toAccount, Double amount) {
        this.fromAccount = fromAccount;
        this.sender = user;
        this.toAccount = toAccount;
        this.amount = amount;
    }

    public UserData getSender() {
        return sender;
    }

    public TransferDto setSender(UserData sender) {
        this.sender = sender;
        return this;
    }

    public UserData getReceiver() {
        return receiver;
    }

    public TransferDto setReceiver(UserData receiver) {
        this.receiver = receiver;
        return this;
    }

    public Long getFromAccount() {
        return fromAccount;
    }

    public Long getToAccount() {
        return toAccount;
    }

    public Double getAmount() {
        return amount;
    }

    public TransferDto setFromAccount(Long fromAccount) {
        this.fromAccount = fromAccount;
        return this;
    }

    public TransferDto setToAccount(Long toAccount) {
        this.toAccount = toAccount;
        return this;
    }

    public TransferDto setAmount(Double amount) {
        this.amount = amount;
        return this;
    }
}
