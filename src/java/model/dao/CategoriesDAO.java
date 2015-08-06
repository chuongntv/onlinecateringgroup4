/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.util.List;
import model.pojo.CategoryTypes;
import model.util.HibernateUtil;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author MSI
 */
public class CategoriesDAO {
        @Autowired
    public final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    public List<CategoryTypes> getCategoryTypes() {
        try {
            sessionFactory.getCurrentSession().beginTransaction();
            List<CategoryTypes> list = sessionFactory.getCurrentSession().createQuery("from CategoryTypes").list();
            return list;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } finally {
            sessionFactory.getCurrentSession().close();
        }

    }
}
