/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.servlet.http.HttpSession;
import model.dao.AccountsDAO;
import model.dao.CategoriesDAO;
import model.dao.CityDAO;
import model.dao.CountriesDAO;
import model.dao.CustomerChildInvokesDAO;
import model.dao.CustomerInvokesDAO;
import model.dao.FoodsDAO;
import model.dao.MenuDao;
import model.dao.SubMenusDAO;
import model.dao.SuppliersDAO;
import model.pojo.Accounts;
import model.pojo.CategoryTypes;
import model.pojo.Caterers;
import model.pojo.Cities;
import model.pojo.Countries;
import model.pojo.CustomerChildInvoices;
import model.pojo.CustomerInvoices;
import model.pojo.Foods;
import model.pojo.Menus;
import model.pojo.SubMenus;
import model.pojo.Suppliers;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author MSI
 */
@Controller
@RequestMapping(value = "/order")
public class OrderController {

    MenuDao menuDao = new MenuDao();

    @RequestMapping(value = "/getcountry", method = RequestMethod.GET)
    public String orderStep1(ModelMap modelMap, HttpSession session) {
        try {
            if (session.getAttribute("user") == null) {
                return "redirect:/account/login.htm";
            } else {
                Accounts acc = (Accounts) session.getAttribute("user");
                if (!acc.getUserGroup().equals("customer")) {
                    return "redirect:/account/login.htm";
                }
            }

            CountriesDAO countryDao = new CountriesDAO();
            List<Countries> list = countryDao.getCountries();
            modelMap.put("countries", list);
            // modelMap.put("search",new Suppliers());
        } catch (Exception ex) {
            ex.printStackTrace();

        }
        return "invoicecus/customer_order_getcountry";

    }

    @RequestMapping(value = "/getchoice", method = RequestMethod.POST)
    public String orderStep2(@RequestParam(value = "countryid") int countryId, ModelMap modelMap) {
        try {
            CityDAO cityDao = new CityDAO();
            CategoriesDAO cateDao = new CategoriesDAO();
            List<Cities> listCities = cityDao.findListCities(countryId);
            List<CategoryTypes> listCate = cateDao.getCategoryTypes();
            modelMap.put("cities", listCities);
            modelMap.put("catetypes", listCate);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "invoicecus/customer_order_getchoice";
    }

    @RequestMapping(value = "/getmenus", method = RequestMethod.POST)
    public String orderStep3(@RequestParam(value = "cityid") int cityId, @RequestParam(value = "cateid") int cateId, @RequestParam(value = "numberofpeople") int numOfPeople, ModelMap modelMap) {
        try {
            List<Menus> listMenus = menuDao.findListMenu(cateId, cityId, numOfPeople);
            modelMap.put("listmenu", listMenus);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return "invoicecus/customer_order_getmenus";
    }
    //List<String> listFoods = null;
    //  int idMenu;

    @RequestMapping(value = "/choosefoods/{id}", method = RequestMethod.GET)
    public String orderStep4(@PathVariable(value = "id") int id, ModelMap modelMap, HttpSession session) {
        try {
            //  idMenu = id;
            if (session.getAttribute("user") == null) {
                return "redirect:/account/login.htm";
            } else {
                Accounts acc = (Accounts) session.getAttribute("user");
                if (!acc.getUserGroup().equals("customer")) {
                    return "redirect:/account/login.htm";
                }
            }
            session.setAttribute("idmenu", id);
            FoodsDAO foodDao = new FoodsDAO();
            List<Foods> listFoods = foodDao.getFoodsByMenu(id);
            SubMenusDAO subMenuDao = new SubMenusDAO();
            List<SubMenus> listSubMenus = subMenuDao.getSubMenuByMenu(id);

            modelMap.put("listsubmenus", listSubMenus);
            modelMap.put("listfoods", listFoods);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "invoicecus/customer_order_choosefoods";
    }

    @RequestMapping(value = "/choosefoods", method = RequestMethod.POST)
    public String orderStep4(@RequestParam(value = "foods") List<String> listFoods, ModelMap modelMap, HttpSession sessions) {
        //this.listFoods = listFoods;
        sessions.setAttribute("listfoods", listFoods);
        try {
            /*    //////////////////////////////////
             AccountsDAO accountsDAO = new AccountsDAO();
             Accounts account = accountsDAO.findByUsername("thienly");
             sessions.setAttribute("user", account);
             ////////////////////////////////////// */

            CustomerInvoices cusInvoke = new CustomerInvoices();
            Accounts acc = (Accounts) sessions.getAttribute("user");
            cusInvoke.setAccounts(acc);
            //CustomerChildInvoices cusChildInvoke = new CustomerChildInvoices();
            modelMap.put("customerinvoke", cusInvoke);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "invoicecus/customer_order_confirm";

    }

    @RequestMapping(value = "/confirm", method = RequestMethod.POST)
    public String orderStep5(@ModelAttribute(value = "customerinvoke") CustomerInvoices cusInvoke, ModelMap modelMap, HttpSession sessions) {
        try {

            CustomerInvokesDAO customerInvokesDAO = new CustomerInvokesDAO();
            int idMenu = (Integer) sessions.getAttribute("idmenu");
            List<String> listFoods = (List<String>) sessions.getAttribute("listfoods");
            Caterers caterer = menuDao.findMenu(idMenu).getCaterers();
            cusInvoke.setCaterers(caterer);
            Menus menu = menuDao.findMenu(idMenu);
            cusInvoke.setCostPerPlate(menu.getCostPerPlate());
            if ((cusInvoke.getNumberOfPlate() < menu.getMinPlate()) || (cusInvoke.getNumberOfPlate() > menu.getMaxPlate())) {
                modelMap.put("messagenumber", "Number of plate must be <" + menu.getMinPlate() + "and >" + menu.getMaxPlate());
                return "invoicecus/customer_order_confirm";
            }

            Accounts account = (Accounts) sessions.getAttribute("user");
            cusInvoke.setAccounts(account);

            /*                   DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
             Date date = new Date();
             date.getDate();
             date.getMonth();
             date.getYear();
             if(){
                
             }*/
            SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
            //String inputString1
            String inputString2 = cusInvoke.getDeliveryDate();
            Date date2 = null;
            try {
                Date date1 = new Date();
                date2 = myFormat.parse(inputString2);
                long diff = date2.getTime() - date1.getTime();
                System.out.println("Days: " + TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS));
                if (TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS) < 7) {
                    modelMap.put("messagedate", "Date Delivery must be earlyer than now 7 days");
                    return "invoicecus/customer_order_confirm";
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
            SimpleDateFormat myFormat2 = new SimpleDateFormat("MM/dd/yyyy");
            Date date = new Date();
            /*    String year= String.valueOf(date.getYear());
             String month= String.valueOf(date.getMonth());
             String day= String.valueOf(date.getDate());
             String invoiceDate = year+" "+month+" "+day;
             cusInvoke.setInvoiceDate(invoiceDate);*/
            cusInvoke.setInvoiceDate(myFormat2.format(date));
            cusInvoke.setDeliveryDate(myFormat2.format(date2));
            cusInvoke.setStatus("waiting");
            CustomerInvoices cusInvoiceSuccess = customerInvokesDAO.create(cusInvoke);
            if (listFoods != null) {
                CustomerChildInvokesDAO customerChildInvokesDAO = new CustomerChildInvokesDAO();
                for (String food : listFoods) {
                    CustomerChildInvoices customerChildInvoices = new CustomerChildInvoices();
                    customerChildInvoices.setCustomerInvoices(cusInvoiceSuccess);
                    customerChildInvoices.setFoodName(food);
                    customerChildInvokesDAO.create(customerChildInvoices);
                }
            } else {
                modelMap.put("message", "you must chose at least 1 item");
                return "redirect:order/choosefoods/" + idMenu + ".htm";
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        modelMap.put("message", "Order success");
        return "invoicecus/customer_order_result";
    }
}
