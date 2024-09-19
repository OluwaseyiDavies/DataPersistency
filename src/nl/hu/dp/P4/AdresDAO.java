package nl.hu.dp.P4;

import nl.hu.dp.P4.domain.Reiziger;
import nl.hu.dp.P4.domain.Adres;

import java.util.List;

public interface AdresDAO {
    boolean save(Adres adres);
    boolean update(Adres adres);
    boolean delete(Adres adres);
    Adres findById(int id);
    Adres findByReiziger(Reiziger reiziger);
    List<Adres> findAll();
}
