package com.boa.api.request;

import java.util.ArrayList;
import java.util.List;

public class Transaction {
    
    private Double montant;
    private String paysenvoi;
    private String paysdestination;
    private Integer typetransaction;
    private String questionsecrete;
    private String reponsesecrete, nationalite;
    private String referencebancaire;
    private List<String> raisontransfert = null;
    private String canal;
    private Sender sender;
    private Beneficiary beneficiary;

    public Transaction() {
    }

    public Transaction(Double montant, String paysenvoi, String paysdestination, 
    Integer typetransaction, String questionsecrete, String reponsesecrete, String referencebancaire, List<String> raisontransfert, String canal, Sender sender, Beneficiary beneficiary) {
        this.montant = montant;
        this.paysenvoi = paysenvoi;
        this.paysdestination = paysdestination;
        this.typetransaction = typetransaction;
        this.questionsecrete = questionsecrete;
        this.reponsesecrete = reponsesecrete;
        this.referencebancaire = referencebancaire;
        this.raisontransfert = raisontransfert;
        this.canal = canal;
        this.sender = sender;
        this.beneficiary = beneficiary;
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

    public String getQuestionsecrete() {
        return this.questionsecrete;
    }

    public void setQuestionsecrete(String questionsecrete) {
        this.questionsecrete = questionsecrete;
    }

    public String getReponsesecrete() {
        return this.reponsesecrete;
    }

    public void setReponsesecrete(String reponsesecrete) {
        this.reponsesecrete = reponsesecrete;
    }

    public String getReferencebancaire() {
        return this.referencebancaire;
    }

    public void setReferencebancaire(String referencebancaire) {
        this.referencebancaire = referencebancaire;
    }

    public List<String> getRaisontransfert() {
        if(raisontransfert==null ||raisontransfert.isEmpty()) 
            raisontransfert = new ArrayList<>();
        return this.raisontransfert;
    }

    public void setRaisontransfert(List<String> raisontransfert) {
        this.raisontransfert = raisontransfert;
    }

    public String getCanal() {
        return this.canal;
    }

    public void setCanal(String canal) {
        this.canal = canal;
    }

    public Sender getSender() {
        return this.sender;
    }

    public void setSender(Sender sender) {
        this.sender = sender;
    }

    public Beneficiary getBeneficiary() {
        return this.beneficiary;
    }

    public void setBeneficiary(Beneficiary beneficiary) {
        this.beneficiary = beneficiary;
    }

    public Transaction montant(Double montant) {
        this.montant = montant;
        return this;
    }

    public Transaction paysenvoi(String paysenvoi) {
        this.paysenvoi = paysenvoi;
        return this;
    }

    public Transaction paysdestination(String paysdestination) {
        this.paysdestination = paysdestination;
        return this;
    }

    public Transaction typetransaction(Integer typetransaction) {
        this.typetransaction = typetransaction;
        return this;
    }

    public Transaction questionsecrete(String questionsecrete) {
        this.questionsecrete = questionsecrete;
        return this;
    }

    public Transaction reponsesecrete(String reponsesecrete) {
        this.reponsesecrete = reponsesecrete;
        return this;
    }

    public Transaction referencebancaire(String referencebancaire) {
        this.referencebancaire = referencebancaire;
        return this;
    }

    public Transaction raisontransfert(List<String> raisontransfert) {
        this.raisontransfert = raisontransfert;
        return this;
    }

    public Transaction canal(String canal) {
        this.canal = canal;
        return this;
    }

    public Transaction sender(Sender sender) {
        this.sender = sender;
        return this;
    }

    public Transaction beneficiary(Beneficiary beneficiary) {
        this.beneficiary = beneficiary;
        return this;
    }

    public String getNationalite() {
        return this.nationalite;
    }

    public void setNationalite(String nationalite) {
        this.nationalite = nationalite;
    }

    @Override
    public String toString() {
        return "{" +
            " montant='" + getMontant() + "'" +
            ", paysenvoi='" + getPaysenvoi() + "'" +
            ", paysdestination='" + getPaysdestination() + "'" +
            ", typetransaction='" + getTypetransaction() + "'" +
            ", questionsecrete='" + getQuestionsecrete() + "'" +
            ", reponsesecrete='" + getReponsesecrete() + "'" +
            ", referencebancaire='" + getReferencebancaire() + "'" +
            ", raisontransfert='" + getRaisontransfert() + "'" +
            ", canal='" + getCanal() + "'" +
            ", sender='" + getSender() + "'" +
            ", beneficiary='" + getBeneficiary() + "'" +
            ", nationalite='" + getNationalite() + "'" +
            "}";
    }

}