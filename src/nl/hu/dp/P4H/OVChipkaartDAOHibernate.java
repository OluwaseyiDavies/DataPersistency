package nl.hu.dp.P4H;

import nl.hu.dp.P4H.domain.OVChipkaart;

import java.util.List;

public interface OVChipkaartDAOHibernate {
    boolean save(OVChipkaart ovChipkaart);
    boolean update(OVChipkaart ovChipkaart);
    boolean delete(OVChipkaart ovChipkaart);
    OVChipkaart findByKaart_nummer(int kaart_nummer);
    List<OVChipkaart> findAll();
}
