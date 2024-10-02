package nl.hu.dp.P3H;

import nl.hu.dp.P3H.domain.Adres;
import nl.hu.dp.P3H.AdresDAOHibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.sql.Connection;
import java.util.List;

public class AdresDAOsqlHibernate implements AdresDAOHibernate {

    private SessionFactory sessionFactory;
    private Connection connection;

    public AdresDAOsqlHibernate(Connection connection, SessionFactory sessionFactory) {
        this.connection = connection;
        this.sessionFactory = sessionFactory;
    }

    @Override
    public boolean save(Adres adres) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(adres);
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
    public boolean update(Adres adres) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(adres);
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
    public boolean delete(Adres adres) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.delete(adres);
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
    public Adres findByAdres_id(int adres_id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Adres.class, adres_id);
        }
    }

    @Override
    public Adres findByReiziger(int reiziger_id) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Adres WHERE reiziger.reiziger_id = :reiziger_id", Adres.class)
                    .setParameter("reiziger_id", reiziger_id)
                    .uniqueResult();
        }
    }

    @Override
    public List<Adres> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Adres", Adres.class).list();
        }
    }
}










