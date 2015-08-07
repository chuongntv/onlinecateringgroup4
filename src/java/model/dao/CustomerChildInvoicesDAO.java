/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import model.pojo.CustomerChildInvoices;
import model.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Nathan Tran
 */
public class CustomerChildInvoicesDAO {

    @Autowired
    public final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public List<CustomerChildInvoices> findCusChildInvoiceByCusInvoices(int id) {

        try {
            if (!sessionFactory.getCurrentSession().getTransaction().isActive()) {
                sessionFactory.getCurrentSession().getTransaction().begin();
            }
            Query query = sessionFactory.getCurrentSession().createQuery("select childinvoice,invoice,customer from CustomerChildInvoices childinvoice join childinvoice.customerInvoices invoice join invoice.accounts customer where invoice.id =:id");
            query.setParameter("id", id);
            List list = query.list();

            Iterator ite = list.iterator();

            List<CustomerChildInvoices> listInvoiceses = new ArrayList<>();

            while (ite.hasNext()) {
                Object[] obj = (Object[]) ite.next();
                CustomerChildInvoices supChildInvoice = new CustomerChildInvoices();
                supChildInvoice = (CustomerChildInvoices) obj[0];
                listInvoiceses.add(supChildInvoice);
            }

            return listInvoiceses;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } finally {
            sessionFactory.getCurrentSession().close();
        }

    }
    
}
