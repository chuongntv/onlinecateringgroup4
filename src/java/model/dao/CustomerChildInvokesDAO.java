/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import model.pojo.CustomerChildInvoices;
import model.pojo.SupplierChildInvoices;
import model.pojo.SupplierInvoices;
import model.util.HibernateUtil;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author MSI
 */
public class CustomerChildInvokesDAO {
       @Autowired
    public final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public CustomerChildInvoices create(CustomerChildInvoices customerChildInvoke) {
        try {
            sessionFactory.getCurrentSession().beginTransaction();
            sessionFactory.getCurrentSession().save(customerChildInvoke);
            sessionFactory.getCurrentSession().getTransaction().commit();
            return customerChildInvoke;
        } catch (Exception ex) {
            sessionFactory.getCurrentSession().getTransaction().rollback();
            return null;
        } finally {
            sessionFactory.getCurrentSession().close();
        }
    }
}
