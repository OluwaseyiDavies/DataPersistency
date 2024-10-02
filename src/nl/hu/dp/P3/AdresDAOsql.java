package nl.hu.dp.P3;

import nl.hu.dp.P3.domain.Adres;
import nl.hu.dp.P3.domain.Reiziger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdresDAOsql implements AdresDAO {
    private Connection connection;
    private ReizigerDAO rdao;

    public AdresDAOsql(Connection connection, ReizigerDAO rdao) {
        this.connection = connection;
        this.rdao = rdao;
    }

    @Override
    public boolean save(Adres adres) {
        try {
            String query = "INSERT INTO adres (adres_id, postcode, huisnummer, straat, woonplaats, reiziger_id) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setInt(1, adres.getAdres_id());
            pst.setString(2, adres.getPostcode());
            pst.setString(3, adres.getHuisnummer());
            pst.setString(4, adres.getStraat());
            pst.setString(5, adres.getWoonplaats());
            pst.setInt(6, adres.getReiziger().getReiziger_id());
            pst.executeUpdate();
            pst.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Adres adres) {
        try {
            String query = "UPDATE adres SET postcode=?, huisnummer=?, straat=?, woonplaats=? WHERE adres_id=?";
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setString(1, adres.getPostcode());
            pst.setString(2, adres.getHuisnummer());
            pst.setString(3, adres.getStraat());
            pst.setString(4, adres.getWoonplaats());
            pst.setInt(5, adres.getAdres_id());
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
            String query = "DELETE FROM adres WHERE adres_id=?";
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
            String query = "SELECT * FROM adres WHERE reiziger_id=?";
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setInt(1, reiziger.getReiziger_id());
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                Adres adres = new Adres(
                        rs.getInt("adres_id"),
                        rs.getString("postcode"),
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

    @Override
    public List<Adres> findAll() {
        List<Adres> adressen = new ArrayList<>();
        List<Reiziger> reizigers = rdao.findAll();

        try {
            for (Reiziger reiziger : reizigers) {
                String query = "SELECT * FROM adres WHERE reiziger_id=?";
                PreparedStatement pst = connection.prepareStatement(query);
                pst.setInt(1, reiziger.getReiziger_id());
                ResultSet rs = pst.executeQuery();

                if (rs.next()) {
                    Adres adres = new Adres(
                            rs.getInt("adres_id"),
                            rs.getString("postcode"),
                            rs.getString("huisnummer"),
                            rs.getString("straat"),
                            rs.getString("woonplaats"),
                            reiziger
                    );
                    adressen.add(adres);
                }
                rs.close();
                pst.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return adressen;
    }
}























