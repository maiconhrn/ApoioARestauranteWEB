package br.uem.apoioarestaurante.dao.generic.impl;

import br.uem.apoioarestaurante.dao.generic.intf.BasicGenericDAO;
import br.uem.apoioarestaurante.exceptions.DAOException;
import br.uem.apoioarestaurante.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.Serializable;
import java.util.List;

/**
 * @author Maicon
 */
public class HibernateBasicGenericDAO<T> implements BasicGenericDAO<T> {

    private Class<T> entityClass;
    private Session session;

    protected HibernateBasicGenericDAO(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    private void requireOpenSession() throws DAOException {
        if (this.session == null || !this.session.isOpen()) {
            throw new DAOException("This method call need an open session to work");
        }
    }

    private boolean openSession() {
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            return true;
        } catch (Exception e) {
            System.out.println("Error while attempt to initiate hibernate session: " + e);
            return false;
        }
    }

    private boolean closeSession() {
        if (this.session.isOpen()) {
            this.session.close();

            return true;
        }

        return false;
    }

    @Override
    public boolean connect() {
        return this.session != null ? this.session.isOpen() || openSession() : openSession();
    }

    @Override
    public T save(T t) {
        Transaction transaction = null;

        try {
            requireOpenSession();

            transaction = this.session.beginTransaction();
            this.session.save(t);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

        return t;
    }

    @Override
    public T update(T t) {
        Transaction transaction = null;

        try {
            requireOpenSession();

            transaction = this.session.beginTransaction();
            this.session.update(t);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

        return t;
    }

    @Override
    public boolean delete(T t) {
        Transaction transaction = null;

        try {
            requireOpenSession();

            transaction = this.session.beginTransaction();
            this.session.delete(t);
            transaction.commit();

            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public List<T> listAll() {
        try {
            requireOpenSession();

            String query = "from " + entityClass.getSimpleName();
            return this.session.createQuery(query, entityClass).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean disconnect() {
        return this.session == null || closeSession();
    }
}
