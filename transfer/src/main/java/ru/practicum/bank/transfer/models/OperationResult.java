package ru.practicum.bank.transfer.models;

public class OperationResult {
    private int status;
    private String errorMessage;

    public int getStatus() {
        return status;
    }

    public OperationResult setStatus(int status) {
        this.status = status;
        return this;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public OperationResult setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
        return this;
    }
}
