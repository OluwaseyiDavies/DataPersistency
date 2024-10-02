package nl.hu.dp.P3H.domain;

import javax.persistence.*;

@Entity
@Table(name = "adres")
public class Adres {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int adres_id;

    @Column(name = "postcode", nullable = false)
    private String postcode;

    @Column(name = "huisnummer", nullable = false)
    private String huisnummer;

    @Column(name = "straat", nullable = false)
    private String straat;

    @Column(name = "woonplaats", nullable = false)
    private String woonplaats;

    @OneToOne(mappedBy = "adres")
    private Reiziger reiziger;

    public Adres() {}

    public Adres(int adres_id, String postcode, String huisnummer, String straat, String woonplaats) {
        this.adres_id = adres_id;
        this.postcode = postcode;
        this.huisnummer = huisnummer;
        this.straat = straat;
        this.woonplaats = woonplaats;
    }

    public int getAdres_id() {
        return adres_id;
    }

    public void setAdres_id(int adres_id) {
        this.adres_id = adres_id;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getHuisnummer() {
        return huisnummer;
    }

    public void setHuisnummer(String huisnummer) {
        this.huisnummer = huisnummer;
    }

    public String getStraat() {
        return straat;
    }

    public void setStraat(String straat) {
        this.straat = straat;
    }

    public String getWoonplaats() {
        return woonplaats;
    }

    public void setWoonplaats(String woonplaats) {
        this.woonplaats = woonplaats;
    }

    public Reiziger getReiziger() {
        return reiziger;
    }

    public void setReiziger(Reiziger reiziger) {
        this.reiziger = reiziger;
    }

    @Override
    public String toString() {
        return String.format("Adres {id=%d, postcode='%s', huisnummer='%s', straat='%s', woonplaats='%s', reiziger='%s'}",
                adres_id, postcode, huisnummer, straat, woonplaats, reiziger);
    }
}
