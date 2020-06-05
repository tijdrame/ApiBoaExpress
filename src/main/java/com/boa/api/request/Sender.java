package com.boa.api.request;

public class Sender {
    private String nom;
    private String prenom;
    private String telephoneport;
    private String numerocompte;

    public Sender() {
    }

    public Sender(String nom, String prenom, String telephoneport, String numerocompte) {
        this.nom = nom;
        this.prenom = prenom;
        this.telephoneport = telephoneport;
        this.numerocompte = numerocompte;
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

    public String getNumerocompte() {
        return this.numerocompte;
    }

    public void setNumerocompte(String numerocompte) {
        this.numerocompte = numerocompte;
    }

    public Sender nom(String nom) {
        this.nom = nom;
        return this;
    }

    public Sender prenom(String prenom) {
        this.prenom = prenom;
        return this;
    }

    public Sender telephoneport(String telephoneport) {
        this.telephoneport = telephoneport;
        return this;
    }

    public Sender numerocompte(String numerocompte) {
        this.numerocompte = numerocompte;
        return this;
    }

    @Override
    public String toString() {
        return "{" +
            " nom='" + getNom() + "'" +
            ", prenom='" + getPrenom() + "'" +
            ", telephoneport='" + getTelephoneport() + "'" +
            ", numerocompte='" + getNumerocompte() + "'" +
            "}";
    }

}