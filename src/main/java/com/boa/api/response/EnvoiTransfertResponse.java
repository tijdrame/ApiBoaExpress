package com.boa.api.response;

public class EnvoiTransfertResponse extends GenericResponse{
    
    private String detailsop;
    private Transaction transaction;

    public EnvoiTransfertResponse() {
    }

    public EnvoiTransfertResponse(String detailsop, Transaction transaction) {
        this.detailsop = detailsop;
        this.transaction = transaction;
    }

    
    public String getDetailsop() {
        return this.detailsop;
    }

    public void setDetailsop(String detailsop) {
        this.detailsop = detailsop;
    }

    public Transaction getTransaction() {
        return this.transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public EnvoiTransfertResponse detailsop(String detailsop) {
        this.detailsop = detailsop;
        return this;
    }

    public EnvoiTransfertResponse transaction(Transaction transaction) {
        this.transaction = transaction;
        return this;
    }

    @Override
    public String toString() {
        return "{" +
        " generic=='" + super.toString() +
            ", detailsop='" + getDetailsop() + "'" +
            ", transaction='" + getTransaction() + "'" +
            "}";
    }

}