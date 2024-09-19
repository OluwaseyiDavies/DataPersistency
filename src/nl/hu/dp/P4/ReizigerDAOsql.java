package nl.hu.dp.P4;

import nl.hu.dp.P4.domain.Reiziger;
import nl.hu.dp.P4.domain.Adres;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReizigerDAOsql implements ReizigerDAO {
    private Connection connection;
    private AdresDAO adresDAO;

    public ReizigerDAOsql(Connection connection, AdresDAO adresDAO) {
        this.connection = connection;
        this.adresDAO = adresDAO;
    }

    @Override
    public boolean save(Reiziger reiziger) {
        try {
            String query = "INSERT INTO reiziger (reiziger_id, voorletters, tussenvoegsel, achternaam, geboortedatum) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setInt(1, reiziger.getId());
            pst.setString(2, reiziger.getVoorletters());
            pst.setString(3, reiziger.getTussenvoegsel());
            pst.setString(4, reiziger.getAchternaam());
            pst.setDate(5, reiziger.getGeboortedatum());
            pst.executeUpdate();
            pst.close();

            // Opslaan van het bijbehorende adres
            if (reiziger.getAdres() != null) {
                adresDAO.save(reiziger.getAdres());
            }

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Reiziger reiziger) {
        try {
            String query = "UPDATE reiziger SET voorletters = ?, tussenvoegsel = ?, achternaam = ?, geboortedatum = ? WHERE reiziger_id = ?";
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setString(1, reiziger.getVoorletters());
            pst.setString(2, reiziger.getTussenvoegsel());
            pst.setString(3, reiziger.getAchternaam());
            pst.setDate(4, reiziger.getGeboortedatum());
            pst.setInt(5, reiziger.getId());
            pst.executeUpdate();
            pst.close();

            // Update het bijbehorende adres
            if (reiziger.getAdres() != null) {
                adresDAO.update(reiziger.getAdres());
            }

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Reiziger reiziger) {
        try {
            // Verwijder eerst het bijbehorende adres
            if (reiziger.getAdres() != null) {
                adresDAO.delete(reiziger.getAdres());
            }

            String query = "DELETE FROM reiziger WHERE reiziger_id = ?";
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setInt(1, reiziger.getId());
            pst.executeUpdate();
            pst.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Reiziger findById(int id) {
        try {
            String query = "SELECT * FROM reiziger WHERE reiziger_id = ?";
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                Reiziger reiziger = new Reiziger(rs.getInt("reiziger_id"),
                        rs.getString("voorletters"),
                        rs.getString("tussenvoegsel"),
                        rs.getString("achternaam"),
                        rs.getDate("geboortedatum"));

                // Haal het bijbehorende adres op
                Adres adres = adresDAO.findByReiziger(reiziger);
                reiziger.setAdres(adres);

                rs.close();
                pst.close();
                return reiziger;
            }
            rs.close();
            pst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Reiziger> findAll() {
        List<Reiziger> reizigers = new ArrayList<>();
        try {
            String query = "SELECT * FROM reiziger";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Reiziger reiziger = new Reiziger(rs.getInt("reiziger_id"),
                        rs.getString("voorletters"),
                        rs.getString("tussenvoegsel"),
                        rs.getString("achternaam"),
                        rs.getDate("geboortedatum"));

                // Haal het bijbehorende adres op
                Adres adres = adresDAO.findByReiziger(reiziger);
                reiziger.setAdres(adres);

                reizigers.add(reiziger);
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reizigers;
    }

    @Override
    public List<Reiziger> findByGbdatum(Date geboortedatum) {
        List<Reiziger> reizigers = new ArrayList<>();
        try {
            String query = "SELECT * FROM reiziger WHERE geboortedatum = ?";
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setDate(1, geboortedatum);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Reiziger reiziger = new Reiziger(rs.getInt("reiziger_id"),
                        rs.getString("voorletters"),
                        rs.getString("tussenvoegsel"),
                        rs.getString("achternaam"),
                        rs.getDate("geboortedatum"));

                // Haal het bijbehorende adres op
                Adres adres = adresDAO.findByReiziger(reiziger);
                reiziger.setAdres(adres);

                reizigers.add(reiziger);
            }
            rs.close();
            pst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reizigers;
    }
}
