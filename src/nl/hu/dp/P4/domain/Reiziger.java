package nl.hu.dp.P4.domain;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Reiziger {
    private int reiziger_id;
    private String voorletters;
    private String tussenvoegsel;
    private String achternaam;
    private LocalDate geboortedatum;
    private List<OVChipkaart> ovChipkaarten = new ArrayList<>();

    public Reiziger(int reiziger_id, String voorletters, String tussenvoegsel, String achternaam, LocalDate geboortedatum) {
        this.reiziger_id = reiziger_id;
        this.voorletters = voorletters;
        this.tussenvoegsel = tussenvoegsel;
        this.achternaam = achternaam;
        this.geboortedatum = geboortedatum;
    }

    public int getReiziger_id() {
        return reiziger_id;
    }

    public void setReiziger_id(int reiziger_id) {
        this.reiziger_id = reiziger_id;
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

    public List<OVChipkaart> getOVChipkaarten() {
        return ovChipkaarten;
    }

    public void addOVChipkaart(OVChipkaart ovChipkaart) {
        this.ovChipkaarten.add(ovChipkaart);
        ovChipkaart.setReiziger(null);
    }

    @Override
    public String toString() {
        StringBuilder volledigeNaam = new StringBuilder (voorletters);
        if (tussenvoegsel != null && !tussenvoegsel.isEmpty()) {
            volledigeNaam.append(" ").append(tussenvoegsel);
        }
        volledigeNaam.append(" ").append(achternaam);

        StringBuilder ovChipkaartenInfo = new StringBuilder();
        if (!ovChipkaarten.isEmpty()) {
            ovChipkaartenInfo.append(", OVChipkaarten: ");
            for (OVChipkaart ovChipkaart : ovChipkaarten) {
                ovChipkaartenInfo.append(ovChipkaart.toString()).append("; ");
            }
        } else {
            ovChipkaartenInfo.append(", geen OVChipkaarten");
        }
        return "#" +reiziger_id + " " + volledigeNaam + " (" + geboortedatum + ")" + ovChipkaartenInfo.toString();
    }
}
