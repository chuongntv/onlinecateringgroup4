/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import model.pojo.WorkerTypes;
import model.pojo.Workers;
import model.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author admin
 */
public class WorkersDAO {

    @Autowired
    public final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public List<Workers> getWorkers(int typeId) {
        try {
            sessionFactory.getCurrentSession().beginTransaction();
            // and m.status=0
            List<Workers> list = sessionFactory.getCurrentSession().createQuery("from Workers m where m.workerTypes.id =:typeId and m.status=0").setParameter("typeId", typeId).list();
            return list;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } finally {
            sessionFactory.getCurrentSession().close();
        }

    }
    

    public Workers create(Workers worker, int typeId) {
        try {
            sessionFactory.getCurrentSession().beginTransaction();
            //insert into EMPLOYEE (EMAIL, FIRST_NAME, LAST_NAME) values (?, ?, ?)
            Query query = sessionFactory.getCurrentSession().createSQLQuery("INSERT INTO Workers (WorkerTypeId, WorkerName, WorkerPhoneNumber,WorkerEmail,WorkerAddress,WorkerDateOfJoin) VALUES "
                    + " (:typeId,:workerName,:phone,:email,:address,:datejoin)");
            query.setParameter("typeId", typeId);
            query.setParameter("workerName", worker.getWorkerName());
            query.setParameter("phone", worker.getWorkerPhoneNumber());
            query.setParameter("email", worker.getWorkerEmail());
            query.setParameter("address", worker.getWorkerAddress());
            DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
            Date today = Calendar.getInstance().getTime();
            String reportDate = df.format(today);
            query.setParameter("datejoin", reportDate);
            query.executeUpdate();
            sessionFactory.getCurrentSession().getTransaction().commit();
            return worker;
        } catch (Exception ex) {
            sessionFactory.getCurrentSession().getTransaction().rollback();
            return null;
        } finally {
            sessionFactory.getCurrentSession().close();
        }
    }

    public Workers findWorker(int id) {
        Workers test = null;
        try {
            if (!sessionFactory.getCurrentSession().getTransaction().isActive()) {
                sessionFactory.getCurrentSession().getTransaction().begin();
            }
            Query query = sessionFactory.getCurrentSession().createQuery("from Workers c where c.id =:id");
            query.setInteger("id", id);
            test = (Workers) query.uniqueResult();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (sessionFactory.getCurrentSession().getTransaction().isActive()) {
                sessionFactory.getCurrentSession().close();
            }
        }
        return test;
    }

    public Workers edit(Workers worker) {
        try {
            sessionFactory.getCurrentSession().beginTransaction();
            Query query = sessionFactory.getCurrentSession().createSQLQuery("UPDATE Workers"
                    + "   SET WorkerName = :name"
                    + "      ,WorkerPhoneNumber = :phone"
                    + "      ,WorkerEmail = :email"
                    + "      ,WorkerAddress = :address"
                    + "      ,Status = :status"
                    + " WHERE Id = :id");
            query.setParameter("id", worker.getId());
            query.setParameter("name", worker.getWorkerName());
            query.setParameter("phone", worker.getWorkerPhoneNumber());
            query.setParameter("email", worker.getWorkerEmail());
            query.setParameter("address", worker.getWorkerAddress());
            query.setParameter("status", worker.getStatus());
            query.executeUpdate();
            sessionFactory.getCurrentSession().getTransaction().commit();
            return worker;
        } catch (Exception ex) {
            sessionFactory.getCurrentSession().getTransaction().rollback();
            return null;
        } finally {
            sessionFactory.getCurrentSession().close();
        }

    }

    public void delete(Workers worker) {
        try {
            sessionFactory.getCurrentSession().beginTransaction();
            sessionFactory.getCurrentSession().delete(worker);
            sessionFactory.getCurrentSession().getTransaction().commit();
        } catch (Exception ex) {
            sessionFactory.getCurrentSession().getTransaction().rollback();
        } finally {
            sessionFactory.getCurrentSession().close();
        }
    }
}
