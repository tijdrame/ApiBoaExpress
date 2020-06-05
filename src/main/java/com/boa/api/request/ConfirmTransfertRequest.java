package com.boa.api.request;

public class ConfirmTransfertRequest {
    
    private String numerotransaction, referencebancaire;

    public ConfirmTransfertRequest() {
    }

    public ConfirmTransfertRequest(String numerotransaction, String referencebancaire) {
        this.numerotransaction = numerotransaction;
        this.referencebancaire = referencebancaire;
    }

    public String getNumerotransaction() {
        return this.numerotransaction;
    }

    public void setNumerotransaction(String numerotransaction) {
        this.numerotransaction = numerotransaction;
    }

    public String getReferencebancaire() {
        return this.referencebancaire;
    }

    public void setReferencebancaire(String referencebancaire) {
        this.referencebancaire = referencebancaire;
    }

    public ConfirmTransfertRequest numerotransaction(String numerotransaction) {
        this.numerotransaction = numerotransaction;
        return this;
    }

    public ConfirmTransfertRequest referencebancaire(String referencebancaire) {
        this.referencebancaire = referencebancaire;
        return this;
    }

    @Override
    public String toString() {
        return "{" +
            " numerotransaction='" + getNumerotransaction() + "'" +
            ", referencebancaire='" + getReferencebancaire() + "'" +
            "}";
    }

     
}