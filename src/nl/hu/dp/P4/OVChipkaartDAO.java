package nl.hu.dp.P4;

import nl.hu.dp.P4.domain.OVChipkaart;
import nl.hu.dp.P4.domain.Reiziger;
import java.util.List;

public interface OVChipkaartDAO {
    boolean save(OVChipkaart ovChipkaart);
    boolean update(OVChipkaart ovChipkaart);
    boolean delete(OVChipkaart ovChipkaart);
    OVChipkaart findById(int kaartNummer);
    List<OVChipkaart> findByReiziger(Reiziger reiziger);
    List<OVChipkaart> findAll();
}