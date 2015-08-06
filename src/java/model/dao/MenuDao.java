/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import model.pojo.Menus;
import model.pojo.Suppliers;
import model.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author MSI
 */
public class MenuDao {

    @Autowired
    public final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public List<Menus> findListMenu(int cateType,int cityId,int numberOfPeople) {
        try {
            if (!sessionFactory.getCurrentSession().getTransaction().isActive()) {
                sessionFactory.getCurrentSession().getTransaction().begin();
            }
            String strQuery = "select m,category,caterer,city  from Menus m    join   m.categoryTypes category join m.caterers caterer join caterer.cities city  where m.minPlate < "+numberOfPeople+" and m.maxPlate >"+numberOfPeople+" and city.id= "+cityId+" and category.id = "+cateType ;
        //    Query query = sessionFactory.getCurrentSession().createQuery("select m,category,caterer  from Menus m     inner join   m.categoryTypes category inner join m.caterers caterer  where m.minPlate < :numberOfPeople and m.maxPlate >:numberOfPeople and caterer.cityId= :cityId and m.categoryTypeId = :cateType ");
          //  query.setParameter("numberOfPeople", numberOfPeople);
          //  query.setParameter("cityId", cityId);
         //   query.setParameter("cateType", cateType);
            Query query = sessionFactory.getCurrentSession().createQuery(strQuery);
            List list = query.list();
            Iterator ite = list.iterator();
            List<Menus> listMenus = new ArrayList<>();
            while (ite.hasNext()) {
                Object[] obj = (Object[]) ite.next();
                Menus menu = new Menus();
                menu = (Menus) obj[0];
                listMenus.add(menu);
            }
            return listMenus;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } finally {
            sessionFactory.getCurrentSession().close();
        }
    }
    
        public Menus findMenu(int id){
        try{
            if (!sessionFactory.getCurrentSession().getTransaction().isActive()) {
                sessionFactory.getCurrentSession().getTransaction().begin();
            }
            Query query = sessionFactory.getCurrentSession().createQuery("select m,category,caterer,city  from Menus m    join   m.categoryTypes category join m.caterers caterer join caterer.cities city  where m.id =:id");
            query.setParameter("id", id);
            
             query.setInteger("id", id);
            Object[] obj = (Object[]) query.uniqueResult();
            Menus menu = (Menus) obj[0];
            return menu;
            
        }catch(Exception ex){
            return null;
        }finally{
            sessionFactory.getCurrentSession().close();
        }
    }
    
}
