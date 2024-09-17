package nl.hu.dp.P4H;

import nl.hu.dp.P3H.AdresDAO;
import nl.hu.dp.P3H.domain.Adres;
import nl.hu.dp.P3H.domain.Reiziger;
import nl.hu.dp.P4H.domain.AdresHibernate;
import nl.hu.dp.P4H.domain.ReizigerHibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class AdresDAOHibernate implements AdresDAO {
    private Session session;

    public AdresDAOHibernate(Session session) {
        this.session = session;
    }

    @Override
    public boolean save(Adres adres) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(adres);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Adres adres) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(adres);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Adres adres) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.delete(adres);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Adres findById(int id) {
        return session.get(Adres.class, id);
    }

    @Override
    public Adres findByReiziger(Reiziger reiziger) {
        return session.createQuery("FROM Adres WHERE reiziger = :reiziger", Adres.class)
                .setParameter("reiziger", reiziger)
                .uniqueResult();
    }

    @Override
    public List<Adres> findAll() {
        return session.createQuery("FROM Adres", Adres.class).list();
    }
}
