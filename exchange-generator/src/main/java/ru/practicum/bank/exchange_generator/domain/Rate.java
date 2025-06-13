package ru.practicum.bank.exchange_generator.domain;

public class Rate {
    private String title;
    private String name;
    private Double value;

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }

    public Rate setTitle(String title) {
        this.title = title;
        return this;
    }

    public Rate setName(String name) {
        this.name = name;
        return this;
    }

    public Double getValue() {
        return value;
    }

    public Rate setValue(Double value) {
        this.value = value;
        return this;
    }
}
