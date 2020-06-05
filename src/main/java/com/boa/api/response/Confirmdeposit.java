package com.boa.api.response;

public class Confirmdeposit {
    private String codepartenaire;
    private String numerotransaction;
    private Double montant;
    private Double montantaupaiement;
    private String datetransaction;
    private String telephoneport;

    public Confirmdeposit() {
    }

    public Confirmdeposit(String codepartenaire, String numerotransaction, Double montant, Double montantaupaiement, String datetransaction, String telephoneport) {
        this.codepartenaire = codepartenaire;
        this.numerotransaction = numerotransaction;
        this.montant = montant;
        this.montantaupaiement = montantaupaiement;
        this.datetransaction = datetransaction;
        this.telephoneport = telephoneport;
    }

    public String getCodepartenaire() {
        return this.codepartenaire;
    }

    public void setCodepartenaire(String codepartenaire) {
        this.codepartenaire = codepartenaire;
    }

    public String getNumerotransaction() {
        return this.numerotransaction;
    }

    public void setNumerotransaction(String numerotransaction) {
        this.numerotransaction = numerotransaction;
    }

    public Double getMontant() {
        return this.montant;
    }

    public void setMontant(Double montant) {
        this.montant = montant;
    }

    public Double getMontantaupaiement() {
        return this.montantaupaiement;
    }

    public void setMontantaupaiement(Double montantaupaiement) {
        this.montantaupaiement = montantaupaiement;
    }

    public String getDatetransaction() {
        return this.datetransaction;
    }

    public void setDatetransaction(String datetransaction) {
        this.datetransaction = datetransaction;
    }

    public String getTelephoneport() {
        return this.telephoneport;
    }

    public void setTelephoneport(String telephoneport) {
        this.telephoneport = telephoneport;
    }

    public Confirmdeposit codepartenaire(String codepartenaire) {
        this.codepartenaire = codepartenaire;
        return this;
    }

    public Confirmdeposit numerotransaction(String numerotransaction) {
        this.numerotransaction = numerotransaction;
        return this;
    }

    public Confirmdeposit montant(Double montant) {
        this.montant = montant;
        return this;
    }

    public Confirmdeposit montantaupaiement(Double montantaupaiement) {
        this.montantaupaiement = montantaupaiement;
        return this;
    }

    public Confirmdeposit datetransaction(String datetransaction) {
        this.datetransaction = datetransaction;
        return this;
    }

    public Confirmdeposit telephoneport(String telephoneport) {
        this.telephoneport = telephoneport;
        return this;
    }

    @Override
    public String toString() {
        return "{" +
            " codepartenaire='" + getCodepartenaire() + "'" +
            ", numerotransaction='" + getNumerotransaction() + "'" +
            ", montant='" + getMontant() + "'" +
            ", montantaupaiement='" + getMontantaupaiement() + "'" +
            ", datetransaction='" + getDatetransaction() + "'" +
            ", telephoneport='" + getTelephoneport() + "'" +
            "}";
    }

}