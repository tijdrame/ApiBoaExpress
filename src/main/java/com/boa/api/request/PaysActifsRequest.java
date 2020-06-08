package com.boa.api.request;

public class PaysActifsRequest {
    private String paysEnvoi;


    public PaysActifsRequest() {
    }

    public PaysActifsRequest(String paysEnvoi) {
        this.paysEnvoi = paysEnvoi;
    }

    public String getPaysEnvoi() {
        return this.paysEnvoi;
    }

    public void setPaysEnvoi(String paysEnvoi) {
        this.paysEnvoi = paysEnvoi;
    }

    public PaysActifsRequest paysEnvoi(String paysEnvoi) {
        this.paysEnvoi = paysEnvoi;
        return this;
    }

    @Override
    public String toString() {
        return "{" +
            " paysEnvoi='" + getPaysEnvoi() + "'" +
            "}";
    }
}