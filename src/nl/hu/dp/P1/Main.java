package nl.hu.dp.P1;

import nl.hu.dp.P3.AdresDAO;
import nl.hu.dp.P3.ReizigerDAO;
import nl.hu.dp.P3.ReizigerDAOsql;
import nl.hu.dp.P3.domain.Adres;
import nl.hu.dp.P3.domain.Reiziger;
import nl.hu.dp.P3.AdresDAOsql;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class Main {
    private static Connection connection;

    public static void main(String[] args) throws SQLException {
        try {
            getConnection();
            AdresDAO adresDAO = new AdresDAOsql(connection);
            ReizigerDAO reizigerDAO = new ReizigerDAOsql(connection, adresDAO);
            testReizigersDAO(reizigerDAO);
        } finally {
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

    private static void testReizigersDAO(ReizigerDAO rdao) throws SQLException {
        System.out.println("\n---------- Test ReizigerDAO -------------");

        // Alle reizigers ophalen uit de database
        List<Reiziger> reizigers = rdao.findAll();
        System.out.println("[Test] ReizigerDAO.findAll() geeft de volgende reizigers:");
        for (Reiziger r : reizigers) {
            System.out.println(r);
        }

        // Een nieuwe reiziger en adres aanmaken en opslaan
        String gbdatum = "2002-09-17";
        Reiziger gerrit = new Reiziger(6, "O.", "Oluwatobi", "Davies", Date.valueOf(gbdatum));
        Adres adresGerrit = new Adres(6, "8232RV","1", "Lubeckstraat", "Lelystad", gerrit);
        gerrit.setAdres(adresGerrit);

        System.out.print("[Test] Eerst " + reizigers.size() + " reizigers, na ReizigerDAO.save() ");
        rdao.save(gerrit);
        reizigers = rdao.findAll();
        System.out.println(reizigers.size() + " reizigers\n");
    }
}
