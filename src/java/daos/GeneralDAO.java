/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import models.Account;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author Reza
 */
public class GeneralDAO<T> implements IGeneralDAO<T>{

    private Transaction transaction;
    private Session session;
    private SessionFactory factory;
    private T table;
    
    public GeneralDAO(SessionFactory factory, Class<T> table) {
        try {
            this.factory = factory;
            this.table = table.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<T> getAll() {
        List<T> reg = new ArrayList<>();
        try {
            session = factory.openSession();
            transaction = session.beginTransaction();
            reg = session.createQuery("from " + table.getClass().getSimpleName()).list();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
        return reg;
    }
    
    public T getById(Object id) {
        T t = null;
        try {
            session = factory.openSession();
            transaction = session.beginTransaction();
            Query querry = session.createQuery("FROM " + table.getClass().getSimpleName() + " where id =:id");
            querry.setParameter("id", id);
            t = (T) querry.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
        return t;
    }

    public boolean saveOrDelete(T model, boolean issave) {
        boolean result = false;
        try {
            session = factory.openSession();
            transaction = session.beginTransaction();
            if (issave) {
                session.saveOrUpdate(model);
            } else {
                session.delete(model);
            }
            transaction.commit();
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return result;
    }

    @Override
    public T getByUsername(Object username) {
         T t = null;
        try {
            session = factory.openSession();
            transaction = session.beginTransaction();
            Query querry = session.createQuery("FROM " + table.getClass().getSimpleName() + " where username =:id");
            querry.setParameter("id", username);
            t = (T) querry.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
        return t;
    }
    
    
    @Override
    public T getByEmail(Object mail) {
         T t = null;
        try {
            session = factory.openSession();
            transaction = session.beginTransaction();
            Query querry = session.createQuery("FROM " + table.getClass().getSimpleName() + " where email =:id");
            querry.setParameter("id", mail);
            t = (T) querry.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
        return t;
    }


}
