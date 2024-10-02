package nl.hu.dp.P3H;

import nl.hu.dp.P3H.domain.Reiziger;

import java.time.LocalDate;
import java.util.List;

public interface ReizigerDAOHibernate {
    boolean save(Reiziger reiziger);
    boolean update(Reiziger reiziger);
    boolean delete(Reiziger reiziger);
    Reiziger findByReiziger_id(int reiziger_id);
    List<Reiziger> findByGbdatum(LocalDate gbdatum);
    List<Reiziger> findAll();
}
