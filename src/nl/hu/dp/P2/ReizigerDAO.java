package nl.hu.dp.P2;

import nl.hu.dp.P2.domain.Reiziger;

import java.sql.Date;
import java.util.List;

public interface ReizigerDAO {
    boolean save(Reiziger reiziger);
    boolean update(Reiziger reiziger);
    boolean delete(Reiziger reiziger);
    Reiziger findById(int id);
    List<Reiziger> findByGbdatum(Date datum);
    List<Reiziger> findAll();
}