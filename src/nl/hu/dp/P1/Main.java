package nl.hu.dp.P1;

//import nl.hu.dp.P2.domain.Reiziger;
//import nl.hu.dp.P2.ReizigerDAO;
//import nl.hu.dp.P2.ReizigerDAOsql;

//import nl.hu.dp.P2H.domain.Reiziger;
//import nl.hu.dp.P2H.ReizigerDAOHibernate;
//import nl.hu.dp.P2H.ReizigerDAOsqlHibernate;

//import nl.hu.dp.P3.domain.Adres;
//import nl.hu.dp.P3.domain.Reiziger;
//import nl.hu.dp.P3.AdresDAO;
//import nl.hu.dp.P3.AdresDAOsql;
//import nl.hu.dp.P3.ReizigerDAO;
//import nl.hu.dp.P3.ReizgerDAOsql;

//import nl.hu.dp.P3H.domain.Adres;
//import nl.hu.dp.P3H.domain.Reiziger;
//import nl.hu.dp.P3H.AdresDAOHibernate;
//import nl.hu.dp.P3H.AdresDAOsqlHibernate;
//import nl.hu.dp.P3H.ReizigerDAOHibernate;
//import nl.hu.dp.P3H.ReizigerDAOsqlHibernate;

//import nl.hu.dp.P4.domain.OVChipkaart;
//import nl.hu.dp.P4.domain.Reiziger;
//import nl.hu.dp.P4.OVChipkaartDAO;
//import nl.hu.dp.P4.OVChipkaartDAOsql;
//import nl.hu.dp.P4.ReizigerDAO;
//import nl.hu.dp.P4.ReizigerDAOsql;

import nl.hu.dp.P4H.domain.OVChipkaart;
import nl.hu.dp.P4H.domain.Reiziger;
import nl.hu.dp.P4H.OVChipkaartDAOHibernate;
import nl.hu.dp.P4H.OVChipkaartDAOsqlHibernate;
import nl.hu.dp.P4H.ReizigerDAOHibernate;
import nl.hu.dp.P4H.ReizigerDAOsqlHibernate;

import java.sql.*;
import java.time.LocalDate;
import java.util.List;

import nl.hu.dp.P4H.OVChipkaartDAOsqlHibernate;
import nl.hu.dp.P4H.ReizigerDAOsqlHibernate;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main {
    private static Connection connection;

    public static void main(String[] args) throws SQLException {
        try {
            getConnection();
//            Gebruik om Hibernate code te testen
            SessionFactory sessionFactory = new Configuration().configure("/nl/hu/dp/hibernate.cfg.xml").buildSessionFactory();

//            P2
//            ReizigerDAOsql reizigerDAO = new ReizigerDAOsql(connection);
//            testReizigerDAOHibernate(reizigerDAO);

//            P2H
//            ReizigerDAOsqlHibernate reizigerDAOHibernate = new ReizigerDAOsqlHibernate(connection, sessionFactory);
//            testReizigerDAOHibernate(reizigerDAOHibernate);

//            P3
//            ReizgerDAOsql reizigerDAO = new ReizgerDAOsql(connection);
//            AdresDAOsql adresDAO = new AdresDAOsql(connection, reizigerDAO);
//            testReizigerDAO(reizigerDAO, adresDAO);


//            P3H
//            ReizigerDAOsqlHibernate reizigerDAOHibernate = new ReizigerDAOsqlHibernate(connection, sessionFactory);
//            AdresDAOsqlHibernate adresDAOHibernate = new AdresDAOsqlHibernate(connection, sessionFactory);

//            P4
//            ReizigerDAOsql reizgerDAO = new ReizigerDAOsql(connection);
//            OVChipkaartDAOsql ovChipkaartDAO = new OVChipkaartDAOsql(connection, reizgerDAO);
//            testReizigerEnOVChipkaart(reizgerDAO, ovChipkaartDAO);

//            P4H
//            ReizigerDAOsqlHibernate reizigerDAOHibernate = new ReizigerDAOsqlHibernate(connection, sessionFactory);
//            OVChipkaartDAOsqlHibernate ovChipkaartDAOHibernate = new OVChipkaartDAOsqlHibernate(connection, sessionFactory);
//            testReizigerEnOVChipkaartHibernate(reizigerDAOHibernate, ovChipkaartDAOHibernate);

            testConnection();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
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

// Test voor P2
//    private static void testReizigersDAO(ReizigerDAOsql rdao) throws SQLException {
//        System.out.println("\n---------- Test ReizigerDAO -------------");
//
//        // Alle reizigers ophalen uit de database
//        List<Reiziger> reizigers = rdao.findAll();
//        System.out.println("[Test] ReizigerDAO.findAll() geeft de volgende reizigers:");
//        for (Reiziger r: reizigers) {
//            System.out.println(r);
//        }
//
//        //Een nieuwe reiziger aanmaken en opslaan in de database
//        String gbdatum = "1981-03-14";
//        Reiziger sietske = new Reiziger(582911, "S.", "", "Boers", java.sql.Date.valueOf(gbdatum));
//        System.out.print("[Test] Eerst " + reizigers.size() + " reizigers, na ReizigerDAO.save() ");
//        rdao.save(sietske);
//        reizigers = rdao.findAll();
//        System.out.println(reizigers.size() + " reizigers\n");
//    }
//
//    Test voor P2H
//    private static void testReizigerDAOHibernate(ReizigerDAOsqlHibernate rdao) throws SQLException {
//        System.out.println("\n---------- Test ReizigerDAOHibernate -------------");
//
//        // Alle reizigers ophalen uit de database
//        List<Reiziger> reizigers = rdao.findAll();
//        System.out.println("[Test] ReizigerDAO.findAll() geeft de volgende reizigers:");
//        for (Reiziger r: reizigers) {
//            System.out.println(r);
//        }
//
//        // Een nieuwe reiziger aanmaken en opslaan in de database
//        LocalDate gbdatum = LocalDate.of(1981, 3, 14);
//        Reiziger sietske = new Reiziger(582911, "S.", "", "Boers", gbdatum);
//        System.out.print("[Test] Eerst " + reizigers.size() + " reizigers, na ReizigerDAOHibernate.save() ");
//        rdao.save(sietske);
//        reizigers = rdao.findAll();
//        System.out.println(reizigers.size() + " reizigers\n");
//    }
//
//    Test voor P3
//    private static void testReizigerDAO(ReizgerDAOsql rdao, AdresDAOsql adao) throws SQLException {
//        System.out.println("\n---------- Test ReizigerDAO -------------");
//
//        // Alle reizigers ophalen uit de database
//        List<Reiziger> reizigers = rdao.findAll();
//        System.out.println("[Test] ReizigerDAO.findAll() geeft de volgende reizigers:");
//
//        for (Reiziger reiziger : reizigers) {
//            // Voor elke reiziger zoeken we het bijbehorende adres
//            Adres adres = adao.findByReiziger(reiziger);
//            reiziger.setAdres(adres); // Verbind het adres met de reiziger
//
//            // Reiziger en adres printen in het gewenste formaat
//            if (adres != null) {
//                System.out.printf("Reiziger {#%d %s, geb. %s, Adres {#%d %s-%s}}\n",
//                        reiziger.getReiziger_id(),
//                        reiziger.getVoorletters() + (reiziger.getTussenvoegsel() != null ? " " + reiziger.getTussenvoegsel() : "") + " " + reiziger.getAchternaam(),
//                        reiziger.getGeboortedatum(),
//                        adres.getAdres_id(),
//                        adres.getPostcode(),
//                        adres.getHuisnummer()
//                );
//            } else {
//                System.out.printf("Reiziger {#%d %s, geb. %s, Geen adres}\n",
//                        reiziger.getReiziger_id(),
//                        reiziger.getVoorletters() + (reiziger.getTussenvoegsel() != null ? " " + reiziger.getTussenvoegsel() : "") + " " + reiziger.getAchternaam(),
//                        reiziger.getGeboortedatum()
//                );
//            }
//        }
//    }
//
//    Test voor P3H
//    private static void testReizigerDAOHibernate(ReizigerDAOsqlHibernate rdao, AdresDAOsqlHibernate adao) throws SQLException {
//        System.out.println("\n---------- Test ReizigerDAOHibernate -------------");
//
//        // Alle reizigers ophalen uit de database
//        List<Reiziger> reizigers = rdao.findAll();
//        System.out.println("[Test] ReizigerDAO.findAll() geeft de volgende reizigers:");
//
//        for (Reiziger reiziger : reizigers) {
//            // Voor elke reiziger zoeken we het bijbehorende adres
//            Adres adres = adao.findByReiziger(reiziger.getReiziger_id());
//            reiziger.setAdres(adres); // Verbind het adres met de reiziger
//
//            // Reiziger en adres printen in het gewenste formaat
//            if (adres != null) {
//                System.out.printf("Reiziger {#%d %s, geb. %s, Adres {#%d %s-%s}}\n",
//                        reiziger.getReiziger_id(),
//                        reiziger.getVoorletters() + (reiziger.getTussenvoegsel() != null ? " " + reiziger.getTussenvoegsel() : "") + " " + reiziger.getAchternaam(),
//                        reiziger.getGeboortedatum(),
//                        adres.getAdres_id(),
//                        adres.getPostcode(),
//                        adres.getHuisnummer()
//                );
//            } else {
//                System.out.printf("Reiziger {#%d %s, geb. %s, Geen adres}\n",
//                        reiziger.getReiziger_id(),
//                        reiziger.getVoorletters() + (reiziger.getTussenvoegsel() != null ? " " + reiziger.getTussenvoegsel() : "") + " " + reiziger.getAchternaam(),
//                        reiziger.getGeboortedatum()
//                );
//            }
//        }
//    }
//
//    Test voor P4
//    private static void testReizigerEnOVChipkaart(ReizigerDAOsql rdao, OVChipkaartDAOsql ovdao) throws SQLException {
//        System.out.println("\n---------- Test ReizigerDAO & OVChipkaartDAO -------------");
//
//        // Alle reizigers ophalen uit de database
//        List<Reiziger> reizigers = rdao.findAll();
//        System.out.println("[Test] ReizigerDAO.findAll() geeft de volgende reizigers:");
//
//        for (Reiziger reiziger : reizigers) {
//            // Voor elke reiziger zoeken we de bijbehorende OV-chipkaarten
//            List<OVChipkaart> ovChipkaarten = ovdao.findByReiziger(reiziger);
//            reiziger.getOVChipkaarten().addAll(ovChipkaarten); // Verbind de OV-chipkaarten met de reiziger
//
//            // Reiziger en OV-chipkaarten printen in het gewenste formaat
//            if (!ovChipkaarten.isEmpty()) {
//                System.out.printf("Reiziger {#%d %s, geb. %s, OVChipkaarten: %s}\n",
//                        reiziger.getReiziger_id(),
//                        reiziger.getVoorletters() + (reiziger.getTussenvoegsel() != null ? " " + reiziger.getTussenvoegsel() : "") + " " + reiziger.getAchternaam(),
//                        reiziger.getGeboortedatum(),
//                        ovChipkaarten);
//            } else {
//                System.out.printf("Reiziger {#%d %s, geb. %s, geen OVChipkaarten}\n",
//                        reiziger.getReiziger_id(),
//                        reiziger.getVoorletters() + (reiziger.getTussenvoegsel() != null ? " " + reiziger.getTussenvoegsel() : "") + " " + reiziger.getAchternaam(),
//                        reiziger.getGeboortedatum());
//            }
//        }
//    }
//
//    Test voor P4H
//    private static void testReizigerEnOVChipkaartHibernate(ReizigerDAOsqlHibernate rdao, OVChipkaartDAOsqlHibernate ovdao) throws SQLException {
//        System.out.println("\n---------- Test ReizigerDAOHibernate & OVChipkaartDAOHibernate -------------");
//
//        // Alle reizigers ophalen uit de database
//        List<Reiziger> reizigers = rdao.findAll();
//        System.out.println("[Test] ReizigerDAO.findAll() geeft de volgende reizigers:");
//
//        for (Reiziger reiziger : reizigers) {
//            // Voor elke reiziger zoeken we de bijbehorende OV-chipkaarten
//            List<OVChipkaart> ovChipkaarten = reiziger.getOvChipkaart(); // Omdat de relatie is gemapt
//            reiziger.getOvChipkaart().addAll(ovChipkaarten); // Verbind de OV-chipkaarten met de reiziger
//
//            // Reiziger en OV-chipkaarten printen in het gewenste formaat
//            if (!ovChipkaarten.isEmpty()) {
//                System.out.printf("Reiziger {#%d %s, geb. %s, OVChipkaarten: %s}\n",
//                        reiziger.getReiziger_id(),
//                        reiziger.getVoorletters() + (reiziger.getTussenvoegsel() != null ? " " + reiziger.getTussenvoegsel() : "") + " " + reiziger.getAchternaam(),
//                        reiziger.getGeboortedatum(),
//                        ovChipkaarten);
//            } else {
//                System.out.printf("Reiziger {#%d %s, geb. %s, geen OVChipkaarten}\n",
//                        reiziger.getReiziger_id(),
//                        reiziger.getVoorletters() + (reiziger.getTussenvoegsel() != null ? " " + reiziger.getTussenvoegsel() : "") + " " + reiziger.getAchternaam(),
//                        reiziger.getGeboortedatum());
//            }
//        }
//    }
}
