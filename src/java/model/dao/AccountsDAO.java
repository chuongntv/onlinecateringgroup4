/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.util.List;
import model.pojo.Accounts;
import model.pojo.Caterers;
import model.pojo.Countries;
import model.pojo.Suppliers;
import model.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author admin
 */
public class AccountsDAO {
    @Autowired
    public final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    //admin
    public Accounts findAccount(int id) {
        try {
            if (!sessionFactory.getCurrentSession().getTransaction().isActive()) {
                sessionFactory.getCurrentSession().getTransaction().begin();
            }
            Query query = sessionFactory.getCurrentSession().createQuery("from Accounts c where c.id =:id");
            query.setParameter("id", id);
            return (Accounts) query.uniqueResult();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } finally {
            sessionFactory.getCurrentSession().close();
		}
	}
	public List<Accounts> findAllCustomers() {
        try {
            sessionFactory.getCurrentSession().beginTransaction();
            // and m.status=0
            List<Accounts> list = sessionFactory.getCurrentSession().createQuery("from Accounts m where m.userGroup =:userGroup and m.status=0").setParameter("userGroup", "customer").list();
            return list;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } finally {
            sessionFactory.getCurrentSession().close();
        }

    }
    
    public List<Accounts> findAllCustomersBlocked() {
        try {
            sessionFactory.getCurrentSession().beginTransaction();
            // and m.status=0
            List<Accounts> list = sessionFactory.getCurrentSession().createQuery("from Accounts m where m.userGroup =:userGroup and m.status=1").setParameter("userGroup", "customer").list();
            return list;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } finally {
            sessionFactory.getCurrentSession().close();
        }

    }
    
    public Accounts block(int accountId) {
        try {
            sessionFactory.getCurrentSession().beginTransaction();
            Query query = sessionFactory.getCurrentSession().createSQLQuery("UPDATE Accounts SET Status = 1 Where Id = :id");
            query.setParameter("id", accountId);
            query.executeUpdate();
            sessionFactory.getCurrentSession().getTransaction().commit();
            return findAccount(accountId);
        } catch (Exception ex) {
            sessionFactory.getCurrentSession().getTransaction().rollback();
            return null;
        } finally {
            sessionFactory.getCurrentSession().close();
        }

    }
    public Accounts active(int accountId) {
        try {
            sessionFactory.getCurrentSession().beginTransaction();
            Query query = sessionFactory.getCurrentSession().createSQLQuery("UPDATE Accounts SET Status = 0 Where Id = :id");
            query.setParameter("id", accountId);
            query.executeUpdate();
            sessionFactory.getCurrentSession().getTransaction().commit();
            return findAccount(accountId);
        } catch (Exception ex) {
            sessionFactory.getCurrentSession().getTransaction().rollback();
            return null;
        } finally {
            sessionFactory.getCurrentSession().close();
        }

    }
    //admin end
    //Phuc
    public List<Accounts> getAccounts() {
        try {
            sessionFactory.getCurrentSession().beginTransaction();
            List<Accounts> list = sessionFactory.getCurrentSession().createQuery("from Accounts").list();
            return list;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } finally {
            sessionFactory.getCurrentSession().close();
        }
    }

    public Accounts register(Accounts acc) {
        try {
            sessionFactory.getCurrentSession().beginTransaction();
            sessionFactory.getCurrentSession().save(acc);
            sessionFactory.getCurrentSession().getTransaction().commit();
            return acc;
        } catch (Exception ex) {
            sessionFactory.getCurrentSession().getTransaction().rollback();
            return null;
        } finally {
            sessionFactory.getCurrentSession().close();
        }
    }

    public Accounts findAccounts(String user, String pass) {
        try {
            sessionFactory.getCurrentSession().beginTransaction();
            Query query = sessionFactory.getCurrentSession().createQuery("from Accounts a where a.username = :user AND a.password = :pass ");
            query.setParameter("user", user);
            query.setParameter("pass", pass);
            return (Accounts) query.uniqueResult();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } finally {
            sessionFactory.getCurrentSession().close();
        }
    }

    public Accounts findByUsername(String username) {
        try {
            sessionFactory.getCurrentSession().beginTransaction();
            Query query = sessionFactory.getCurrentSession().createQuery("from Accounts a where a.username = :user");
            query.setParameter("user", username);
            return (Accounts) query.uniqueResult();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } finally {
            sessionFactory.getCurrentSession().close();
        }
	}
	public Accounts findById(int id) {
        try {
            sessionFactory.getCurrentSession().beginTransaction();
            Query query = sessionFactory.getCurrentSession().createQuery("from Accounts a where a.id = :id");
            query.setParameter("id", id);
            return (Accounts) query.uniqueResult();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } finally {
            sessionFactory.getCurrentSession().close();
        }
    }
    public Accounts update(Accounts acc) {
        try {
            sessionFactory.getCurrentSession().beginTransaction();
            Query query = sessionFactory.getCurrentSession().createSQLQuery("UPDATE Accounts SET email=:email,fullname=:fullname,address=:add,dateofbirth=:dob,phonenumber=:phone WHERE Id=:id");
            query.setParameter("id", acc.getId());
            query.setParameter("email", acc.getEmail());
            query.setParameter("fullname", acc.getFullName());
            query.setParameter("add", acc.getAddress());
            query.setParameter("dob", acc.getDateOfBirth());
            query.setParameter("phone", acc.getPhoneNumber());
            query.executeUpdate();
            sessionFactory.getCurrentSession().getTransaction().commit();
            return acc;
        } catch (Exception ex) {
            sessionFactory.getCurrentSession().getTransaction().rollback();
            return null;
        } finally {
            sessionFactory.getCurrentSession().close();
        }
    }

    public Accounts edit(Accounts acc) {
        try {
            sessionFactory.getCurrentSession().beginTransaction();
            Query query = sessionFactory.getCurrentSession().createSQLQuery("UPDATE Accounts SET password=:pass, status=:status, userGroup=:user, email=:email,fullname=:fullname,address=:add,dateofbirth=:dob,phonenumber=:phone WHERE Id=:id");
            query.setParameter("id", acc.getId());
            query.setParameter("user", acc.getUserGroup());
            query.setParameter("pass", acc.getPassword());
            query.setParameter("email", acc.getEmail());
            query.setParameter("fullname", acc.getFullName());
            query.setParameter("add", acc.getAddress());
            query.setParameter("dob", acc.getDateOfBirth());
            query.setParameter("phone", acc.getPhoneNumber());
            query.setParameter("status", acc.getStatus());
            query.executeUpdate();
            sessionFactory.getCurrentSession().getTransaction().commit();
            return acc;
        } catch (Exception ex) {
            sessionFactory.getCurrentSession().getTransaction().rollback();
            return null;
        } finally {
            sessionFactory.getCurrentSession().close();
        }
    }

    public Accounts changepass(String pass,int id) {
        try {
            sessionFactory.getCurrentSession().beginTransaction();
            Query query = sessionFactory.getCurrentSession().createSQLQuery("UPDATE Accounts SET password=:pass WHERE Id=:id");            
            query.setParameter("pass", pass);
            query.setParameter("id", id);                                 
            query.executeUpdate();
            sessionFactory.getCurrentSession().getTransaction().commit();
            return this.findAccount(id);
        } catch (Exception ex) {
            sessionFactory.getCurrentSession().getTransaction().rollback();
            return null;
        } finally {
            sessionFactory.getCurrentSession().close();
		}
	}
    public boolean checkInfo(Accounts acc1, Accounts acc2) {
        if (acc1.getEmail().equals(acc2.getEmail())
                && acc1.getFullName().equals(acc2.getFullName())
                && acc1.getAddress().equals(acc2.getAddress())
                && acc1.getDateOfBirth().equals(acc2.getDateOfBirth())
                && acc1.getPhoneNumber().equals(acc2.getPhoneNumber())) {
            return true;
        } else {
            return false;
        }
    }

    public List<Countries> findCountries(String qur) {
        try {
            sessionFactory.getCurrentSession().beginTransaction();
            List<Countries> list = sessionFactory.getCurrentSession().createQuery("from Countries where Id like '%" + qur + "%' OR Countryname like '%" + qur + "%'").list();
            return list;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } finally {
            sessionFactory.getCurrentSession().close();
        }
    }
    
    public Suppliers addSupplier(Suppliers sup) {
        try {
            sessionFactory.getCurrentSession().beginTransaction();
            sessionFactory.getCurrentSession().save(sup);
            sessionFactory.getCurrentSession().getTransaction().commit();
            return sup;
        } catch (Exception ex) {
            sessionFactory.getCurrentSession().getTransaction().rollback();
            return null;
        } finally {
            sessionFactory.getCurrentSession().close();
        }
    }
    
    public Caterers addCaterer(Caterers cat) {
        try {
            sessionFactory.getCurrentSession().beginTransaction();
            sessionFactory.getCurrentSession().save(cat);
            sessionFactory.getCurrentSession().getTransaction().commit();
            return cat;
        } catch (Exception ex) {
            sessionFactory.getCurrentSession().getTransaction().rollback();
            return null;
        } finally {
            sessionFactory.getCurrentSession().close();
        }
    }
    //Phuc End
}
