/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.util.List;
import model.pojo.WorkerTypes;
import model.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author admin
 */
public class WorkerTypesDAO {
    @Autowired
    public final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    
    public List<WorkerTypes> getTypes(int catererId) {
        try {
            sessionFactory.getCurrentSession().beginTransaction();
            //List<MaterialCategories> list = sessionFactory.getCurrentSession().createQuery("from MaterialCategories m where m.suppliers.id ="+(supplierId)).list();
            List<WorkerTypes> list = sessionFactory.getCurrentSession().createQuery("from WorkerTypes m where m.caterers.id =:catererId").setParameter("catererId", catererId).list();
            return list;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } finally {
            sessionFactory.getCurrentSession().close();
        }

    }
    
    public WorkerTypes create(WorkerTypes type, int catererId) {
        try {
            sessionFactory.getCurrentSession().beginTransaction();
            //insert into EMPLOYEE (EMAIL, FIRST_NAME, LAST_NAME) values (?, ?, ?)
            Query query = sessionFactory.getCurrentSession().createSQLQuery("INSERT INTO [OnlineCateringDB].[dbo].[WorkerTypes] (CatererId, WorkerTypeName, PayPerDay) VALUES (:value1,:value2,:value3)");
            query.setParameter("value1", catererId);
            query.setParameter("value2", type.getWorkerTypeName());
            //Double payPerDay = new Double(type.getPayPerDay());
            query.setParameter("value3", type.getPayPerDay());
            query.executeUpdate();
            sessionFactory.getCurrentSession().getTransaction().commit();
            return type;
        } catch (Exception ex) {
            sessionFactory.getCurrentSession().getTransaction().rollback();
            return null;
        } finally {
            sessionFactory.getCurrentSession().close();
        }
    }
    
    public WorkerTypes findType(int id) {
        WorkerTypes test =  null;
        try {
            if (!sessionFactory.getCurrentSession().getTransaction().isActive()) {
                sessionFactory.getCurrentSession().getTransaction().begin();
            }
            Query query = sessionFactory.getCurrentSession().createQuery("from WorkerTypes c where c.id =:id");
            query.setInteger("id", id);
            
            test = (WorkerTypes)query.uniqueResult();             
            
        } catch (Exception ex) {
            ex.printStackTrace();             
            
        } finally {
           if (sessionFactory.getCurrentSession().getTransaction().isActive()) 
                sessionFactory.getCurrentSession().close();
        }
        return test;
    }

    public WorkerTypes edit(WorkerTypes type, int catererId) {
        try {
            sessionFactory.getCurrentSession().beginTransaction();
            Query query = sessionFactory.getCurrentSession().createSQLQuery("UPDATE WorkerTypes SET WorkerTypeName = :value2, PayPerDay = :value3 WHERE Id = :value4");
            //query.setParameter("value1", catererId);
            query.setParameter("value2", type.getWorkerTypeName());
            query.setParameter("value3", type.getPayPerDay());
            query.setParameter("value4", type.getId());
            query.executeUpdate();
            sessionFactory.getCurrentSession().getTransaction().commit();
            return type;
        } catch (Exception ex) {
            sessionFactory.getCurrentSession().getTransaction().rollback();
            return null;
        } finally {
            sessionFactory.getCurrentSession().close();
        }

    }

    public void delete(WorkerTypes type) {
        try {
            sessionFactory.getCurrentSession().beginTransaction();
            sessionFactory.getCurrentSession().delete(type);
            sessionFactory.getCurrentSession().getTransaction().commit();
        } catch (Exception ex) {
            sessionFactory.getCurrentSession().getTransaction().rollback();
        } finally {
            sessionFactory.getCurrentSession().close();
        }
    }
}
