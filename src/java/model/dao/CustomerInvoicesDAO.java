/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import model.pojo.CustomerInvoices;
import model.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Nathan Tran
 */
public class CustomerInvoicesDAO {

    @Autowired
    public final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public List<CustomerInvoices> getList(int id) {
        try {
            if (!sessionFactory.getCurrentSession().getTransaction().isActive()) {
                sessionFactory.getCurrentSession().getTransaction().begin();
            }
            Query query = sessionFactory.getCurrentSession().createQuery("select invoice,account,caterer,worker from CustomerInvoices invoice join invoice.accounts account join invoice.caterers caterer left join invoice.workers worker where account.id=:id ORDER BY invoice.id DESC ");
            query.setParameter("id", id);
            List list = query.list();

            Iterator ite = list.iterator();
            List<CustomerInvoices> listSupplierInvoiceses = new ArrayList<>();

            while (ite.hasNext()) {
                Object[] obj = (Object[]) ite.next();
                CustomerInvoices supInvoice = new CustomerInvoices();
                supInvoice = (CustomerInvoices) obj[0];
                listSupplierInvoiceses.add(supInvoice);
            }

            return listSupplierInvoiceses;
        } catch (Exception ex) {
            return null;
        } finally {
            sessionFactory.getCurrentSession().close();
        }
    }

    public CustomerInvoices findById(int id) {
        try {
            if (!sessionFactory.getCurrentSession().getTransaction().isActive()) {
                sessionFactory.getCurrentSession().getTransaction().begin();
            }
            Query query = sessionFactory.getCurrentSession().createQuery("select invoice,account,caterer,worker from CustomerInvoices invoice join invoice.accounts account join invoice.caterers caterer left join invoice.workers worker where invoice.id = :id ORDER BY invoice.id DESC");
            query.setParameter("id", id);
            List list = query.list();
            Iterator ite = list.iterator();
            List<CustomerInvoices> listSupplierInvoiceses = new ArrayList<>();

            while (ite.hasNext()) {
                Object[] obj = (Object[]) ite.next();
                CustomerInvoices supInvoice = new CustomerInvoices();
                supInvoice = (CustomerInvoices) obj[0];
                listSupplierInvoiceses.add(supInvoice);
            }
            return listSupplierInvoiceses.get(0);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } finally {
            sessionFactory.getCurrentSession().close();
        }
    }

    public CustomerInvoices edit(CustomerInvoices acc) {
        try {
            sessionFactory.getCurrentSession().beginTransaction();
            Query query = sessionFactory.getCurrentSession().createSQLQuery("UPDATE CustomerInvoices SET workerid=:workerid WHERE Id=:id");
            query.setParameter("id", acc.getId());                        
            query.setParameter("workerid", acc.getWorkers());
            query.executeUpdate();
            sessionFactory.getCurrentSession().getTransaction().commit();
            return acc;
        } catch (Exception ex) {
            sessionFactory.getCurrentSession().getTransaction().rollback();
            ex.printStackTrace();
            return null;
        } finally {
            sessionFactory.getCurrentSession().close();
        }
    }
    
    public CustomerInvoices editStatus(CustomerInvoices acc) {
        try {
            sessionFactory.getCurrentSession().beginTransaction();
            Query query = sessionFactory.getCurrentSession().createSQLQuery("UPDATE CustomerInvoices SET status=:status WHERE Id=:id");
            query.setParameter("id", acc.getId());            
            query.setParameter("status", acc.getStatus());            
            query.executeUpdate();
            sessionFactory.getCurrentSession().getTransaction().commit();
            return acc;
        } catch (Exception ex) {
            sessionFactory.getCurrentSession().getTransaction().rollback();
            ex.printStackTrace();
            return null;
        } finally {
            sessionFactory.getCurrentSession().close();
        }
    }

    public List<CustomerInvoices> getListByCaterer(int id) {

        try {
            if (!sessionFactory.getCurrentSession().getTransaction().isActive()) {
                sessionFactory.getCurrentSession().getTransaction().begin();
            }
            Query query = sessionFactory.getCurrentSession().createQuery("select invoice,account,caterer,worker from CustomerInvoices invoice join invoice.accounts account join invoice.caterers caterer left join invoice.workers worker where caterer.id = :id ORDER BY invoice.id DESC ");
            query.setParameter("id", id);
            List list = query.list();

            Iterator ite = list.iterator();
            List<CustomerInvoices> listSupplierInvoiceses = new ArrayList<>();

            while (ite.hasNext()) {
                Object[] obj = (Object[]) ite.next();
                CustomerInvoices supInvoice = new CustomerInvoices();
                supInvoice = (CustomerInvoices) obj[0];
                listSupplierInvoiceses.add(supInvoice);
            }

            return listSupplierInvoiceses;
        } catch (Exception ex) {
            return null;
        } finally {
            sessionFactory.getCurrentSession().close();
        }
    }
    
    
}
