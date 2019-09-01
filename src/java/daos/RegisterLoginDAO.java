/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import models.Account;
import models.Employee;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author Lenovo
 */
public class RegisterLoginDAO implements IRegisterLoginDAO{

    private SessionFactory factory;
    private Session session;
    private Transaction transaction;

    public RegisterLoginDAO(SessionFactory factory) {
        this.factory = factory;
    }
    
    @Override
    public boolean updateAccountStatus(Account account) {
        boolean result = false;
        try {
            session = factory.openSession();
            transaction = session.beginTransaction();
            session.saveOrUpdate(account);
            transaction.commit();
            result = true;
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }finally{
            session.close();
        }
        return result;
    }

    @Override
    public Account getByToken(String token) {
        Account account = new Account();
        try {
            session = factory.openSession();
            transaction = session.beginTransaction();
            Query query = session.createQuery("FROM Account WHERE token = :token");
            query.setParameter("token", token);
            account = (Account) query.uniqueResult();
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }finally{
            session.close();
        }
        return account;
    }

    @Override
    public Account getByEmail(String a) {
         Account account = new Account();
        try {
            session = factory.openSession();
            transaction = session.beginTransaction();
            Query query = session.createQuery("FROM Account WHERE employee.email = :email");
            query.setParameter("email", a);
            account = (Account) query.uniqueResult();
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }finally{
            session.close();
        }
        return account;
    }
    
    
    @Override
     public Account getByUsername(String username) {
         Account t = new Account();
        try {
            session = factory.openSession();
            transaction = session.beginTransaction();
            Query querry = session.createQuery("FROM Account where username =:id");
            querry.setParameter("id", username);
            t = (Account) querry.uniqueResult();
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
