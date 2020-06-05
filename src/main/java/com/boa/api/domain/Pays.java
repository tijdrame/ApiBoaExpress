package com.boa.api.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A Pays.
 */
@Entity
@Table(name = "pays")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Pays implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "pays", nullable = false)
    private String pays;

    @NotNull
    @Column(name = "iso_alpha_2", nullable = false)
    private String isoAlpha2;

    @NotNull
    @Column(name = "iso_alpha_3", nullable = false)
    private String isoAlpha3;

    @NotNull
    @Column(name = "iso_numerique", nullable = false)
    private String isoNumerique;

    public Pays (){}
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPays() {
        return pays;
    }

    public Pays pays(String pays) {
        this.pays = pays;
        return this;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public String getIsoAlpha2() {
        return isoAlpha2;
    }

    public Pays isoAlpha2(String isoAlpha2) {
        this.isoAlpha2 = isoAlpha2;
        return this;
    }

    public void setIsoAlpha2(String isoAlpha2) {
        this.isoAlpha2 = isoAlpha2;
    }

    public String getIsoAlpha3() {
        return isoAlpha3;
    }

    public Pays isoAlpha3(String isoAlpha3) {
        this.isoAlpha3 = isoAlpha3;
        return this;
    }

    public void setIsoAlpha3(String isoAlpha3) {
        this.isoAlpha3 = isoAlpha3;
    }

    public String getIsoNumerique() {
        return isoNumerique;
    }

    public Pays isoNumerique(String isoNumerique) {
        this.isoNumerique = isoNumerique;
        return this;
    }

    public void setIsoNumerique(String isoNumerique) {
        this.isoNumerique = isoNumerique;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Pays)) {
            return false;
        }
        return id != null && id.equals(((Pays) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Pays{" +
            "id=" + getId() +
            ", pays='" + getPays() + "'" +
            ", isoAlpha2='" + getIsoAlpha2() + "'" +
            ", isoAlpha3='" + getIsoAlpha3() + "'" +
            ", isoNumerique='" + getIsoNumerique() + "'" +
            "}";
    }
}
