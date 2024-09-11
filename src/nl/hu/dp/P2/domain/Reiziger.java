package nl.hu.dp.P2.domain;

import nl.hu.dp.P3.domain.Adres;

import java.sql.Date;

public class Reiziger {
    private int id;
    private String voorletters;
    private String tussenvoegesel;
    private String achternaam;
    private Date geboortedatum;
    private Adres adres; // Nieuw attribuut voor associatie

    public Reiziger(int id, String voorletters, String tussenvoegesel, String achternaam, Date geboortedatum) {
        this.id = id;
        this.voorletters = voorletters;
        this.tussenvoegesel = tussenvoegesel;
        this.achternaam = achternaam;
        this.geboortedatum = geboortedatum;
    }

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

    public String getTussenvoegesel() {
        return tussenvoegesel;
    }

    public void setTussenvoegesel(String tussenvoegesel) {
        this.tussenvoegesel = tussenvoegesel;
    }

    public String getAchternaam() {
        return achternaam;
    }

    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }

    public Date getGeboortedatum() {
        return geboortedatum;
    }

    public void setGeboortedatum(Date geboortedatum) {
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
        String volledigeNaam = voorletters;
        if (tussenvoegesel != null && !tussenvoegesel.isEmpty()) {
            volledigeNaam += "" + tussenvoegesel;
        }
        volledigeNaam += " " + achternaam;
        String output = "#" + id + " " + volledigeNaam + " (" + geboortedatum + ")";
        if (adres != null) {
            output += ", Adres {" + adres.toString() + "}";
        }
        return output;
    }
}