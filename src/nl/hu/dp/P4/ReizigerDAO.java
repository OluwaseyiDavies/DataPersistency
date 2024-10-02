package nl.hu.dp.P4;

import nl.hu.dp.P4.domain.Reiziger;

import java.time.LocalDate;
import java.util.List;

public interface ReizigerDAO {
    boolean save(Reiziger reiziger);
    boolean update(Reiziger reiziger);
    boolean delete(Reiziger reiziger);
    Reiziger findByReizigers_id(int reiziger_id);
    List<Reiziger> findByGbdatum(LocalDate datum);
    List<Reiziger> findAll();
}
