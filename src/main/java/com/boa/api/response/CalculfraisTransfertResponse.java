package com.boa.api.response;

public class CalculfraisTransfertResponse extends GenericResponse{
    private String codeop;
    private String detailsop;
    private Fraistransaction fraistransaction;

    public CalculfraisTransfertResponse() {
    }

    public CalculfraisTransfertResponse(String codeop, String detailsop, Fraistransaction fraistransaction) {
        this.codeop = codeop;
        this.detailsop = detailsop;
        this.fraistransaction = fraistransaction;
    }

    public String getCodeop() {
        return this.codeop;
    }

    public void setCodeop(String codeop) {
        this.codeop = codeop;
    }

    public String getDetailsop() {
        return this.detailsop;
    }

    public void setDetailsop(String detailsop) {
        this.detailsop = detailsop;
    }

    public Fraistransaction getFraistransaction() {
        return this.fraistransaction;
    }

    public void setFraistransaction(Fraistransaction fraistransaction) {
        this.fraistransaction = fraistransaction;
    }

    public CalculfraisTransfertResponse codeop(String codeop) {
        this.codeop = codeop;
        return this;
    }

    public CalculfraisTransfertResponse detailsop(String detailsop) {
        this.detailsop = detailsop;
        return this;
    }

    public CalculfraisTransfertResponse fraistransaction(Fraistransaction fraistransaction) {
        this.fraistransaction = fraistransaction;
        return this;
    }

    @Override
    public String toString() {
        return "{" +
        " generic=='" + super.toString() +
            " codeop='" + getCodeop() + "'" +
            ", detailsop='" + getDetailsop() + "'" +
            ", fraistransaction='" + getFraistransaction() + "'" +
            "}";
    }

}