package nl.hu.dp.P4.domain;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "reiziger")
public class Reiziger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reiziger_id")
    private int id;

    @Column(name = "voorletters")
    private String voorletters;

    @Column(name = "tussenvoegsel")
    private String tussenvoegsel;

    @Column(name = "achternaam")
    private String achternaam;

    @Column(name = "geboortedatum")
    private LocalDate geboortedatum;

    @OneToOne(mappedBy = "reiziger", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Adres adres;

    @OneToMany(mappedBy = "reiziger", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<nl.hu.dp.P4.domain.OVChipkaart> ovChipkaarten = new ArrayList<>();

    public Reiziger() {}

    public Reiziger(String voorletters, String tussenvoegsel, String achternaam, LocalDate geboortedatum) {
        this.voorletters = voorletters;
        this.tussenvoegsel = tussenvoegsel;
        this.achternaam = achternaam;
        this.geboortedatum = geboortedatum;
    }

    // Getters en Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVoorletters() {
        return voorletters;
    }

    public void setVoorletters(String voorletters) {
        this.voorletters = voorletters;
    }

    public String getTussenvoegsel() {
        return tussenvoegsel;
    }

    public void setTussenvoegsel(String tussenvoegsel) {
        this.tussenvoegsel = tussenvoegsel;
    }

    public String getAchternaam() {
        return achternaam;
    }

    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }

    public LocalDate getGeboortedatum() {
        return geboortedatum;
    }

    public void setGeboortedatum(LocalDate geboortedatum) {
        this.geboortedatum = geboortedatum;
    }

    public Adres getAdres() {
        return adres;
    }

    public void setAdres(Adres adres) {
        this.adres = adres;
        if (adres.getReiziger() != this) {
            adres.setReiziger(this);
        }
    }

    public List<nl.hu.dp.P4.domain.OVChipkaart> getOvChipkaarten() {
        return ovChipkaarten;
    }

    public void setOvChipkaarten(List<nl.hu.dp.P4.domain.OVChipkaart> ovChipkaarten) {
        this.ovChipkaarten = ovChipkaarten;
        for (nl.hu.dp.P4.domain.OVChipkaart ovChipkaart : ovChipkaarten) {
            if (ovChipkaart.getReiziger() != this) {
                ovChipkaart.setReiziger(this);
            }
        }
    }

    public void addOVChipkaart(nl.hu.dp.P4.domain.OVChipkaart ovChipkaart) {
        this.ovChipkaarten.add(ovChipkaart);
        ovChipkaart.setReiziger(this);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("Reiziger {#" + id + ": " + voorletters + " ");
        if (tussenvoegsel != null && !tussenvoegsel.isEmpty()) {
            result.append(tussenvoegsel).append(" ");
        }
        result.append(achternaam).append(", geb. ").append(geboortedatum);

        if (adres != null) {
            result.append(", ").append(adres);
        }

        if (!ovChipkaarten.isEmpty()) {
            result.append(", OVChipkaarten: ").append(ovChipkaarten);
        }

        result.append("}");
        return result.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Reiziger)) return false;
        Reiziger reiziger = (Reiziger) o;
        return id == reiziger.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
