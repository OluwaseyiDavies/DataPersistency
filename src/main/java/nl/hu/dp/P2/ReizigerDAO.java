package nl.hu.dp.P2;

import nl.hu.dp.P2.domain.Reiziger;

import java.time.LocalDate;
import java.util.List;

public interface ReizigerDAO {
    boolean save(Reiziger reiziger);
    boolean update(Reiziger reiziger);
    boolean delete(Reiziger reiziger);
    Reiziger findByReiziger_id(int reiziger_id);
    List<Reiziger> findByGbdatum(LocalDate datum);
    List<Reiziger> findAll();
}
