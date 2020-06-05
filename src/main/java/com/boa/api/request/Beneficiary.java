package com.boa.api.request;

public class Beneficiary {
    private String nom;
    private String prenom;
    private String telephoneport;


    public Beneficiary() {
    }

    public Beneficiary(String nom, String prenom, String telephoneport) {
        this.nom = nom;
        this.prenom = prenom;
        this.telephoneport = telephoneport;
    }

    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return this.prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getTelephoneport() {
        return this.telephoneport;
    }

    public void setTelephoneport(String telephoneport) {
        this.telephoneport = telephoneport;
    }

    public Beneficiary nom(String nom) {
        this.nom = nom;
        return this;
    }

    public Beneficiary prenom(String prenom) {
        this.prenom = prenom;
        return this;
    }

    public Beneficiary telephoneport(String telephoneport) {
        this.telephoneport = telephoneport;
        return this;
    }

    @Override
    public String toString() {
        return "{" +
            " nom='" + getNom() + "'" +
            ", prenom='" + getPrenom() + "'" +
            ", telephoneport='" + getTelephoneport() + "'" +
            "}";
    }
}