package nl.hu.dp.P4H.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class AdresHibernate {

    @Id
    @GeneratedValue
    private Long id;

    public ReizigerHibernate getReiziger() {
    return null;
    }

    public ReizigerHibernate setReiziger(ReizigerHibernate reizigerHibernate) {
    return reizigerHibernate;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
