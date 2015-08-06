/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import model.dao.AccountsDAO;
import model.dao.CountriesDAO;
import model.dao.SuppliersDAO;
import model.pojo.Accounts;
import model.pojo.Countries;
import model.pojo.Suppliers;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
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
@RequestMapping(value = "/suppliers")
public class SuppliersController {

    SuppliersDAO suppliersDao = new SuppliersDAO();

    @RequestMapping(value = "/listSuppliers", method = RequestMethod.GET)
    public String showAll(ModelMap modelMap, HttpSession session) {
        try {
            if (session.getAttribute("user") == null) {
                return "redirect:/account/login.htm";
            } else {
                Accounts acc = (Accounts) session.getAttribute("user");
                if (!acc.getUserGroup().equals("admin") ) {
                    return "redirect:/account/login.htm";
                }
            }

            List<Suppliers> list = suppliersDao.getListSuppliers();
            modelMap.put("suppliers", list);
            // modelMap.put("search",new Suppliers());
        } catch (Exception ex) {
            ex.printStackTrace();

        }
        return "admin/suppliers_list";
    }
    /*    @RequestMapping(value = "/listSuppliers", method = RequestMethod.GET)
     public String search(ModelMap modelMap){
        
     }*/

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String search(@RequestParam(value = "name") String name, ModelMap modelMap) {

        try {
            List<Suppliers> list = suppliersDao.findByName(name);
            modelMap.put("suppliers", list);

        } catch (Exception ex) {
            ex.printStackTrace();

        }

        return "admin/suppliers_list";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable(value = "id") int id, ModelMap modelMap, HttpSession session) {

        if (session.getAttribute("user") == null) {
            return "redirect:/account/login.htm";
        } else {
            Accounts acc = (Accounts) session.getAttribute("user");
            if (!acc.getUserGroup().equals("admin")) {
                return "redirect:/account/login.htm";
            }
        }

        Suppliers sup = suppliersDao.findSupplier(id);
        List<Countries> listCountries = new ArrayList<>();
        CountriesDAO countryDao = new CountriesDAO();
        listCountries = countryDao.getCountries();
        modelMap.put("listcountries", listCountries);
        modelMap.put("supplier", sup);
        return "admin/suppliers_edit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST/*, produces = "text/plain;charset=UTF-8"*/)
    public String edit(@ModelAttribute(value = "supplier") @Valid Suppliers supplier, ModelMap modelMap, BindingResult bindingResult, HttpSession sessions) {
        if (bindingResult.hasErrors()) {
            modelMap.put("message", "Edit supplier failed !");
            return "admin/suppliers_edit";
        } else {
            //   supplier.setSupplierName(name);
            suppliersDao.edit(supplier);
            modelMap.put("suppliers", suppliersDao.getListSuppliers());
            //return "admin/suppliers_list";
            return "redirect:../suppliers/listSuppliers.htm";
        }
    }

    @RequestMapping(value = "/create_account", method = RequestMethod.GET)
    public String create(ModelMap modelMap, HttpSession session) {
        try {
            if (session.getAttribute("user") == null) {
                return "redirect:/account/login.htm";
            } else {
                Accounts acc = (Accounts) session.getAttribute("user");
                if (!acc.getUserGroup().equals("admin")) {
                    return "redirect:/account/login.htm";
                }
            }
            modelMap.put("account", new Accounts());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "admin/suppliers_create_account";
    }

    @RequestMapping(value = "/create_account", method = RequestMethod.POST)
    public String create(@ModelAttribute(value = "account") Accounts account, ModelMap modelMap, BindingResult bindingResult, HttpSession sessions) {
        if (bindingResult.hasErrors()) {
            modelMap.put("message", "Create failed !");
            return "admin/suppliers_create_account";
        } else {
            account.setCustomerInvoiceses(null);
            account.setSupplierses(null);
            account.setStatus(0);
            account.setUserGroup("supplier");
            AccountsDAO accountDAO = new AccountsDAO();
            if (accountDAO.findByUsername(account.getUsername()) == null) {
                accountDAO.register(account);
                CountriesDAO countriesDAO = new CountriesDAO();
                Suppliers supplier = new Suppliers();
                supplier.setAccounts(account);
                List<Countries> listCountries = countriesDAO.getCountries();
                modelMap.put("listcountries", listCountries);
                modelMap.put("supplier", supplier);
                modelMap.put("message", "Create success !");
                return "admin/suppliers_create_supplier";
            } else {
                modelMap.put("message", "Username is already exists");
                return "admin/suppliers_create_account";
            }
            //  countryDAO.create(country);
            //   List<Countries> listCountries = countryDAO.getCountries();
            //  sessions.setAttribute("countries", listCountries);
        }
    }

    @RequestMapping(value = "/create_supplier", method = RequestMethod.POST)
    public String createSupp(@ModelAttribute(value = "supplier") Suppliers supplier, ModelMap modelMap, BindingResult bindingResult, HttpSession sessions) {
        if (bindingResult.hasErrors()) {
            modelMap.put("message", "Create failed !");
            return "admin/suppliers_create_supplier";
        } else {
            suppliersDao.create(supplier);
        }
        return "redirect:../suppliers/listSuppliers.htm";
    }

}
