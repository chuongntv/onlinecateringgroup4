/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import model.pojo.Caterers;
import model.pojo.SubMenus;
import model.pojo.SupplierInvoices;
import model.util.HibernateUtil;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author admin
 */
public class CaterersDAO {

    @Autowired
    public final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public Caterers findCaterer(int id) {
        try {
            if (!sessionFactory.getCurrentSession().getTransaction().isActive()) {
                sessionFactory.getCurrentSession().getTransaction().begin();
            }
            Query query = sessionFactory.getCurrentSession().createQuery("from Caterers c where c.id =:id");
            query.setInteger("id", id);
            return (Caterers) query.uniqueResult();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } finally {
            sessionFactory.getCurrentSession().close();
        }
    }
    
    public List<Caterers> getAllCaterersWithStatus(int status) {
        try {
            if (!sessionFactory.getCurrentSession().getTransaction().isActive()) {
                sessionFactory.getCurrentSession().getTransaction().begin();
            }
            Query query = sessionFactory.getCurrentSession().createQuery("select caterer, account from Caterers caterer join caterer.accounts account where account.status=:status");
            query.setParameter("status", status);
            List<Caterers> list = query.list();
//            for (Caterers item : list) {
//                Hibernate.initialize(item.getAccounts());
//            }
            Iterator ite = list.iterator();
            List<Caterers> listCaterer = new ArrayList<>();

            while (ite.hasNext()) {
                Object[] obj = (Object[]) ite.next();
                Caterers temp = new Caterers();                
                temp = (Caterers) obj[0];
                Hibernate.initialize(temp.getAccounts());
                Hibernate.initialize(temp.getCities());
                listCaterer.add(temp);
            }
            return listCaterer;
            
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } finally {
            sessionFactory.getCurrentSession().close();
        }

    }

    public Caterers findCatererByAccount(int id) {
        try {
            if (!sessionFactory.getCurrentSession().getTransaction().isActive()) {
                sessionFactory.getCurrentSession().getTransaction().begin();
            }
            Query query = sessionFactory.getCurrentSession().createQuery("select caterer,account from Caterers caterer join caterer.accounts account where account.id =:id");
            query.setParameter("id", id);
            Object[] obj = (Object[]) query.uniqueResult();
            Caterers caterers = (Caterers) obj[0];
            return caterers;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } finally {
            sessionFactory.getCurrentSession().close();
        }

    }
}
