package nl.hu.dp.P2H;

import nl.hu.dp.P2H.domain.Reiziger;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;

public class ReizigerDAOsqlHibernate implements ReizigerDAOHibernate {

    private SessionFactory sessionFactory;
    private Connection connection;

    public ReizigerDAOsqlHibernate(Connection connection, SessionFactory sessionFactory) {
        this.connection = connection;
        this.sessionFactory = sessionFactory;
    }

    @Override
    public boolean save (Reiziger reiziger) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(reiziger);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update (Reiziger reiziger) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(reiziger);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete (Reiziger reiziger) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.delete(reiziger);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Reiziger findByReiziger_id(int reiziger_id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Reiziger.class, reiziger_id);
        }
    }

    @Override
    public List<Reiziger> findByGbdatum(LocalDate gbdatum) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Reiziger WHERE geboortedatum = :datum", Reiziger.class)
                    .setParameter("datum", gbdatum)
                    .getResultList();
        }
    }

    @Override
    public List<Reiziger> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Reiziger", Reiziger.class).list();
        }
    }
}
