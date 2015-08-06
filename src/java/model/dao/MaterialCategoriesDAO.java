/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.util.List;
import model.pojo.MaterialCategories;
import model.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author TriThoai
 */
public class MaterialCategoriesDAO {

    @Autowired
    public final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public List<MaterialCategories> getCatergories(int supplierId) {
        try {
            sessionFactory.getCurrentSession().beginTransaction();
            //List<MaterialCategories> list = sessionFactory.getCurrentSession().createQuery("from MaterialCategories m where m.suppliers.id ="+(supplierId)).list();
            List<MaterialCategories> list = sessionFactory.getCurrentSession().createQuery("from MaterialCategories m where m.suppliers.id =:supplierId").setParameter("supplierId", supplierId).list();
            
            return list;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } finally {
            sessionFactory.getCurrentSession().close();
        }

    }

    public MaterialCategories create(MaterialCategories category, int accountId) {
        try {
            sessionFactory.getCurrentSession().beginTransaction();
            //insert into EMPLOYEE (EMAIL, FIRST_NAME, LAST_NAME) values (?, ?, ?)
            Query query = sessionFactory.getCurrentSession().createSQLQuery("INSERT INTO MaterialCategories (SupplierId, CategoryName) VALUES (:value1,:value2)");
            query.setParameter("value1", accountId);
            query.setParameter("value2", category.getCategoryName());
            query.executeUpdate();
            sessionFactory.getCurrentSession().getTransaction().commit();
            return category;
        } catch (Exception ex) {
            sessionFactory.getCurrentSession().getTransaction().rollback();
            return null;
        } finally {
            sessionFactory.getCurrentSession().close();
        }
    }

    public MaterialCategories findCategory(int id) {
        try {
            if (!sessionFactory.getCurrentSession().getTransaction().isActive()) {
                sessionFactory.getCurrentSession().getTransaction().begin();
            }
            Query query = sessionFactory.getCurrentSession().createQuery("from MaterialCategories c where c.id =:id");
            query.setInteger("id", id);
            MaterialCategories test = new MaterialCategories();
            test =  (MaterialCategories)query.uniqueResult();
            return test;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } finally {
            sessionFactory.getCurrentSession().close();
        }
    }

    public MaterialCategories edit(MaterialCategories category, int accountId) {
        try {
            sessionFactory.getCurrentSession().beginTransaction();
            Query query = sessionFactory.getCurrentSession().createSQLQuery("UPDATE MaterialCategories SET SupplierId = :value1, CategoryName =:value2 WHERE Id = :value3");
            query.setParameter("value1", accountId);
            query.setParameter("value2", category.getCategoryName());
            query.setParameter("value3", category.getId());
            query.executeUpdate();
            sessionFactory.getCurrentSession().getTransaction().commit();
            return category;
        } catch (Exception ex) {
            sessionFactory.getCurrentSession().getTransaction().rollback();
            return null;
        } finally {
            sessionFactory.getCurrentSession().close();
        }

    }

    public void delete(MaterialCategories category) {
        try {
            sessionFactory.getCurrentSession().beginTransaction();
            sessionFactory.getCurrentSession().delete(category);
            sessionFactory.getCurrentSession().getTransaction().commit();
        } catch (Exception ex) {
            sessionFactory.getCurrentSession().getTransaction().rollback();
        } finally {
            sessionFactory.getCurrentSession().close();
        }
    }
}
