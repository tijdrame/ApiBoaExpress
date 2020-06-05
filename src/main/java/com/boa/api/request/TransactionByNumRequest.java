package com.boa.api.request;

public class TransactionByNumRequest {
    private String numerotransaction;


    public TransactionByNumRequest() {
    }

    public TransactionByNumRequest(String numerotransaction) {
        this.numerotransaction = numerotransaction;
    }

    public String getNumerotransaction() {
        return this.numerotransaction;
    }

    public void setNumerotransaction(String numerotransaction) {
        this.numerotransaction = numerotransaction;
    }

    public TransactionByNumRequest numerotransaction(String numerotransaction) {
        this.numerotransaction = numerotransaction;
        return this;
    }

    @Override
    public String toString() {
        return "{" +
            " numerotransaction='" + getNumerotransaction() + "'" +
            "}";
    }
    
}