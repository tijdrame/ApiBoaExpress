package com.boa.api.response;

public class Transaction {
    
    private String numerotransaction;
    private String datedepot;
    private Double montant;
    private String deviseenvoi;
    private Double montantaupaiement;
    private String devisedestination;
    private Double frais;
    private Double tauxdechange;
    private Double tva;
    private Double autretaxe1;
    private Double autretaxe2;
    private Double autretaxe3;
    private String idemetteur;
    private String idrecepteur;
    private Integer typetransaction;
    private String paysenvoi;
    private String paysdestination;


    public Transaction() {
    }

    public Transaction(String numerotransaction, String datedepot, Double montant, String deviseenvoi, Double montantaupaiement, String devisedestination, Double frais, Double tauxdechange, Double tva, Double autretaxe1, Double autretaxe2, Double autretaxe3, String idemetteur, String idrecepteur, Integer typetransaction, String paysenvoi, String paysdestination) {
        this.numerotransaction = numerotransaction;
        this.datedepot = datedepot;
        this.montant = montant;
        this.deviseenvoi = deviseenvoi;
        this.montantaupaiement = montantaupaiement;
        this.devisedestination = devisedestination;
        this.frais = frais;
        this.tauxdechange = tauxdechange;
        this.tva = tva;
        this.autretaxe1 = autretaxe1;
        this.autretaxe2 = autretaxe2;
        this.autretaxe3 = autretaxe3;
        this.idemetteur = idemetteur;
        this.idrecepteur = idrecepteur;
        this.typetransaction = typetransaction;
        this.paysenvoi = paysenvoi;
        this.paysdestination = paysdestination;
    }

    public String getNumerotransaction() {
        return this.numerotransaction;
    }

    public void setNumerotransaction(String numerotransaction) {
        this.numerotransaction = numerotransaction;
    }

    public String getDatedepot() {
        return this.datedepot;
    }

    public void setDatedepot(String datedepot) {
        this.datedepot = datedepot;
    }

    public Double getMontant() {
        return this.montant;
    }

    public void setMontant(Double montant) {
        this.montant = montant;
    }

    public String getDeviseenvoi() {
        return this.deviseenvoi;
    }

    public void setDeviseenvoi(String deviseenvoi) {
        this.deviseenvoi = deviseenvoi;
    }

    public Double getMontantaupaiement() {
        return this.montantaupaiement;
    }

    public void setMontantaupaiement(Double montantaupaiement) {
        this.montantaupaiement = montantaupaiement;
    }

    public String getDevisedestination() {
        return this.devisedestination;
    }

    public void setDevisedestination(String devisedestination) {
        this.devisedestination = devisedestination;
    }

    public Double getFrais() {
        return this.frais;
    }

    public void setFrais(Double frais) {
        this.frais = frais;
    }

    public Double getTauxdechange() {
        return this.tauxdechange;
    }

    public void setTauxdechange(Double tauxdechange) {
        this.tauxdechange = tauxdechange;
    }

    public Double getTva() {
        return this.tva;
    }

    public void setTva(Double tva) {
        this.tva = tva;
    }

    public Double getAutretaxe1() {
        return this.autretaxe1;
    }

    public void setAutretaxe1(Double autretaxe1) {
        this.autretaxe1 = autretaxe1;
    }

    public Double getAutretaxe2() {
        return this.autretaxe2;
    }

    public void setAutretaxe2(Double autretaxe2) {
        this.autretaxe2 = autretaxe2;
    }

    public Double getAutretaxe3() {
        return this.autretaxe3;
    }

    public void setAutretaxe3(Double autretaxe3) {
        this.autretaxe3 = autretaxe3;
    }

    public String getIdemetteur() {
        return this.idemetteur;
    }

    public void setIdemetteur(String idemetteur) {
        this.idemetteur = idemetteur;
    }

    public String getIdrecepteur() {
        return this.idrecepteur;
    }

    public void setIdrecepteur(String idrecepteur) {
        this.idrecepteur = idrecepteur;
    }

    public Integer getTypetransaction() {
        return this.typetransaction;
    }

    public void setTypetransaction(Integer typetransaction) {
        this.typetransaction = typetransaction;
    }

    public String getPaysenvoi() {
        return this.paysenvoi;
    }

    public void setPaysenvoi(String paysenvoi) {
        this.paysenvoi = paysenvoi;
    }

    public String getPaysdestination() {
        return this.paysdestination;
    }

    public void setPaysdestination(String paysdestination) {
        this.paysdestination = paysdestination;
    }

    public Transaction numerotransaction(String numerotransaction) {
        this.numerotransaction = numerotransaction;
        return this;
    }

    public Transaction datedepot(String datedepot) {
        this.datedepot = datedepot;
        return this;
    }

    public Transaction montant(Double montant) {
        this.montant = montant;
        return this;
    }

    public Transaction deviseenvoi(String deviseenvoi) {
        this.deviseenvoi = deviseenvoi;
        return this;
    }

    public Transaction montantaupaiement(Double montantaupaiement) {
        this.montantaupaiement = montantaupaiement;
        return this;
    }

    public Transaction devisedestination(String devisedestination) {
        this.devisedestination = devisedestination;
        return this;
    }

    public Transaction frais(Double frais) {
        this.frais = frais;
        return this;
    }

    public Transaction tauxdechange(Double tauxdechange) {
        this.tauxdechange = tauxdechange;
        return this;
    }

    public Transaction tva(Double tva) {
        this.tva = tva;
        return this;
    }

    public Transaction autretaxe1(Double autretaxe1) {
        this.autretaxe1 = autretaxe1;
        return this;
    }

    public Transaction autretaxe2(Double autretaxe2) {
        this.autretaxe2 = autretaxe2;
        return this;
    }

    public Transaction autretaxe3(Double autretaxe3) {
        this.autretaxe3 = autretaxe3;
        return this;
    }

    public Transaction idemetteur(String idemetteur) {
        this.idemetteur = idemetteur;
        return this;
    }

    public Transaction idrecepteur(String idrecepteur) {
        this.idrecepteur = idrecepteur;
        return this;
    }

    public Transaction typetransaction(Integer typetransaction) {
        this.typetransaction = typetransaction;
        return this;
    }

    public Transaction paysenvoi(String paysenvoi) {
        this.paysenvoi = paysenvoi;
        return this;
    }

    public Transaction paysdestination(String paysdestination) {
        this.paysdestination = paysdestination;
        return this;
    }

    @Override
    public String toString() {
        return "{" +
            " numerotransaction='" + getNumerotransaction() + "'" +
            ", datedepot='" + getDatedepot() + "'" +
            ", montant='" + getMontant() + "'" +
            ", deviseenvoi='" + getDeviseenvoi() + "'" +
            ", montantaupaiement='" + getMontantaupaiement() + "'" +
            ", devisedestination='" + getDevisedestination() + "'" +
            ", frais='" + getFrais() + "'" +
            ", tauxdechange='" + getTauxdechange() + "'" +
            ", tva='" + getTva() + "'" +
            ", autretaxe1='" + getAutretaxe1() + "'" +
            ", autretaxe2='" + getAutretaxe2() + "'" +
            ", autretaxe3='" + getAutretaxe3() + "'" +
            ", idemetteur='" + getIdemetteur() + "'" +
            ", idrecepteur='" + getIdrecepteur() + "'" +
            ", typetransaction='" + getTypetransaction() + "'" +
            ", paysenvoi='" + getPaysenvoi() + "'" +
            ", paysdestination='" + getPaysdestination() + "'" +
            "}";
    }
    

}