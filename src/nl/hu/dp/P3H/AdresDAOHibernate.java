package nl.hu.dp.P3H;

import nl.hu.dp.P3H.domain.Adres;
import nl.hu.dp.P3H.domain.Reiziger;

import java.util.List;

public interface AdresDAOHibernate {
    boolean save(Adres adres);
    boolean update(Adres adres);
    boolean delete(Adres adres);
    Adres findByAdres_id(int adres_id);
    Adres findByReiziger(int reiziger_id);
    List<Adres> findAll();
}
