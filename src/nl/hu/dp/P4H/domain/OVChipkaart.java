package nl.hu.dp.P4H.domain;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "ov_chipkaart")
public class OVChipkaart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int kaart_nummer;

    @Column(name = "geldig_tot", nullable = false)
    private LocalDate geldig_tot;

    @Column(name = "klasse", nullable = false)
    private int klasse;

    @Column(name = "saldo", nullable = false)
    private double saldo;

    @ManyToOne
    @JoinColumn(name = "reiziger_id", nullable = false)
    private Reiziger reiziger;

    public OVChipkaart() {}

    public OVChipkaart(int kaart_nummer, LocalDate geldig_tot, int klasse, double saldo, Reiziger reiziger) {
        this.kaart_nummer = kaart_nummer;
        this.geldig_tot = geldig_tot;
        this.klasse = klasse;
        this.saldo = saldo;
        this.reiziger = reiziger;
    }

    public int getKaart_nummer() {
        return kaart_nummer;
    }

    public void setKaart_nummer(int kaart_nummer) {
        this.kaart_nummer = kaart_nummer;
    }

    public LocalDate getGeldig_tot() {
        return geldig_tot;
    }

    public void setGeldig_tot(LocalDate geldig_tot) {
        this.geldig_tot = geldig_tot;
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
        return String.format("OVChipkaart {kaart_nummer=%d, geldig_tot='%s', klasse=%d, saldo=%.2f, reiziger='%s'}",
                kaart_nummer, geldig_tot, klasse, saldo, reiziger.getReiziger_id());
    }
}
