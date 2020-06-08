package com.boa.api.response;

import java.util.ArrayList;
import java.util.List;

public class PaysActifsResponse extends GenericResponse {
    private String codeop, detailsop;
    private List<PaysResp> pays;

    public PaysActifsResponse() {
    }

    public PaysActifsResponse(String codeop, String detailsop, List<PaysResp> pays) {
        this.codeop = codeop;
        this.detailsop = detailsop;
        this.pays = pays;
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

    public List<PaysResp> getPays() {
        if(pays==null || pays.isEmpty()) this.pays = new ArrayList<>();
        return this.pays;
    }

    public void setPays(List<PaysResp> pays) {
        this.pays = pays;
    }

    public PaysActifsResponse codeop(String codeop) {
        this.codeop = codeop;
        return this;
    }

    public PaysActifsResponse detailsop(String detailsop) {
        this.detailsop = detailsop;
        return this;
    }

    public PaysActifsResponse pays(List<PaysResp> pays) {
        this.pays = pays;
        return this;
    }

    @Override
    public String toString() {
        return "{" +
            " codeop='" + getCodeop() + "'" +
            ", detailsop='" + getDetailsop() + "'" +
            ", pays='" + getPays() + "'" +
            "}";
    }

}