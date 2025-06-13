package ru.practicum.bank.blocker.models;

public class Check {
    Boolean blockResult;
    String description;

    public Boolean getBlockResult() {
        return blockResult;
    }

    public Check setBlockResult(Boolean blockResult) {
        this.blockResult = blockResult;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Check setDescription(String description) {
        this.description = description;
        return this;
    }
}
