package nl.hu.dp.P4;

import nl.hu.dp.P4.domain.Reiziger;
import nl.hu.dp.P4.domain.OVChipkaart;

import java.util.List;

public interface OVChipkaartDAO {
    boolean save(OVChipkaart ovChipkaart);
    boolean update(OVChipkaart ovChipkaart);
    boolean delete(OVChipkaart ovChipkaart);
    List<OVChipkaart> findByReiziger(Reiziger reiziger);
    List<OVChipkaart> findAll();
}
