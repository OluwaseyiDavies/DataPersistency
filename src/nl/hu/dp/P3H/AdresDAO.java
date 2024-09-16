package nl.hu.dp.P3H;

import nl.hu.dp.P3H.domain.Adres;
import nl.hu.dp.P3H.domain.Reiziger;

import java.util.List;

public interface AdresDAO {
    boolean save(Adres adres);
    boolean update(Adres adres);
    boolean delete(Adres adres);
    Adres findById(int id);
    Adres findByReiziger(Reiziger reiziger);
    List<Adres> findAll();
}