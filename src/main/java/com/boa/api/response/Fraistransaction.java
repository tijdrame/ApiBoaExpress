package com.boa.api.response;

public class Fraistransaction {
    
    private String codepartenaire;
    private String paysenvoi;
    private String deviseenvoi;
    private String paysdestination;
    private String devisedestination;
    private String typetransaction;
    private String montant;
    private String frais;
    private String tva;
    private String autretaxe1;
    private String autretaxe2;
    private String autretaxe3;

    public Fraistransaction() {
    }

    public Fraistransaction(String codepartenaire, String paysenvoi, String deviseenvoi, String paysdestination, String devisedestination, String typetransaction, String montant, String frais, String tva, String autretaxe1, String autretaxe2, String autretaxe3) {
        this.codepartenaire = codepartenaire;
        this.paysenvoi = paysenvoi;
        this.deviseenvoi = deviseenvoi;
        this.paysdestination = paysdestination;
        this.devisedestination = devisedestination;
        this.typetransaction = typetransaction;
        this.montant = montant;
        this.frais = frais;
        this.tva = tva;
        this.autretaxe1 = autretaxe1;
        this.autretaxe2 = autretaxe2;
        this.autretaxe3 = autretaxe3;
    }

    public String getCodepartenaire() {
        return this.codepartenaire;
    }

    public void setCodepartenaire(String codepartenaire) {
        this.codepartenaire = codepartenaire;
    }

    public String getPaysenvoi() {
        return this.paysenvoi;
    }

    public void setPaysenvoi(String paysenvoi) {
        this.paysenvoi = paysenvoi;
    }

    public String getDeviseenvoi() {
        return this.deviseenvoi;
    }

    public void setDeviseenvoi(String deviseenvoi) {
        this.deviseenvoi = deviseenvoi;
    }

    public String getPaysdestination() {
        return this.paysdestination;
    }

    public void setPaysdestination(String paysdestination) {
        this.paysdestination = paysdestination;
    }

    public String getDevisedestination() {
        return this.devisedestination;
    }

    public void setDevisedestination(String devisedestination) {
        this.devisedestination = devisedestination;
    }

    public String getTypetransaction() {
        return this.typetransaction;
    }

    public void setTypetransaction(String typetransaction) {
        this.typetransaction = typetransaction;
    }

    public String getMontant() {
        return this.montant;
    }

    public void setMontant(String montant) {
        this.montant = montant;
    }

    public String getFrais() {
        return this.frais;
    }

    public void setFrais(String frais) {
        this.frais = frais;
    }

    public String getTva() {
        return this.tva;
    }

    public void setTva(String tva) {
        this.tva = tva;
    }

    public String getAutretaxe1() {
        return this.autretaxe1;
    }

    public void setAutretaxe1(String autretaxe1) {
        this.autretaxe1 = autretaxe1;
    }

    public String getAutretaxe2() {
        return this.autretaxe2;
    }

    public void setAutretaxe2(String autretaxe2) {
        this.autretaxe2 = autretaxe2;
    }

    public String getAutretaxe3() {
        return this.autretaxe3;
    }

    public void setAutretaxe3(String autretaxe3) {
        this.autretaxe3 = autretaxe3;
    }

    public Fraistransaction codepartenaire(String codepartenaire) {
        this.codepartenaire = codepartenaire;
        return this;
    }

    public Fraistransaction paysenvoi(String paysenvoi) {
        this.paysenvoi = paysenvoi;
        return this;
    }

    public Fraistransaction deviseenvoi(String deviseenvoi) {
        this.deviseenvoi = deviseenvoi;
        return this;
    }

    public Fraistransaction paysdestination(String paysdestination) {
        this.paysdestination = paysdestination;
        return this;
    }

    public Fraistransaction devisedestination(String devisedestination) {
        this.devisedestination = devisedestination;
        return this;
    }

    public Fraistransaction typetransaction(String typetransaction) {
        this.typetransaction = typetransaction;
        return this;
    }

    public Fraistransaction montant(String montant) {
        this.montant = montant;
        return this;
    }

    public Fraistransaction frais(String frais) {
        this.frais = frais;
        return this;
    }

    public Fraistransaction tva(String tva) {
        this.tva = tva;
        return this;
    }

    public Fraistransaction autretaxe1(String autretaxe1) {
        this.autretaxe1 = autretaxe1;
        return this;
    }

    public Fraistransaction autretaxe2(String autretaxe2) {
        this.autretaxe2 = autretaxe2;
        return this;
    }

    public Fraistransaction autretaxe3(String autretaxe3) {
        this.autretaxe3 = autretaxe3;
        return this;
    }

    @Override
    public String toString() {
        return "{" +
            " codepartenaire='" + getCodepartenaire() + "'" +
            ", paysenvoi='" + getPaysenvoi() + "'" +
            ", deviseenvoi='" + getDeviseenvoi() + "'" +
            ", paysdestination='" + getPaysdestination() + "'" +
            ", devisedestination='" + getDevisedestination() + "'" +
            ", typetransaction='" + getTypetransaction() + "'" +
            ", montant='" + getMontant() + "'" +
            ", frais='" + getFrais() + "'" +
            ", tva='" + getTva() + "'" +
            ", autretaxe1='" + getAutretaxe1() + "'" +
            ", autretaxe2='" + getAutretaxe2() + "'" +
            ", autretaxe3='" + getAutretaxe3() + "'" +
            "}";
    }

}