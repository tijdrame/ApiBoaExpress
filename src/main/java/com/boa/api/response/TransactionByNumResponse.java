package com.boa.api.response;

public class TransactionByNumResponse  extends GenericResponse{
    private String codeop, detailsop;
    private Status status;

    public TransactionByNumResponse() {
    }

    public TransactionByNumResponse(String codeop, String detailsop) {
        this.codeop = codeop;
        this.detailsop = detailsop;
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

    public TransactionByNumResponse codeop(String codeop) {
        this.codeop = codeop;
        return this;
    }

    public TransactionByNumResponse detailsop(String detailsop) {
        this.detailsop = detailsop;
        return this;
    }

    public TransactionByNumResponse(String codeop, String detailsop, Status status) {
        this.codeop = codeop;
        this.detailsop = detailsop;
        this.status = status;
    }

    public Status getStatus() {
        return this.status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public TransactionByNumResponse status(Status status) {
        this.status = status;
        return this;
    }

    @Override
    public String toString() {
        return "{" +
            " codeop='" + getCodeop() + "'" +
            ", detailsop='" + getDetailsop() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
    
}