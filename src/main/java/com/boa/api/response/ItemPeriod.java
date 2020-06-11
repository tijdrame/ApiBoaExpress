package com.boa.api.response;

public class ItemPeriod {
    private String country;
    private Double amount, fees;

    private String month;
    private Integer year;

    public ItemPeriod() {
    }

    public ItemPeriod(String country, Double amount, Double fees, String month, Integer year) {
        this.country = country;
        this.amount = amount;
        this.fees = fees;
        this.month = month;
        this.year = year;
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

    public String getMonth() {
        return this.month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public Integer getYear() {
        return this.year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public ItemPeriod country(String country) {
        this.country = country;
        return this;
    }

    public ItemPeriod amount(Double amount) {
        this.amount = amount;
        return this;
    }

    public ItemPeriod fees(Double fees) {
        this.fees = fees;
        return this;
    }

    public ItemPeriod month(String month) {
        this.month = month;
        return this;
    }

    public ItemPeriod year(Integer year) {
        this.year = year;
        return this;
    }

    @Override
    public String toString() {
        return "{" +
            " country='" + getCountry() + "'" +
            ", amount='" + getAmount() + "'" +
            ", fees='" + getFees() + "'" +
            ", month='" + getMonth() + "'" +
            ", year='" + getYear() + "'" +
            "}";
    }

}