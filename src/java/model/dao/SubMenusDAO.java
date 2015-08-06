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
import model.pojo.SubMenus;
import model.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author MSI
 */
public class SubMenusDAO {

    @Autowired
    public final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public List<SubMenus> getSubMenuByMenu(int menuId) {
        try {
            if (!sessionFactory.getCurrentSession().getTransaction().isActive()) {
                sessionFactory.getCurrentSession().getTransaction().begin();
            }
            Query query = sessionFactory.getCurrentSession().createQuery("select submenu, menu from SubMenus submenu join submenu.menus menu where menu.id=:menuId");
            query.setParameter("menuId", menuId);
            List list = query.list();
            Iterator ite = list.iterator();
            List<SubMenus> listSubMenus = new ArrayList<>();

            while (ite.hasNext()) {
                Object[] obj = (Object[]) ite.next();
                SubMenus submenu = new SubMenus();
                submenu = (SubMenus) obj[0];
                listSubMenus.add(submenu);
            }
            return listSubMenus;
            
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } finally {
            sessionFactory.getCurrentSession().close();
        }
        
    }

}
