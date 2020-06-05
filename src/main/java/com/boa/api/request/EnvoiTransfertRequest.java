package com.boa.api.request;

public class EnvoiTransfertRequest {
    
    private Transaction transaction;

    public EnvoiTransfertRequest() {
    }

    public EnvoiTransfertRequest(Transaction transaction) {
        this.transaction = transaction;
    }

    public Transaction getTransaction() {
        return this.transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public EnvoiTransfertRequest transaction(Transaction transaction) {
        this.transaction = transaction;
        return this;
    }
    
    @Override
    public String toString() {
        return "{" +
            " transaction='" + getTransaction() + "'" +
            "}";
    }

}