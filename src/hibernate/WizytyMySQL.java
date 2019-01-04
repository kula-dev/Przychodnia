/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernate;

import org.hibernate.Session;
import org.hibernate.Transaction;
import przychodnia.Wizyty;

/**
 *
 * @author Soprano
 */
public class WizytyMySQL {
    static Session session = null; 
    
    public static void insert(Wizyty e){
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.save(e);
        tx.commit();
        session.close();
    }
    
    public static void update(Wizyty e){
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.update(e);
        tx.commit();
        session.close();
    }
    
    public static void delete(Wizyty e){
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.delete(e);
        tx.commit();
        session.close();
    }
}
