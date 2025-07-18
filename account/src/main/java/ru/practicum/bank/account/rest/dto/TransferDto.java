package ru.practicum.bank.account.rest.dto;

public class TransferDto {
    private Long fromAccount;
    private  AccountDto sender;
    private  AccountDto receiver;
    private Long toAccount;
    private Double amount;
    private Double destAmount;

    public TransferDto() {
    }

    public TransferDto(Long fromAccount, AccountDto sender, AccountDto receiver, Long toAccount, Double amount) {
        this.fromAccount = fromAccount;
        this.sender = sender;
        this.receiver = receiver;
        this.toAccount = toAccount;
        this.amount = amount;
    }

    public TransferDto(Long fromAccount, AccountDto user, Long toAccount, Double amount) {
        this.fromAccount = fromAccount;
        this.sender = user;
        this.toAccount = toAccount;
        this.amount = amount;
    }

    public Double getDestAmount() {
        return destAmount;
    }

    public TransferDto setDestAmount(Double destAmount) {
        this.destAmount = destAmount;
        return this;
    }

    public AccountDto getSender() {
        return sender;
    }

    public TransferDto setSender(AccountDto sender) {
        this.sender = sender;
        return this;
    }

    public AccountDto getReceiver() {
        return receiver;
    }

    public TransferDto setReceiver(AccountDto receiver) {
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
