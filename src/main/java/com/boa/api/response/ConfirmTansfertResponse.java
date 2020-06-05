package com.boa.api.response;

public class ConfirmTansfertResponse extends GenericResponse {

    private String codeop;
    private String detailsop;
    private Confirmdeposit confirmdeposit;

    public ConfirmTansfertResponse() {
    }

    public ConfirmTansfertResponse(String codeop, String detailsop, Confirmdeposit confirmdeposit) {
        this.codeop = codeop;
        this.detailsop = detailsop;
        this.confirmdeposit = confirmdeposit;
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

    public Confirmdeposit getConfirmdeposit() {
        return this.confirmdeposit;
    }

    public void setConfirmdeposit(Confirmdeposit confirmdeposit) {
        this.confirmdeposit = confirmdeposit;
    }

    public ConfirmTansfertResponse codeop(String codeop) {
        this.codeop = codeop;
        return this;
    }

    public ConfirmTansfertResponse detailsop(String detailsop) {
        this.detailsop = detailsop;
        return this;
    }

    public ConfirmTansfertResponse confirmdeposit(Confirmdeposit confirmdeposit) {
        this.confirmdeposit = confirmdeposit;
        return this;
    }

    @Override
    public String toString() {
        return "{" +
        " generic=='" + super.toString() +
        " responseReference='" + getResponseReference() + 
        " codeop='" + getCodeop() + 
        "'" + ", detailsop='" + getDetailsop() +
         "'" + ", confirmdeposit='" + getConfirmdeposit() + "'" + "}";
    }

}