package nl.hu.dp.P3;

import nl.hu.dp.P3.domain.Adres;
import nl.hu.dp.P3.domain.Reiziger;
import java.util.List;

public interface AdresDAO {
    boolean save(Adres adres);
    boolean update(Adres adres);
    boolean delete(Adres adres);
    Adres findByReiziger(Reiziger reiziger);
    List<Adres> findAll();
}
