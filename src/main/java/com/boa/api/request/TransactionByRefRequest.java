package com.boa.api.request;

public class TransactionByRefRequest {
    private String referencebancaire;
    private String sens;


    public TransactionByRefRequest() {
    }

    public TransactionByRefRequest(String referencebancaire, String sens) {
        this.referencebancaire = referencebancaire;
        this.sens = sens;
    }

    public String getReferencebancaire() {
        return this.referencebancaire;
    }

    public void setReferencebancaire(String referencebancaire) {
        this.referencebancaire = referencebancaire;
    }

    public String getSens() {
        return this.sens;
    }

    public void setSens(String sens) {
        this.sens = sens;
    }

    public TransactionByRefRequest referencebancaire(String referencebancaire) {
        this.referencebancaire = referencebancaire;
        return this;
    }

    public TransactionByRefRequest sens(String sens) {
        this.sens = sens;
        return this;
    }

    @Override
    public String toString() {
        return "{" +
            " referencebancaire='" + getReferencebancaire() + "'" +
            ", sens='" + getSens() + "'" +
            "}";
    }
    

}