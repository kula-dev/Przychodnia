/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernate;

import org.hibernate.Session;
import org.hibernate.Transaction;
import przychodnia.Pacjent;

/**
 *
 * @author Soprano
 */
public class PacjentMySQL {
    static Session session = null; 
    
    public static void insert(Pacjent e){
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.save(e);
        tx.commit();
        session.close();
    }
    
    public static void delete(Pacjent e){
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.delete(e);
        tx.commit();
        session.close();
    }
    
}
