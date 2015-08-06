/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.util.List;
import model.pojo.Materials;
import model.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author admin
 */
public class MaterialsDAO {
    @Autowired
    public final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public List<Materials> getAllMaterials() {
        try {
            sessionFactory.getCurrentSession().beginTransaction();
            List<Materials> list = sessionFactory.getCurrentSession().createQuery("from Materials").list();
            return list;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } finally {
            sessionFactory.getCurrentSession().close();
        }

    }
    
    public List<Materials> getAllMaterialsByCategoryId(int categoryId) {
        try {
            sessionFactory.getCurrentSession().beginTransaction();
            List<Materials> list = sessionFactory.getCurrentSession().createQuery("from Materials m where m.materialCategories.id = "+categoryId).list();
            return list;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } finally {
            sessionFactory.getCurrentSession().close();
        }

    }

    public Materials create(Materials material, int categoryId) {
        try {
            sessionFactory.getCurrentSession().beginTransaction();
            //insert into EMPLOYEE (EMAIL, FIRST_NAME, LAST_NAME) values (?, ?, ?)
            Query query = sessionFactory.getCurrentSession().createSQLQuery("INSERT INTO Materials (CategoryId,MaterialName,MaterialPricePerUnit,MaterialUnit) VALUES (:value1,:value2,:value3,:value4)");
            query.setParameter("value1", categoryId);
            query.setParameter("value2", material.getMaterialName());
            query.setParameter("value3", material.getMaterialPricePerUnit());
            query.setParameter("value4", material.getMaterialUnit());
            query.executeUpdate();
            sessionFactory.getCurrentSession().getTransaction().commit();
            return material;
        } catch (Exception ex) {
            sessionFactory.getCurrentSession().getTransaction().rollback();
            return null;
        } finally {
            sessionFactory.getCurrentSession().close();
        }
    }

    public Materials findMaterial(int id) {
        try {
            if (!sessionFactory.getCurrentSession().getTransaction().isActive()) {
                sessionFactory.getCurrentSession().getTransaction().begin();
            }
            Query query = sessionFactory.getCurrentSession().createQuery("from Materials c where c.id =:id");
            query.setInteger("id", id);
            return (Materials) query.uniqueResult();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } finally {
            sessionFactory.getCurrentSession().close();
        }
    }

    public Materials edit(Materials material, int categoryId) {
        try {
            sessionFactory.getCurrentSession().beginTransaction();
            Query query = sessionFactory.getCurrentSession().createSQLQuery("UPDATE Materials SET CategoryId = :value1, MaterialName =:value2,MaterialPricePerUnit = :value3, MaterialUnit =:value4 WHERE Id = :value5");
            query.setParameter("value1", categoryId);
            query.setParameter("value2", material.getMaterialName());
            query.setParameter("value3", material.getMaterialPricePerUnit());
            query.setParameter("value4", material.getMaterialUnit());
            query.setParameter("value5", material.getId());
            query.executeUpdate();
            sessionFactory.getCurrentSession().getTransaction().commit();
            return material;
        } catch (Exception ex) {
            sessionFactory.getCurrentSession().getTransaction().rollback();
            return null;
        } finally {
            sessionFactory.getCurrentSession().close();
        }

    }

    public void delete(Materials material) {
        try {
            sessionFactory.getCurrentSession().beginTransaction();
            sessionFactory.getCurrentSession().delete(material);
            sessionFactory.getCurrentSession().getTransaction().commit();
        } catch (Exception ex) {
            sessionFactory.getCurrentSession().getTransaction().rollback();
        } finally {
            sessionFactory.getCurrentSession().close();
        }
    }
}
