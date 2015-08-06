/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import model.pojo.SupplierChildInvoices;
import model.pojo.SupplierInvoices;
import model.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author admin
 */
public class SupplierChildInvoicesDAO {
    
    @Autowired
    public final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public List<SupplierChildInvoices> findSupplierChildInvoiceBySupplierInvoices(int id) {

        try {
            if (!sessionFactory.getCurrentSession().getTransaction().isActive()) {
                sessionFactory.getCurrentSession().getTransaction().begin();
            }
             Query query = sessionFactory.getCurrentSession().createQuery("select childinvoice,invoice,supplier from SupplierChildInvoices childinvoice join childinvoice.supplierInvoices invoice join invoice.suppliers supplier where invoice.id =:id");
             query.setParameter("id", id);
             List list = query.list();
             
             Iterator ite = list.iterator();
             
            List<SupplierChildInvoices> listSupplierChildInvoiceses = new ArrayList<>();
            
            while(ite.hasNext()){
                Object[] obj = (Object[]) ite.next();
                SupplierChildInvoices supChildInvoice = new SupplierChildInvoices();
                supChildInvoice = (SupplierChildInvoices)obj[0];
                listSupplierChildInvoiceses.add(supChildInvoice);
            }
            
            return listSupplierChildInvoiceses;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } finally {
            sessionFactory.getCurrentSession().close();
        }
        
    }
    public SupplierChildInvoices findSupplierChildInvoiceBySupplierInvoiceIdAndMaterialName(String supplierInvoiceId,String materialName) {

        try {
            if (!sessionFactory.getCurrentSession().getTransaction().isActive()) {
                sessionFactory.getCurrentSession().getTransaction().begin();
            }
             Query query = sessionFactory.getCurrentSession().createQuery("from  SupplierChildInvoices childinvoice where childinvoice.supplierInvoices.id =:supplierId and childinvoice.materialName = :materialName");
             //query.setParameter("supplierId", supplierInvoiceId);
             query.setParameter("supplierId", Integer.parseInt(supplierInvoiceId));
             query.setParameter("materialName", materialName);
            
            return (SupplierChildInvoices) query.uniqueResult();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } finally {
            sessionFactory.getCurrentSession().close();
        }
        
    }
    
    public List<SupplierChildInvoices> getAllInvoiceChildBySupplierInvoiceId(int supplierInvoiceId) {
        try {
            sessionFactory.getCurrentSession().beginTransaction();
            // and m.status=0
            List<SupplierChildInvoices> list = sessionFactory.getCurrentSession().createQuery("from SupplierChildInvoices m where m.supplierInvoices.id =:supplierInvoiceId ").setParameter("supplierInvoiceId", supplierInvoiceId).list();            
            return list;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } finally {
            sessionFactory.getCurrentSession().close();
        }

    }
    
    public SupplierChildInvoices update(SupplierChildInvoices supplierChildInvoices) {
        try {
            sessionFactory.getCurrentSession().beginTransaction();
            Query query = sessionFactory.getCurrentSession().createSQLQuery("UPDATE SupplierChildInvoices"
                    + "   SET Quantity = :quantity"
                    + " WHERE Id = :id");
            query.setParameter("id", supplierChildInvoices.getId());
            query.setParameter("quantity", supplierChildInvoices.getQuantity());
            query.executeUpdate();
            sessionFactory.getCurrentSession().getTransaction().commit();
            return supplierChildInvoices;
        } catch (Exception ex) {
            sessionFactory.getCurrentSession().getTransaction().rollback();
            return null;
        } finally {
            sessionFactory.getCurrentSession().close();
        }

    }
    
    public SupplierChildInvoices create(SupplierChildInvoices supplierChildInvoices,int supplierInvoiceId) {
        try {
            sessionFactory.getCurrentSession().beginTransaction();
            Query query = sessionFactory.getCurrentSession().createSQLQuery("INSERT INTO SupplierChildInvoices (SupplierInvoiceId,MaterialName,Quantity,MaterialPricePerUnit) VALUES "
                    + " (:supplierInvoiceId,:materialName,:quantity,:materialPricePerUnit)");
            query.setParameter("supplierInvoiceId", supplierInvoiceId);
            query.setParameter("materialName", supplierChildInvoices.getMaterialName());
            query.setParameter("quantity", supplierChildInvoices.getQuantity());
            query.setParameter("materialPricePerUnit", supplierChildInvoices.getMaterialPricePerUnit());
            query.executeUpdate();
            sessionFactory.getCurrentSession().getTransaction().commit();
            return supplierChildInvoices;
        } catch (Exception ex) {
            sessionFactory.getCurrentSession().getTransaction().rollback();
            return null;
        } finally {
            sessionFactory.getCurrentSession().close();
        }
    }
    public SupplierChildInvoices createHaveMaterialName(SupplierChildInvoices supplierChildInvoices,int supplierInvoiceId, String materialName) {
        try {
            sessionFactory.getCurrentSession().beginTransaction();
            Query query = sessionFactory.getCurrentSession().createSQLQuery("INSERT INTO SupplierChildInvoices (SupplierInvoiceId,MaterialName,Quantity,MaterialPricePerUnit) VALUES "
                    + " (:supplierInvoiceId,:materialName,:quantity,:materialPricePerUnit)");
            query.setParameter("supplierInvoiceId", supplierInvoiceId);
            query.setParameter("materialName", materialName);
            query.setParameter("quantity", supplierChildInvoices.getQuantity());
            query.setParameter("materialPricePerUnit", supplierChildInvoices.getMaterialPricePerUnit());
            query.executeUpdate();
            sessionFactory.getCurrentSession().getTransaction().commit();
            return supplierChildInvoices;
        } catch (Exception ex) {
            sessionFactory.getCurrentSession().getTransaction().rollback();
            return null;
        } finally {
            sessionFactory.getCurrentSession().close();
        }
    }
}
