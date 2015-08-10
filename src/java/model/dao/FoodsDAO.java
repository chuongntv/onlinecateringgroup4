/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import model.pojo.Foods;
import model.pojo.Suppliers;
import model.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author MSI
 */
public class FoodsDAO {

    @Autowired
    public final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public List<Foods> getFoodsByMenu(int menuId) {
        try {
            if (!sessionFactory.getCurrentSession().getTransaction().isActive()) {
                sessionFactory.getCurrentSession().getTransaction().begin();
            }
            Query query = sessionFactory.getCurrentSession().createQuery("select food,submenu,menu from Foods food join food.subMenus submenu join submenu.menus menu where menu.id =:menuId ");
            query.setParameter("menuId", menuId);
            List list = query.list();
            Iterator ite = list.iterator();
            List<Foods> listFoods = new ArrayList<>();

            while (ite.hasNext()) {
                Object[] obj = (Object[]) ite.next();
                Foods food = new Foods();
                food = (Foods) obj[0];
                listFoods.add(food);
            }
            return listFoods;
        } catch (Exception ex) {
            ex.printStackTrace();
            return  null;
        } finally {
            sessionFactory.getCurrentSession().close();
        }

    }
    public Foods create(Foods food) {
        try {
            sessionFactory.getCurrentSession().beginTransaction();
            sessionFactory.getCurrentSession().save(food);
            sessionFactory.getCurrentSession().getTransaction().commit();
            return food;
        } catch (Exception ex) {
            sessionFactory.getCurrentSession().getTransaction().rollback();
            return null;
        } finally {
            sessionFactory.getCurrentSession().close();
        }
    }

    public List<Foods> listFoodsBySubMenu(int id) {
        try {
            if (!sessionFactory.getCurrentSession().getTransaction().isActive()) {
                sessionFactory.getCurrentSession().getTransaction().begin();
            }
            Query query = sessionFactory.getCurrentSession().createQuery("select food,submenu,menu from Foods food join food.subMenus submenu join submenu.menus menu where submenu.id =:id ");
            query.setParameter("id", id);
            List list = query.list();
            Iterator ite = list.iterator();
            List<Foods> listFoods = new ArrayList<>();

            while (ite.hasNext()) {
                Object[] obj = (Object[]) ite.next();
                Foods food = new Foods();
                food = (Foods) obj[0];
                listFoods.add(food);
            }
            return listFoods;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } finally {
            sessionFactory.getCurrentSession().close();
        }
    }

    public Foods findFoodById(int id) {
        try {
            if (!sessionFactory.getCurrentSession().getTransaction().isActive()) {
                sessionFactory.getCurrentSession().getTransaction().begin();
            }
            Query query = sessionFactory.getCurrentSession().createQuery("select food,submenu,menu from Foods food join food.subMenus submenu join submenu.menus menu where food.id =:id ");
            query.setParameter("id", id);
            Object[] obj = (Object[]) query.uniqueResult();
            Foods food = (Foods) obj[0];
            return food;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } finally {
            sessionFactory.getCurrentSession().close();
        }
    }

    public Foods editImage(Foods food) {
        try {
            if (!sessionFactory.getCurrentSession().getTransaction().isActive()) {
                sessionFactory.getCurrentSession().getTransaction().begin();
            }
            String strQuery = "UPDATE Foods SET Image = '" + food.getImage() + "' where Id= " + food.getId();
            Query query = sessionFactory.getCurrentSession().createSQLQuery(strQuery);
            //    query.setParameter("image", menu.getImage());
            // query.setParameter("id", menu.getId());
            query.executeUpdate();
            sessionFactory.getCurrentSession().getTransaction().commit();

            return food;
        } catch (Exception ex) {
            sessionFactory.getCurrentSession().getTransaction().rollback();
            return null;
        } finally {
            sessionFactory.getCurrentSession().close();
        }
    }

    public Foods edit(Foods food) {
        try {
            if (!sessionFactory.getCurrentSession().getTransaction().isActive()) {
                sessionFactory.getCurrentSession().getTransaction().begin();
            }
            sessionFactory.getCurrentSession().merge(food);
            sessionFactory.getCurrentSession().getTransaction().commit();

            return food;
        } catch (Exception ex) {
            sessionFactory.getCurrentSession().getTransaction().rollback();
            return null;
        } finally {
            sessionFactory.getCurrentSession().close();
        }

    }
    public void delete(Foods food) {
        try {
            sessionFactory.getCurrentSession().beginTransaction();
            sessionFactory.getCurrentSession().delete(food);
            sessionFactory.getCurrentSession().getTransaction().commit();
        } catch (Exception ex) {
            sessionFactory.getCurrentSession().getTransaction().rollback();
        } finally {
            sessionFactory.getCurrentSession().close();
        }
    }

    
}
