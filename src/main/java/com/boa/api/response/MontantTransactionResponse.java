package com.boa.api.response;

public class MontantTransactionResponse extends GenericResponse {
    private Double montant, montantFrais;

    public MontantTransactionResponse() {
    }

    public MontantTransactionResponse(Double montant, Double montantFrais) {
        this.montant = montant;
        this.montantFrais = montantFrais;
    }

    public Double getMontant() {
        return this.montant;
    }

    public void setMontant(Double montant) {
        this.montant = montant;
    }

    public Double getMontantFrais() {
        return this.montantFrais;
    }

    public void setMontantFrais(Double montantFrais) {
        this.montantFrais = montantFrais;
    }

    public MontantTransactionResponse montant(Double montant) {
        this.montant = montant;
        return this;
    }

    public MontantTransactionResponse montantFrais(Double montantFrais) {
        this.montantFrais = montantFrais;
        return this;
    }

    @Override
    public String toString() {
        return "{" +
            " montant='" + getMontant() + "'" +
            ", montantFrais='" + getMontantFrais() + "'" +
            "}";
    }

}