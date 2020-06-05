package com.boa.api.request;

public class CalculfraisTransfertRequest {
    
    private Double montant;
    private String paysenvoi;
    private String paysdestination;
    private Integer typetransaction;

    public CalculfraisTransfertRequest() {
    }

    public CalculfraisTransfertRequest(Double montant, String paysenvoi, String paysdestination, Integer typetransaction) {
        this.montant = montant;
        this.paysenvoi = paysenvoi;
        this.paysdestination = paysdestination;
        this.typetransaction = typetransaction;
    }

    public Double getMontant() {
        return this.montant;
    }

    public void setMontant(Double montant) {
        this.montant = montant;
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

    public Integer getTypetransaction() {
        return this.typetransaction;
    }

    public void setTypetransaction(Integer typetransaction) {
        this.typetransaction = typetransaction;
    }

    public CalculfraisTransfertRequest montant(Double montant) {
        this.montant = montant;
        return this;
    }

    public CalculfraisTransfertRequest paysenvoi(String paysenvoi) {
        this.paysenvoi = paysenvoi;
        return this;
    }

    public CalculfraisTransfertRequest paysdestination(String paysdestination) {
        this.paysdestination = paysdestination;
        return this;
    }

    public CalculfraisTransfertRequest typetransaction(Integer typetransaction) {
        this.typetransaction = typetransaction;
        return this;
    }

    @Override
    public String toString() {
        return "{" +
            " montant='" + getMontant() + "'" +
            ", paysenvoi='" + getPaysenvoi() + "'" +
            ", paysdestination='" + getPaysdestination() + "'" +
            ", typetransaction='" + getTypetransaction() + "'" +
            "}";
    }

}