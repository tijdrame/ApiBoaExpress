package com.boa.api.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;

/**
 * A Transaction.
 */
@Entity
@Table(name = "transaction")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Transaction implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "transaction_seq_gen")
    @SequenceGenerator(name = "transaction_seq_gen", sequenceName = "transaction_id_seq", initialValue = 1, allocationSize = 1)
    private Long id;

    @NotNull
    @Column(name = "montant", nullable = false)
    private Double montant;

    @NotNull
    @Column(name = "montant_frais", nullable = false)
    private Double montantFrais;

    @NotNull
    @Column(name = "pays_envoi", nullable = false)
    private String paysEnvoi;

    @NotNull
    @Column(name = "pays_destination", nullable = false)
    private String paysDestination;

    @NotNull
    @Column(name = "type_transaction", nullable = false)
    private String typeTransaction;

    @NotNull
    @Column(name = "question_secrete", nullable = false)
    private String questionSecrete;

    @NotNull
    @Column(name = "reponse_secrete", nullable = false)
    private String reponseSecrete;

    @NotNull
    @Column(name = "reference_bancaire", nullable = false)
    private String referenceBancaire;

    @Column(name = "raison_transfert")
    private String raisonTransfert;

    @Column(name = "canal")
    private String canal;

    @NotNull
    @Column(name = "sender_name", nullable = false)
    private String senderName;

    @Column(name = "sender_prenom")
    private String senderPrenom;

    @NotNull
    @Column(name = "sender_telephone", nullable = false)
    private String senderTelephone;

    @NotNull
    @Column(name = "sender_compte", nullable = false)
    private String senderCompte;

    @NotNull
    @Column(name = "beneficiary_name", nullable = false)
    private String beneficiaryName;

    @Column(name = "beneficiary_prenom")
    private String beneficiaryPrenom;

    @NotNull
    @Column(name = "beneficiary_phone", nullable = false)
    private String beneficiaryPhone;

    @Column(name = "numero_transaction")
    private String numeroTransaction;

    @Column(name = "is_confirmed")
    private Boolean isConfirmed;

    @Column(name = "code_confim")
    private String codeConfim;

    @Column(name = "date_created")
    private Instant dateCreated;
    
    @Column(name = "date_confirmed")
    private Instant dateConfirmed;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getMontant() {
        return montant;
    }

    public Transaction montant(Double montant) {
        this.montant = montant;
        return this;
    }

    public void setMontant(Double montant) {
        this.montant = montant;
    }

    public String getPaysEnvoi() {
        return paysEnvoi;
    }

    public Transaction paysEnvoi(String paysEnvoi) {
        this.paysEnvoi = paysEnvoi;
        return this;
    }

    public void setPaysEnvoi(String paysEnvoi) {
        this.paysEnvoi = paysEnvoi;
    }

    public String getPaysDestination() {
        return paysDestination;
    }

    public Transaction paysDestination(String paysDestination) {
        this.paysDestination = paysDestination;
        return this;
    }

    public void setPaysDestination(String paysDestination) {
        this.paysDestination = paysDestination;
    }

    public String getTypeTransaction() {
        return typeTransaction;
    }

    public Transaction typeTransaction(String typeTransaction) {
        this.typeTransaction = typeTransaction;
        return this;
    }

    public void setTypeTransaction(String typeTransaction) {
        this.typeTransaction = typeTransaction;
    }

    public String getQuestionSecrete() {
        return questionSecrete;
    }

    public Transaction questionSecrete(String questionSecrete) {
        this.questionSecrete = questionSecrete;
        return this;
    }

    public void setQuestionSecrete(String questionSecrete) {
        this.questionSecrete = questionSecrete;
    }

    public String getReponseSecrete() {
        return reponseSecrete;
    }

    public Transaction reponseSecrete(String reponseSecrete) {
        this.reponseSecrete = reponseSecrete;
        return this;
    }

    public void setReponseSecrete(String reponseSecrete) {
        this.reponseSecrete = reponseSecrete;
    }

    public String getReferenceBancaire() {
        return referenceBancaire;
    }

    public Transaction referenceBancaire(String referenceBancaire) {
        this.referenceBancaire = referenceBancaire;
        return this;
    }

    public void setReferenceBancaire(String referenceBancaire) {
        this.referenceBancaire = referenceBancaire;
    }

    public String getRaisonTransfert() {
        return raisonTransfert;
    }

    public Transaction raisonTransfert(String raisonTransfert) {
        this.raisonTransfert = raisonTransfert;
        return this;
    }

    public void setRaisonTransfert(String raisonTransfert) {
        this.raisonTransfert = raisonTransfert;
    }

    public String getCanal() {
        return canal;
    }

    public Transaction canal(String canal) {
        this.canal = canal;
        return this;
    }

    public void setCanal(String canal) {
        this.canal = canal;
    }

    public String getSenderName() {
        return senderName;
    }

    public Transaction senderName(String senderName) {
        this.senderName = senderName;
        return this;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getSenderPrenom() {
        return senderPrenom;
    }

    public Transaction senderPrenom(String senderPrenom) {
        this.senderPrenom = senderPrenom;
        return this;
    }

    public void setSenderPrenom(String senderPrenom) {
        this.senderPrenom = senderPrenom;
    }

    public String getSenderTelephone() {
        return senderTelephone;
    }

    public Transaction senderTelephone(String senderTelephone) {
        this.senderTelephone = senderTelephone;
        return this;
    }

    public void setSenderTelephone(String senderTelephone) {
        this.senderTelephone = senderTelephone;
    }

    public String getSenderCompte() {
        return senderCompte;
    }

    public Transaction senderCompte(String senderCompte) {
        this.senderCompte = senderCompte;
        return this;
    }

    public void setSenderCompte(String senderCompte) {
        this.senderCompte = senderCompte;
    }

    public String getBeneficiaryName() {
        return beneficiaryName;
    }

    public Transaction beneficiaryName(String beneficiaryName) {
        this.beneficiaryName = beneficiaryName;
        return this;
    }

    public void setBeneficiaryName(String beneficiaryName) {
        this.beneficiaryName = beneficiaryName;
    }

    public String getBeneficiaryPrenom() {
        return beneficiaryPrenom;
    }

    public Transaction beneficiaryPrenom(String beneficiaryPrenom) {
        this.beneficiaryPrenom = beneficiaryPrenom;
        return this;
    }

    public void setBeneficiaryPrenom(String beneficiaryPrenom) {
        this.beneficiaryPrenom = beneficiaryPrenom;
    }

    public String getBeneficiaryPhone() {
        return beneficiaryPhone;
    }

    public Transaction beneficiaryPhone(String beneficiaryPhone) {
        this.beneficiaryPhone = beneficiaryPhone;
        return this;
    }

    public void setBeneficiaryPhone(String beneficiaryPhone) {
        this.beneficiaryPhone = beneficiaryPhone;
    }

    public String getNumeroTransaction() {
        return numeroTransaction;
    }

    public Transaction numeroTransaction(String numeroTransaction) {
        this.numeroTransaction = numeroTransaction;
        return this;
    }

    public void setNumeroTransaction(String numeroTransaction) {
        this.numeroTransaction = numeroTransaction;
    }

    public Boolean isIsConfirmed() {
        return isConfirmed;
    }

    public Transaction isConfirmed(Boolean isConfirmed) {
        this.isConfirmed = isConfirmed;
        return this;
    }

    public void setIsConfirmed(Boolean isConfirmed) {
        this.isConfirmed = isConfirmed;
    }

    public String getCodeConfim() {
        return codeConfim;
    }

    public Transaction codeConfim(String codeConfim) {
        this.codeConfim = codeConfim;
        return this;
    }

    public void setCodeConfim(String codeConfim) {
        this.codeConfim = codeConfim;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Transaction)) {
            return false;
        }
        return id != null && id.equals(((Transaction) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Transaction{" +
            "id=" + getId() +
            ", montant=" + getMontant() +
            ", paysEnvoi='" + getPaysEnvoi() + "'" +
            ", paysDestination='" + getPaysDestination() + "'" +
            ", typeTransaction='" + getTypeTransaction() + "'" +
            ", questionSecrete='" + getQuestionSecrete() + "'" +
            ", reponseSecrete='" + getReponseSecrete() + "'" +
            ", referenceBancaire='" + getReferenceBancaire() + "'" +
            ", raisonTransfert='" + getRaisonTransfert() + "'" +
            ", canal='" + getCanal() + "'" +
            ", senderName='" + getSenderName() + "'" +
            ", senderPrenom='" + getSenderPrenom() + "'" +
            ", senderTelephone='" + getSenderTelephone() + "'" +
            ", senderCompte='" + getSenderCompte() + "'" +
            ", beneficiaryName='" + getBeneficiaryName() + "'" +
            ", beneficiaryPrenom='" + getBeneficiaryPrenom() + "'" +
            ", beneficiaryPhone='" + getBeneficiaryPhone() + "'" +
            ", numeroTransaction='" + getNumeroTransaction() + "'" +
            ", isConfirmed='" + isIsConfirmed() + "'" +
            ", codeConfim='" + getCodeConfim() + "'" +
            ", dateCreated='" + getDateCreated() + "'" +
            ", dateConfirmed='" + getDateConfirmed() + "'" +
            ", montantFrais='" + getMontantFrais() + "'" +
            "}";
    }


    public Transaction() {
    }

    public Transaction(Long id, Double montant, String paysEnvoi, String paysDestination, String typeTransaction, String questionSecrete, String reponseSecrete, String referenceBancaire, String raisonTransfert, String canal, String senderName, String senderPrenom, String senderTelephone, String senderCompte, String beneficiaryName, String beneficiaryPrenom, String beneficiaryPhone, String numeroTransaction, Boolean isConfirmed, String codeConfim, Instant dateCreated, Instant dateConfirmed) {
        this.id = id;
        this.montant = montant;
        this.paysEnvoi = paysEnvoi;
        this.paysDestination = paysDestination;
        this.typeTransaction = typeTransaction;
        this.questionSecrete = questionSecrete;
        this.reponseSecrete = reponseSecrete;
        this.referenceBancaire = referenceBancaire;
        this.raisonTransfert = raisonTransfert;
        this.canal = canal;
        this.senderName = senderName;
        this.senderPrenom = senderPrenom;
        this.senderTelephone = senderTelephone;
        this.senderCompte = senderCompte;
        this.beneficiaryName = beneficiaryName;
        this.beneficiaryPrenom = beneficiaryPrenom;
        this.beneficiaryPhone = beneficiaryPhone;
        this.numeroTransaction = numeroTransaction;
        this.isConfirmed = isConfirmed;
        this.codeConfim = codeConfim;
        this.dateCreated = dateCreated;
        this.dateConfirmed = dateConfirmed;
    }

    public Boolean getIsConfirmed() {
        return this.isConfirmed;
    }


    public Instant getDateCreated() {
        return this.dateCreated;
    }

    public void setDateCreated(Instant dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Instant getDateConfirmed() {
        return this.dateConfirmed;
    }

    public void setDateConfirmed(Instant dateConfirmed) {
        this.dateConfirmed = dateConfirmed;
    }

    public Transaction id(Long id) {
        this.id = id;
        return this;
    }

    public Transaction dateCreated(Instant dateCreated) {
        this.dateCreated = dateCreated;
        return this;
    }

    public Transaction dateConfirmed(Instant dateConfirmed) {
        this.dateConfirmed = dateConfirmed;
        return this;
    }

    public Double getMontantFrais() {
        return this.montantFrais;
    }

    public void setMontantFrais(Double montantFrais) {
        this.montantFrais = montantFrais;
    }

    public Transaction montantFrais(Double montantFrais) {
        this.montantFrais = montantFrais;
        return this;
    }

}
