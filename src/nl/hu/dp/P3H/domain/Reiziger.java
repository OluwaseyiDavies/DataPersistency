package nl.hu.dp.P3H.domain;

import javax.persistence.*;
import java.time.LocalDate;

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

    @OneToOne(mappedBy = "reiziger", cascade = CascadeType.ALL)
    private Adres adres;

    public Reiziger() {}

    public Reiziger(String voorletters, String tussenvoegsel, String achternaam, LocalDate geboortedatum) {
        this.voorletters = voorletters;
        this.tussenvoegsel = tussenvoegsel;
        this.achternaam = achternaam;
        this.geboortedatum = geboortedatum;
    }

    public int getId() {
        return id;
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
    }

    @Override
    public String toString() {
        return "Reiziger {#" + id + ": " + voorletters + " " +
                (tussenvoegsel != null ? tussenvoegsel + " " : "") + achternaam +
                ", geb. " + geboortedatum + ", " + adres + "}";
    }
}