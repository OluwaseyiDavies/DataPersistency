package nl.hu.dp.P3;

import nl.hu.dp.P2.ReizigerDAOsql;
import nl.hu.dp.P3.AdresDAO;
import nl.hu.dp.P3.domain.Adres;
import nl.hu.dp.P2.domain.Reiziger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdresDAOsql implements AdresDAO {
    private Connection connection;

    public AdresDAOsql(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean save(Adres adres) {
        try {
            String query = "INSERT INTO adres (adres_id, huisnummer, straat, woonplaats, reiziger_id) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setInt(1, adres.getAdres_id());
            pst.setString(2, adres.getHuisnummer());
            pst.setString(3, adres.getStraat());
            pst.setString(4, adres.getWoonplaats());
            pst.setInt(5, adres.getReiziger().getId());
            pst.executeUpdate();
            pst.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Adres adres) {
        try {
            String query = "UPDATE adres SET huisnummer = ?, straat = ?, woonplaats = ? WHERE adres_id = ?";
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setString(1, adres.getHuisnummer());
            pst.setString(2, adres.getStraat());
            pst.setString(3, adres.getWoonplaats());
            pst.setInt(4, adres.getAdres_id());
            pst.executeUpdate();
            pst.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Adres adres) {
        try {
            String query = "DELETE FROM adres WHERE adres_id = ?";
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setInt(1, adres.getAdres_id());
            pst.executeUpdate();
            pst.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Adres findByReiziger(Reiziger reiziger) {
        try {
            String query = "SELECT * FROM adres WHERE reiziger_id = ?";
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setInt(1, reiziger.getId());
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                Adres adres = new Adres(
                        rs.getInt("adres_id"),
                        rs.getString("huisnummer"),
                        rs.getString("straat"),
                        rs.getString("woonplaats"),
                        reiziger
                );
                rs.close();
                pst.close();
                return adres;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Adres> findAll() {
        List<Adres> adressen = new ArrayList<>();
        try {
            String query = "SELECT * FROM adres";
            PreparedStatement pst = connection.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Reiziger reiziger = new ReizigerDAOsql(connection, this).findById(rs.getInt("reiziger_id"));
                Adres adres = new Adres(
                        rs.getInt("adres_id"),
                        rs.getString("huisnummer"),
                        rs.getString("straat"),
                        rs.getString("woonplaats"),
                        reiziger
                );
                adressen.add(adres);
            }
            rs.close();
            pst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return adressen;
    }

    @Override
    public Adres findById(int id) {
        try {
            String query = "SELECT * FROM adres WHERE adres_id = ?";
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                Reiziger reiziger = new ReizigerDAOsql(connection, this).findById(rs.getInt("reiziger_id"));
                Adres adres = new Adres(
                        rs.getInt("adres_id"),
                        rs.getString("huisnummer"),
                        rs.getString("straat"),
                        rs.getString("woonplaats"),
                        reiziger
                );
                rs.close();
                pst.close();
                return adres;
            }
            rs.close();
            pst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}














