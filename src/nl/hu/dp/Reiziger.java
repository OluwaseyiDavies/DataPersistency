package nl.hu.dp;

import java.sql.Date;

public class Reiziger {
    private int id;
    private String voorletters;
    private String tussenvoegesel;
    private String achternaam;
    private Date geboortedatum;

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

    @Override
    public String toString() {
        String volledigeNaam = voorletters;
        if (tussenvoegesel != null && !tussenvoegesel.isEmpty()) {
            volledigeNaam += "" + tussenvoegesel;
        }
        volledigeNaam += " " + achternaam;
        return "#" + id + " " + volledigeNaam + " (" + geboortedatum + ")";
    }
}
