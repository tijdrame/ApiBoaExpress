package com.boa.api.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "user_extra")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class UserExtra implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id
    private Long id;

    @ManyToOne
    private Pays pays;

    @OneToOne
    @MapsId
    private User user;

    public UserExtra() {
    }

    public UserExtra(Long id, Pays pays, User user) {
        this.id = id;
        this.pays = pays;
        this.user = user;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Pays getPays() {
        return this.pays;
    }

    public void setPays(Pays pays) {
        this.pays = pays;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UserExtra id(Long id) {
        this.id = id;
        return this;
    }

    public UserExtra pays(Pays pays) {
        this.pays = pays;
        return this;
    }

    public UserExtra user(User user) {
        this.user = user;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserExtra)) {
            return false;
        }
        return id != null && id.equals(((UserExtra) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", pays='" + getPays() + "'" +
            ", user='" + getUser() + "'" +
            "}";
    }

}