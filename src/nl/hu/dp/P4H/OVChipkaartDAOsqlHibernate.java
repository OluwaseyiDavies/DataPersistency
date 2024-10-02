package nl.hu.dp.P4H;

import nl.hu.dp.P4H.domain.OVChipkaart;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.sql.Connection;
import java.util.List;

public class OVChipkaartDAOsqlHibernate implements OVChipkaartDAOHibernate {

    private SessionFactory sessionFactory;
    private Connection connection;

    public OVChipkaartDAOsqlHibernate(Connection connection, SessionFactory sessionFactory) {
        this.connection = connection;
        this.sessionFactory = sessionFactory;
    }

    @Override
    public boolean save(OVChipkaart ovChipkaart) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(ovChipkaart);
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
    public boolean update(OVChipkaart ovChipkaart) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(ovChipkaart);
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
    public boolean delete(OVChipkaart ovChipkaart) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.delete(ovChipkaart);
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
    public OVChipkaart findByKaart_nummer(int kaart_nummer) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(OVChipkaart.class, kaart_nummer);
        }
    }

    @Override
    public List<OVChipkaart> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM OVChipkaart", OVChipkaart.class).list();
        }
    }
}
