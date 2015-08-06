/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import model.pojo.Accounts;
import model.pojo.Suppliers;
import model.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author admin
 */
public class SuppliersDAO {

    @Autowired
    public final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public Suppliers findSupplierByAccountId(int accountId) {
        try {
            if (!sessionFactory.getCurrentSession().getTransaction().isActive()) {
                sessionFactory.getCurrentSession().getTransaction().begin();
            }
            //from Cat as cat where cat.mate.id
            Query query = sessionFactory.getCurrentSession().createQuery("from Suppliers as supp where supp.accounts.id = :accountId");
            query.setInteger("accountId", accountId);
            return (Suppliers) query.uniqueResult();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } finally {
            sessionFactory.getCurrentSession().close();
        }
    }
    public List<Suppliers> getListSupplierToOrder() {
        try {
            sessionFactory.getCurrentSession().beginTransaction();
            List<Suppliers> list = sessionFactory.getCurrentSession().createQuery("from Suppliers").list();
            return list;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } finally {
            sessionFactory.getCurrentSession().close();
        }

    }

    public List<Suppliers> getListSuppliers() {
        try {
            sessionFactory.getCurrentSession().beginTransaction();
            List list = sessionFactory.getCurrentSession().createQuery("select s,c,a  from Suppliers s  inner join   s.accounts a  inner join   s.countries c ").list();
            Iterator ite = list.iterator();
            List<Suppliers> listSuppliers = new ArrayList<>();

            while (ite.hasNext()) {
                Object[] obj = (Object[]) ite.next();
                Suppliers sup = new Suppliers();
                sup = (Suppliers) obj[0];
                listSuppliers.add(sup);
            }

            return listSuppliers;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } finally {
            sessionFactory.getCurrentSession().close();
        }

    }

    public Suppliers findSupplier(int id) {
        try {
            if (!sessionFactory.getCurrentSession().getTransaction().isActive()) {
                sessionFactory.getCurrentSession().getTransaction().begin();
            }
            Query query = sessionFactory.getCurrentSession().createQuery("select s,c,a from Suppliers s  join   s.accounts a  join   s.countries c where s.id =:id");

            query.setInteger("id", id);
            Object[] obj = (Object[]) query.uniqueResult();
            Suppliers supplier = (Suppliers) obj[0];
            return supplier;

        } catch (Exception ex) {
            return null;
        } finally {
            sessionFactory.getCurrentSession().close();
        }
    }

    public List<Suppliers> findByName(String name) {
        try {
            if (!sessionFactory.getCurrentSession().getTransaction().isActive()) {
                sessionFactory.getCurrentSession().getTransaction().begin();
            }
            String strQuery = "select s,c,a  from Suppliers s  inner join   s.accounts a  inner join   s.countries c where s.supplierName like '%" + name + "%' or s.supplierPhoneNumber like '%" + name + "%' or s.supplierEmail like '%" + name + "%' ";

            List list = sessionFactory.getCurrentSession().createQuery(strQuery).list();
            Iterator ite = list.iterator();
            List<Suppliers> listSuppliers = new ArrayList<>();

            while (ite.hasNext()) {
                Object[] obj = (Object[]) ite.next();
                Suppliers sup = new Suppliers();
                sup = (Suppliers) obj[0];
                listSuppliers.add(sup);
            }
            return listSuppliers;
        } catch (Exception ex) {
            return null;
        } finally {
            sessionFactory.getCurrentSession().close();
        }

    }

    public Suppliers edit(Suppliers supplier) {
        try {
            sessionFactory.getCurrentSession().beginTransaction();
            //   sessionFactory.getCurrentSession().merge(supplier);
            sessionFactory.getCurrentSession().update(supplier);
            sessionFactory.getCurrentSession().getTransaction().commit();
            return supplier;
        } catch (Exception ex) {
            sessionFactory.getCurrentSession().getTransaction().rollback();
            return null;
        } finally {
            sessionFactory.getCurrentSession().close();
        }

    }

    public Suppliers create(Suppliers supplier) {
        try {
            sessionFactory.getCurrentSession().beginTransaction();
            sessionFactory.getCurrentSession().save(supplier);
            sessionFactory.getCurrentSession().getTransaction().commit();
            return supplier;
        } catch (Exception ex) {
            sessionFactory.getCurrentSession().getTransaction().rollback();
            return null;
        } finally {
            sessionFactory.getCurrentSession().close();
        }
    }

    public Object[] findJoinSupplier(int id) {
        try {
            if (!sessionFactory.getCurrentSession().getTransaction().isActive()) {
                sessionFactory.getCurrentSession().getTransaction().begin();
            }
            Query query = sessionFactory.getCurrentSession().createQuery("select s,c,a from Suppliers s  join   s.accounts a  join   s.countries c where s.id =:id");

            query.setInteger("id", id);
            Object[] obj = (Object[]) query.uniqueResult();

            return obj;

        } catch (Exception ex) {
            return null;
        } finally {
            sessionFactory.getCurrentSession().close();
        }
    }
}
