package nl.hu.dp.P4H;

import nl.hu.dp.P4.OVChipkaartDAO;
import nl.hu.dp.P4.domain.OVChipkaart;
import nl.hu.dp.P4.domain.Reiziger;
import nl.hu.dp.P4H.domain.OVChipkaartHibernate;
import nl.hu.dp.P4H.domain.ReizigerHibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class OVChipkaartDAOHibernate implements OVChipkaartDAO {
    private Session session;

    public OVChipkaartDAOHibernate(Session session) {
        this.session = session;
    }

    @Override
    public boolean save(OVChipkaart ovChipkaart) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(ovChipkaart);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(OVChipkaart ovChipkaart) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(ovChipkaart);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(OVChipkaart ovChipkaart) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.delete(ovChipkaart);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public OVChipkaart findById(int kaartNummer) {
        return session.get(OVChipkaart.class, kaartNummer);
    }

    @Override
    public List<OVChipkaart> findByReiziger(Reiziger reiziger) {
        return session.createQuery("FROM OVChipkaart WHERE reiziger = :reiziger", OVChipkaart.class)
                .setParameter("reiziger", reiziger)
                .list();
    }

    @Override
    public List<OVChipkaart> findAll() {
        return session.createQuery("FROM OVChipkaart", OVChipkaart.class).list();
    }
}
