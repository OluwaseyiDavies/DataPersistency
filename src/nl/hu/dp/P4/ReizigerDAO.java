package nl.hu.dp.P4;
import nl.hu.dp.P4.domain.Reiziger;

import java.sql.Date;
import java.util.List;

public interface ReizigerDAO {
        boolean save(Reiziger reiziger);
        boolean update(Reiziger reiziger);
        boolean delete(Reiziger reiziger);
        Reiziger findById(int id);
        List<Reiziger> findAll();

    List<Reiziger> findByGbdatum(Date geboortedatum);
}
