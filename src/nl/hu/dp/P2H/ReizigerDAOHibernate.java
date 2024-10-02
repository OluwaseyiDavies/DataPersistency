package nl.hu.dp.P2H;

import java.time.LocalDate;
import java.util.List;
import nl.hu.dp.P2H.domain.Reiziger;

public interface ReizigerDAOHibernate {
    boolean save(Reiziger reiziger);
    boolean update(Reiziger reiziger);
    boolean delete(Reiziger reiziger);
    Reiziger findByReiziger_id(int reiziger_id);
    List<Reiziger> findByGbdatum(LocalDate gbdatum);
    List<Reiziger> findAll();
}
