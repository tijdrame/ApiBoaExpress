package com.boa.api.response;

public class ItemCountry {
    private String country;
    private Double amount, fees;

    public ItemCountry() {
    }

    public ItemCountry(String country, Double amount, Double fees) {
        this.country = country;
        this.amount = amount;
        this.fees = fees;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Double getAmount() {
        return this.amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getFees() {
        return this.fees;
    }

    public void setFees(Double fees) {
        this.fees = fees;
    }

    public ItemCountry country(String country) {
        this.country = country;
        return this;
    }

    public ItemCountry amount(Double amount) {
        this.amount = amount;
        return this;
    }

    public ItemCountry fees(Double fees) {
        this.fees = fees;
        return this;
    }

    @Override
    public String toString() {
        return "{" +
            " country='" + getCountry() + "'" +
            ", amount='" + getAmount() + "'" +
            ", fees='" + getFees() + "'" +
            "}";
    }

}