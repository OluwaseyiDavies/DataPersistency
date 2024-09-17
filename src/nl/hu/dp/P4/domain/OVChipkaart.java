package nl.hu.dp.P4.domain;

import java.util.Date;
import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "ov_chipkaart")
public class OVChipkaart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "kaart_nummer")
    private int kaartNummer;

    @Column(name = "geldig_tot")
    private Date geldigTot;

    @Column(name = "klasse")
    private int klasse;

    @Column(name = "saldo")
    private double saldo;

    @ManyToOne
    @JoinColumn(name = "reiziger_id")
    private Reiziger reiziger;

    public OVChipkaart() {}

    public OVChipkaart(Date geldigTot, int klasse, double saldo, Reiziger reiziger) {
        this.geldigTot = geldigTot;
        this.klasse = klasse;
        this.saldo = saldo;
        this.reiziger = reiziger;
    }

    // Getters en Setters
    public int getKaartNummer() {
        return kaartNummer;
    }

    public void setKaartNummer(int kaartNummer) {
        this.kaartNummer = kaartNummer;
    }

    public Date getGeldigTot() {
        return geldigTot;
    }

    public void setGeldigTot(Date geldigTot) {
        this.geldigTot = geldigTot;
    }

    public int getKlasse() {
        return klasse;
    }

    public void setKlasse(int klasse) {
        this.klasse = klasse;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public Reiziger getReiziger() {
        return reiziger;
    }

    public void setReiziger(Reiziger reiziger) {
        this.reiziger = reiziger;
    }

    @Override
    public String toString() {
        return "OVChipkaart {#" + kaartNummer + ": geldig tot " + geldigTot + ", klasse " + klasse + ", saldo " + saldo + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof nl.hu.dp.P4.domain.OVChipkaart)) return false;
        nl.hu.dp.P4.domain.OVChipkaart that = (nl.hu.dp.P4.domain.OVChipkaart) o;
        return kaartNummer == that.kaartNummer;
    }

    @Override
    public int hashCode() {
        return Objects.hash(kaartNummer);
    }
}
