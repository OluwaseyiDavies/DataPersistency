package nl.hu.dp.P4;

import nl.hu.dp.P4.domain.OVChipkaartH;
import nl.hu.dp.P4.domain.Reiziger;
import java.util.List;

public interface OVChipkaartDAO {
    boolean save(OVChipkaartH ovChipkaart);
    boolean update(OVChipkaartH ovChipkaart);
    boolean delete(OVChipkaartH ovChipkaart);
    OVChipkaartH findById(int kaartNummer);
    List<OVChipkaartH> findByReiziger(Reiziger reiziger);
    List<OVChipkaartH> findAll();
}