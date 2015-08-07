/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import model.pojo.Cities;
import model.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author MSI
 */
public class CityDAO {

    @Autowired
    public final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public List<Cities> findListCities(int countryId) {
        try {
            if (!sessionFactory.getCurrentSession().getTransaction().isActive()) {
                sessionFactory.getCurrentSession().getTransaction().begin();
            }
            String strQuery = "select city, country  from Cities city  inner join   city.countries country where country.id ="+countryId;

            List list = sessionFactory.getCurrentSession().createQuery(strQuery).list();
            Iterator ite = list.iterator();
            List<Cities> listcities = new ArrayList<>();

            while (ite.hasNext()) {
                Object[] obj = (Object[]) ite.next();
                Cities city = new Cities();
                city = (Cities) obj[0];
                listcities.add(city);
            }
            return listcities;
        } catch (Exception ex) {
            return null;
        } finally {
            sessionFactory.getCurrentSession().close();
        }
	}
	public List<Cities> getCities() {
        try {
            sessionFactory.getCurrentSession().beginTransaction();
            Query query = sessionFactory.getCurrentSession().createQuery("from Cities");            
            return query.list();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } finally {
            sessionFactory.getCurrentSession().close();
        }
    }

}
