/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import model.pojo.SupplierInvoices;
import model.pojo.Suppliers;
import model.pojo.WorkerTypes;
import model.util.HibernateUtil;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author admin
 */
public class SupplierInvoicesDAO {

    @Autowired
    public final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public List<SupplierInvoices> findSupplierInvoiceByCaterer(int id) {

        try {
            if (!sessionFactory.getCurrentSession().getTransaction().isActive()) {
                sessionFactory.getCurrentSession().getTransaction().begin();
            }
            Query query = sessionFactory.getCurrentSession().createQuery("select invoice,caterer,supp from SupplierInvoices invoice join invoice.caterers caterer join invoice.suppliers supp where caterer.id =:id");
            query.setParameter("id", id);
            List list = query.list();

            Iterator ite = list.iterator();
            List<SupplierInvoices> listSupplierInvoiceses = new ArrayList<>();

            while (ite.hasNext()) {
                Object[] obj = (Object[]) ite.next();
                SupplierInvoices supInvoice = new SupplierInvoices();
                supInvoice = (SupplierInvoices) obj[0];
                listSupplierInvoiceses.add(supInvoice);
            }

            return listSupplierInvoiceses;
        } catch (Exception ex) {
            return null;
        } finally {
            sessionFactory.getCurrentSession().close();
        }

    }

    public SupplierInvoices create(SupplierInvoices supplierInvoices, int catererId) {
        try {
            sessionFactory.getCurrentSession().beginTransaction();
            Query query = sessionFactory.getCurrentSession().createSQLQuery("INSERT INTO SupplierInvoices (SupplierId, CatererId, InvoiceDate,DeliveryDate,Status) VALUES "
                    + " (:supplierId,:catererId,:invoiceDate,:deliveryDate,:status)");
            query.setParameter("supplierId", supplierInvoices.getSuppliers().getId());
            query.setParameter("catererId", catererId);
            query.setParameter("invoiceDate", supplierInvoices.getInvoiceDate());
            query.setParameter("deliveryDate", supplierInvoices.getDeliveryDate());
            query.setParameter("status", supplierInvoices.getStatus());
            query.executeUpdate();
            sessionFactory.getCurrentSession().getTransaction().commit();
            return supplierInvoices;
        } catch (Exception ex) {
            sessionFactory.getCurrentSession().getTransaction().rollback();
            return null;
        } finally {
            sessionFactory.getCurrentSession().close();
        }
    }

    public List<SupplierInvoices> getAllSupplierInvoiceBySupplierId(int supplierId, String status) {
        try {
            sessionFactory.getCurrentSession().beginTransaction();
            List<SupplierInvoices> list = sessionFactory.getCurrentSession().createQuery("from SupplierInvoices s where s.suppliers.id =:supplierId and s.status=:status order by s.id desc").setParameter("supplierId", supplierId).setParameter("status", status).list();
            for (SupplierInvoices item : list) {
                Hibernate.initialize(item.getCaterers());
            }
            return list;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } finally {
            sessionFactory.getCurrentSession().close();
        }

    }
    
    public boolean updateStatusForInvoice(int supplierInvoiceId, String status) {
        try {
            sessionFactory.getCurrentSession().beginTransaction();
            Query query = sessionFactory.getCurrentSession().createSQLQuery("UPDATE SupplierInvoices"
                    + "   SET Status = :status"
                    + " WHERE Id = :id");
            query.setParameter("id", supplierInvoiceId);
            query.setParameter("status", status);
            query.executeUpdate();
            sessionFactory.getCurrentSession().getTransaction().commit();
            return true;
        } catch (Exception ex) {
            sessionFactory.getCurrentSession().getTransaction().rollback();
            return false;
        } finally {
            sessionFactory.getCurrentSession().close();
        }
    }

    public SupplierInvoices updateDeliveryDateForInvoice(SupplierInvoices supplierInvoices, String deliveryDate) {
        try {
            sessionFactory.getCurrentSession().beginTransaction();
            Query query = sessionFactory.getCurrentSession().createSQLQuery("UPDATE SupplierInvoices"
                    + "   SET DeliveryDate = :date"
                    + " WHERE Id = :id");
            query.setParameter("id", supplierInvoices.getId());
            query.setParameter("date", deliveryDate);
            query.executeUpdate();
            sessionFactory.getCurrentSession().getTransaction().commit();
            return supplierInvoices;
        } catch (Exception ex) {
            sessionFactory.getCurrentSession().getTransaction().rollback();
            return null;
        } finally {
            sessionFactory.getCurrentSession().close();
        }
    }

    public SupplierInvoices getNewestInvoiceBySupplierIdAndCatererId(int supplierId, int catererId) {
        try {
            sessionFactory.getCurrentSession().beginTransaction();
            Query query = sessionFactory.getCurrentSession().createQuery("from SupplierInvoices s where s.caterer.id = :catererId AND s.supplier.id = :supplierId order by s.id desc LIMIT 0");
            query.setInteger("catererId", catererId);
            query.setInteger("supplierId", supplierId);

            return (SupplierInvoices) query.uniqueResult();

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } finally {
            sessionFactory.getCurrentSession().close();
        }

    }

    public SupplierInvoices checkInvoice(int supplierId, int catererId, String invoiceDate) {
        try {
            sessionFactory.getCurrentSession().beginTransaction();
            Query query = sessionFactory.getCurrentSession().createQuery("from SupplierInvoices s where s.caterers.id = :catererId AND s.suppliers.id = :supplierId AND s.invoiceDate = :invoiceDate");
            query.setInteger("catererId", catererId);
            query.setInteger("supplierId", supplierId);
            query.setString("invoiceDate", invoiceDate);
            return (SupplierInvoices) query.uniqueResult();

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } finally {
            sessionFactory.getCurrentSession().close();
        }

    }
}
