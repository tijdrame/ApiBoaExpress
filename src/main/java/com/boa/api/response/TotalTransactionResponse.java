package com.boa.api.response;

public class TotalTransactionResponse extends GenericResponse{
    private Long totalGeneral, totalConfirme;

    public TotalTransactionResponse() {
    }

    public TotalTransactionResponse(Long totalGeneral, Long totalConfirme) {
        this.totalGeneral = totalGeneral;
        this.totalConfirme = totalConfirme;
    }

    public Long getTotalGeneral() {
        return this.totalGeneral;
    }

    public void setTotalGeneral(Long totalGeneral) {
        this.totalGeneral = totalGeneral;
    }

    public Long getTotalConfirme() {
        return this.totalConfirme;
    }

    public void setTotalConfirme(Long totalConfirme) {
        this.totalConfirme = totalConfirme;
    }

    public TotalTransactionResponse totalGeneral(Long totalGeneral) {
        this.totalGeneral = totalGeneral;
        return this;
    }

    public TotalTransactionResponse totalConfirme(Long totalConfirme) {
        this.totalConfirme = totalConfirme;
        return this;
    }

    @Override
    public String toString() {
        return "{" +
            " totalGeneral='" + getTotalGeneral() + "'" +
            ", totalConfirme='" + getTotalConfirme() + "'" +
            "}";
    }

}