package nl.hu.dp.P1;

import nl.hu.dp.P2.domain.Reiziger;
import nl.hu.dp.P2.*;
import nl.hu.dp.P3.domain.*;
import nl.hu.dp.P3.*;
import nl.hu.dp.P3H.domain.*;
import nl.hu.dp.P3H.*;
import nl.hu.dp.P3H.domain.Adres;

import java.sql.*;
import java.util.List;

public class Main {
    private static Connection connection;

    public static void main(String[] args) throws SQLException {
        try {
            getConnection();
            ReizigerDAOsql reizigerDAO = new ReizigerDAOsql(connection);
            testReizigersDAO(reizigerDAO);
            testConnection();
        }finally {
            closeConnection();
        }
    }

    private static Connection getConnection() throws SQLException {
        if (connection == null) {
            String url =
                    "jdbc:postgresql://localhost:5432/ovchip?user=postgres&password=SDavies2003!";
            connection = DriverManager.getConnection(url);
            System.out.println("Connection established");
        }
        return connection;
    }

    private static void closeConnection() throws SQLException {
        if (connection != null) {
            connection.close();
            connection = null;
        }
    }

    private static void testConnection() throws SQLException {
        getConnection();
        String query = "SELECT voorletters, tussenvoegsel, achternaam, geboortedatum FROM reiziger";
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();
        int counter = 0;
        System.out.println("Alle reizigers: ");
        while (resultSet.next()) {
            counter++;
            String voorletters = resultSet.getString("voorletters");
            String tussenvoegsel = resultSet.getString("tussenvoegsel");
            String achternaam = resultSet.getString("achternaam");
            Date geboortedatum = resultSet.getDate("geboortedatum");

            String volledigNaam = voorletters;
            if (tussenvoegsel != null && !tussenvoegsel.isEmpty()) {
                volledigNaam += "" + tussenvoegsel;
            }
            volledigNaam += " " + achternaam;

            System.out.println("#" + counter + ": " + volledigNaam + " (" + geboortedatum + ")");
        }
    }

    private static void testReizigersDAO(ReizigerDAOsql rdao) throws SQLException {
        System.out.println("\n---------- Test ReizigerDAO -------------");

        // Alle reizigers ophalen uit de database
        List<Reiziger> reizigers = rdao.findAll();
        System.out.println("[Test] ReizigerDAO.findAll() geeft de volgende reizigers:");
        for (Reiziger r: reizigers) {
            System.out.println(r);
        }

        //Een nieuwe reiziger aanmaken en opslaan in de database
        String gbdatum = "1981-03-14";
        Reiziger sietske = new Reiziger(582911, "S.", "", "Boers", java.sql.Date.valueOf(gbdatum));
        System.out.print("[Test] Eerst " + reizigers.size() + " reizigers, na ReizigerDAO.save() ");
        rdao.save(sietske);
        reizigers = rdao.findAll();
        System.out.println(reizigers.size() + " reizigers\n");
    }
}

//private static void testAdresDAO(AdresDAOHibernate adao) {
//    System.out.println("\n---------- Test AdresDAO -------------");
//
//    // Een nieuw adres aanmaken en opslaan
//    Adres = new Adres("1234AB", "42", "Straatnaam", "Woonplaats");
//    adao.save(nieuwAdres);
//    System.out.println("[Test] Adres opgeslagen: " + nieuwAdres);
//
//    // Het adres opzoeken op basis van ID
//    Adres gevondenAdres = adao.findByAdres_id(nieuwAdres.getAdres_id());
//    System.out.println("[Test] Adres gevonden: " + gevondenAdres);
//
//    // Het adres updaten
//    nieuwAdres.setStraat("Nieuwe Straat");
//    adao.update(nieuwAdres);
//    System.out.println("[Test] Adres geüpdatet: " + adao.findByAdres_id(nieuwAdres.getAdres_id()));
//
//    // Het adres verwijderen
//    adao.delete(nieuwAdres);
//    System.out.println("[Test] Adres verwijderd: " + nieuwAdres);
//}
