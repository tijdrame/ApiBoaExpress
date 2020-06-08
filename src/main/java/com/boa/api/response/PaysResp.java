package com.boa.api.response;

public class PaysResp {
    
    private String code, nom;

    public PaysResp() {
    }

    public PaysResp(String code, String nom) {
        this.code = code;
        this.nom = nom;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public PaysResp code(String code) {
        this.code = code;
        return this;
    }

    public PaysResp nom(String nom) {
        this.nom = nom;
        return this;
    }

    @Override
    public String toString() {
        return "{" +
            " code='" + getCode() + "'" +
            ", nom='" + getNom() + "'" +
            "}";
    }

}