package nl.hu.dp.P2H;

import nl.hu.dp.P2H.domain.Reiziger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class ReizigerDAOHibernate implements ReizigerDAO {
    private Session session;

    public ReizigerDAOHibernate(Session session) {
        this.session = session;
    }

    @Override
    public boolean save(Reiziger reiziger) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(reiziger);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Reiziger reiziger) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(reiziger);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Reiziger reiziger) {  // Hier is de public modifier toegevoegd
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.delete(reiziger);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Reiziger findById(int id) {
        return session.get(Reiziger.class, id);
    }

    @Override
    public List<Reiziger> findAll() {
        return session.createQuery("FROM Reiziger", Reiziger.class).list();
    }
}
