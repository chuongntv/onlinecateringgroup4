/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.util.List;
import model.pojo.WorkerSalaries;
import model.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author admin
 */
public class WorkerSalariesDAO {
    @Autowired
    public final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    
    public List<WorkerSalaries> getAllSalaries() {
        try {
            sessionFactory.getCurrentSession().beginTransaction();
            List<WorkerSalaries> list = sessionFactory.getCurrentSession().createQuery("from WorkerSalaries").list();
            return list;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } finally {
            sessionFactory.getCurrentSession().close();
        }

    }

    public WorkerSalaries create(WorkerSalaries workerSalaries, int workerId) {
        try {
            sessionFactory.getCurrentSession().beginTransaction();
            Query query = sessionFactory.getCurrentSession().createSQLQuery("INSERT INTO WorkerSalaries (WorkerId, WorkingDay) VALUES "
                    + " (:workerId,1)");
            query.setParameter("workerId", workerId);
            query.executeUpdate();
            sessionFactory.getCurrentSession().getTransaction().commit();
            return workerSalaries;
        } catch (Exception ex) {
            sessionFactory.getCurrentSession().getTransaction().rollback();
            return null;
        } finally {
            sessionFactory.getCurrentSession().close();
        }
    }
    
    public WorkerSalaries findWorkerToPayment(int idWorker){
        try {
            if (!sessionFactory.getCurrentSession().getTransaction().isActive()) {
                sessionFactory.getCurrentSession().getTransaction().begin();
            }
            Query query = sessionFactory.getCurrentSession().createQuery("from WorkerSalaries w where w.workers.id =:idWorker");
            query.setInteger("idWorker", idWorker);
            WorkerSalaries test = (WorkerSalaries) query.uniqueResult();
            return test;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } finally {
            sessionFactory.getCurrentSession().close();
        }
    }

    public WorkerSalaries findSalary(int idWorker) {
        try {
            if (!sessionFactory.getCurrentSession().getTransaction().isActive()) {
                sessionFactory.getCurrentSession().getTransaction().begin();
            }
            Query query = sessionFactory.getCurrentSession().createQuery("from WorkerSalaries w where w.id =:id");
            query.setInteger("id", idWorker);
            WorkerSalaries test = null;
            test = (WorkerSalaries) query.uniqueResult();
            return test;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } finally {
            sessionFactory.getCurrentSession().close();
        }
    }

    public WorkerSalaries edit(WorkerSalaries workerSalaries) {
        try {
            sessionFactory.getCurrentSession().beginTransaction();
            Query query = sessionFactory.getCurrentSession().createSQLQuery("UPDATE WorkerSalaries SET WorkingDay = :value1, PayType = :value2 WHERE Id = :value3");
            query.setParameter("value1", workerSalaries.getWorkingDay());
            query.setParameter("value2", workerSalaries.getPayType());
            query.setParameter("value3", workerSalaries.getId());
            query.executeUpdate();
            sessionFactory.getCurrentSession().getTransaction().commit();
            return workerSalaries;
        } catch (Exception ex) {
            sessionFactory.getCurrentSession().getTransaction().rollback();
            return null;
        } finally {
            sessionFactory.getCurrentSession().close();
        }

    }
    public void delete(WorkerSalaries workerSalaries) {
        try {
            sessionFactory.getCurrentSession().beginTransaction();
            sessionFactory.getCurrentSession().delete(workerSalaries);
            sessionFactory.getCurrentSession().getTransaction().commit();
        } catch (Exception ex) {
            sessionFactory.getCurrentSession().getTransaction().rollback();
        } finally {
            sessionFactory.getCurrentSession().close();
        }
    }
}
