package nl.hu.dp.P4;

import nl.hu.dp.P4.domain.OVChipkaartH;
import nl.hu.dp.P4.domain.Reiziger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OVChipkaartDAOPsql implements OVChipkaartDAO {
    private Connection connection;

    public OVChipkaartDAOPsql(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean save(OVChipkaartH ovChipkaart) {
        try {
            String query = "INSERT INTO ov_chipkaart (kaart_nummer, geldig_tot, klasse, saldo, reiziger_id) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setInt(1, ovChipkaart.getKaartNummer());
            pst.setDate(2, new java.sql.Date(ovChipkaart.getGeldigTot().getTime()));
            pst.setInt(3, ovChipkaart.getKlasse());
            pst.setDouble(4, ovChipkaart.getSaldo());
            pst.setInt(5, ovChipkaart.getReiziger().getId());
            pst.executeUpdate();
            pst.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(OVChipkaartH ovChipkaart) {
        try {
            String query = "UPDATE ov_chipkaart SET geldig_tot = ?, klasse = ?, saldo = ?, reiziger_id = ? WHERE kaart_nummer = ?";
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setDate(1, new java.sql.Date(ovChipkaart.getGeldigTot().getTime()));
            pst.setInt(2, ovChipkaart.getKlasse());
            pst.setDouble(3, ovChipkaart.getSaldo());
            pst.setInt(4, ovChipkaart.getReiziger().getId());
            pst.setInt(5, ovChipkaart.getKaartNummer());
            pst.executeUpdate();
            pst.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(OVChipkaartH ovChipkaart) {
        try {
            String query = "DELETE FROM ov_chipkaart WHERE kaart_nummer = ?";
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setInt(1, ovChipkaart.getKaartNummer());
            pst.executeUpdate();
            pst.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public OVChipkaartH findById(int kaartNummer) {
        try {
            String query = "SELECT * FROM ov_chipkaart WHERE kaart_nummer = ?";
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setInt(1, kaartNummer);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                Reiziger reiziger = new ReizigerDAOsql(connection, null).findById(rs.getInt("reiziger_id"));
                OVChipkaartH ovChipkaart = new OVChipkaartH(
                        rs.getDate("geldig_tot"),
                        rs.getInt("klasse"),
                        rs.getDouble("saldo"),
                        reiziger
                );
                ovChipkaart.setKaartNummer(rs.getInt("kaart_nummer"));
                rs.close();
                pst.close();
                return ovChipkaart;
            }
            rs.close();
            pst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<OVChipkaartH> findByReiziger(Reiziger reiziger) {
        List<OVChipkaartH> ovChipkaarten = new ArrayList<>();
        try {
            String query = "SELECT * FROM ov_chipkaart WHERE reiziger_id = ?";
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setInt(1, reiziger.getId());
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                OVChipkaartH ovChipkaart = new OVChipkaartH(
                        rs.getDate("geldig_tot"),
                        rs.getInt("klasse"),
                        rs.getDouble("saldo"),
                        reiziger
                );
                ovChipkaart.setKaartNummer(rs.getInt("kaart_nummer"));
                ovChipkaarten.add(ovChipkaart);
            }
            rs.close();
            pst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ovChipkaarten;
    }

    @Override
    public List<OVChipkaartH> findAll() {
        List<OVChipkaartH> ovChipkaarten = new ArrayList<>();
        try {
            String query = "SELECT * FROM ov_chipkaart";
            PreparedStatement pst = connection.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Reiziger reiziger = new ReizigerDAOsql(connection, null).findById(rs.getInt("reiziger_id"));
                OVChipkaartH ovChipkaart = new OVChipkaartH(
                        rs.getDate("geldig_tot"),
                        rs.getInt("klasse"),
                        rs.getDouble("saldo"),
                        reiziger
                );
                ovChipkaart.setKaartNummer(rs.getInt("kaart_nummer"));
                ovChipkaarten.add(ovChipkaart);
            }
            rs.close();
            pst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ovChipkaarten;
    }
}
