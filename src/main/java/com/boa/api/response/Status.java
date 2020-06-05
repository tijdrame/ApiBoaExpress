package com.boa.api.response;

public class Status {
   private String statut, partenairereftrans, numerotransaction, codepartenaire; 

    public Status() {
    }

    public Status(String statut, String partenairereftrans, String numerotransaction, String codepartenaire) {
        this.statut = statut;
        this.partenairereftrans = partenairereftrans;
        this.numerotransaction = numerotransaction;
        this.codepartenaire = codepartenaire;
    }

    public String getStatut() {
        return this.statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public String getPartenairereftrans() {
        return this.partenairereftrans;
    }

    public void setPartenairereftrans(String partenairereftrans) {
        this.partenairereftrans = partenairereftrans;
    }

    public String getNumerotransaction() {
        return this.numerotransaction;
    }

    public void setNumerotransaction(String numerotransaction) {
        this.numerotransaction = numerotransaction;
    }

    public String getCodepartenaire() {
        return this.codepartenaire;
    }

    public void setCodepartenaire(String codepartenaire) {
        this.codepartenaire = codepartenaire;
    }

    public Status statut(String statut) {
        this.statut = statut;
        return this;
    }

    public Status partenairereftrans(String partenairereftrans) {
        this.partenairereftrans = partenairereftrans;
        return this;
    }

    public Status numerotransaction(String numerotransaction) {
        this.numerotransaction = numerotransaction;
        return this;
    }

    public Status codepartenaire(String codepartenaire) {
        this.codepartenaire = codepartenaire;
        return this;
    }
    
    @Override
    public String toString() {
        return "{" +
            " statut='" + getStatut() + "'" +
            ", partenairereftrans='" + getPartenairereftrans() + "'" +
            ", numerotransaction='" + getNumerotransaction() + "'" +
            ", codepartenaire='" + getCodepartenaire() + "'" +
            "}";
    }

}