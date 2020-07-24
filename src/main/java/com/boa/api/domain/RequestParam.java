package com.boa.api.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A RequestParam.
 */
@Entity
@Table(name = "request_param")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class RequestParam implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "channel_id", nullable = false)
    private String channelId;

    @NotNull
    @Column(name = "user_id", nullable = false)
    private String userId;

    @NotNull
    @Column(name = "transaction_secret", nullable = false)
    private String transactionSecret;

    @NotNull
    @Column(name = "request_id", nullable = false)
    private String requestId;

    @NotNull
    @Column(name = "code_partenaire", nullable = false)
    private String codePartenaire;

    @NotNull
    @Column(name = "pays", nullable = false)
    private String pays;

    @Column(name = "base_url")
    private String baseUrl;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getChannelId() {
        return channelId;
    }

    public RequestParam channelId(String channelId) {
        this.channelId = channelId;
        return this;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getUserId() {
        return userId;
    }

    public RequestParam userId(String userId) {
        this.userId = userId;
        return this;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTransactionSecret() {
        return transactionSecret;
    }

    public RequestParam transactionSecret(String transactionSecret) {
        this.transactionSecret = transactionSecret;
        return this;
    }

    public void setTransactionSecret(String transactionSecret) {
        this.transactionSecret = transactionSecret;
    }

    public String getRequestId() {
        return requestId;
    }

    public RequestParam requestId(String requestId) {
        this.requestId = requestId;
        return this;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getCodePartenaire() {
        return codePartenaire;
    }

    public RequestParam codePartenaire(String codePartenaire) {
        this.codePartenaire = codePartenaire;
        return this;
    }

    public void setCodePartenaire(String codePartenaire) {
        this.codePartenaire = codePartenaire;
    }

    public String getPays() {
        return pays;
    }

    public RequestParam pays(String pays) {
        this.pays = pays;
        return this;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public String getBaseUrl() {
        return this.baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RequestParam)) {
            return false;
        }
        return id != null && id.equals(((RequestParam) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "RequestParam{" +
            "id=" + getId() +
            ", channelId='" + getChannelId() + "'" +
            ", userId='" + getUserId() + "'" +
            ", transactionSecret='" + getTransactionSecret() + "'" +
            ", requestId='" + getRequestId() + "'" +
            ", codePartenaire='" + getCodePartenaire() + "'" +
            ", pays='" + getPays() + "'" +
            ", baseUrl='" + getBaseUrl() + "'" +
            "}";
    }
}
