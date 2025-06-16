package ru.practicum.bank.blocker.models;

public class TransferDto {
    private Long fromAccount;
    private Long toAccount;
    private Double amount;

    public TransferDto() {
    }

    public TransferDto(Long fromAccount, Long toAccount, Double amount) {
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.amount = amount;
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
