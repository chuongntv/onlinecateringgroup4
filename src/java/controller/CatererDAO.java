/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.pojo.Accounts;
import model.pojo.Caterers;
import model.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author MSI
 */
public class CatererDAO {

    @Autowired
    public final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

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
