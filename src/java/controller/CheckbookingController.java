/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.dao.AccountsDAO;
import model.dao.SupplierInvoicesDAO;
import model.dao.SuppliersDAO;
import model.pojo.Accounts;
import model.pojo.MaterialCategories;
import model.pojo.Materials;
import model.pojo.SupplierInvoices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author admin
 */
@Controller
@RequestMapping(value = "/checkbooking")
public class CheckbookingController {
    
    public int supplierIdPublic;
    AccountsDAO accountDAO = new AccountsDAO();
    SuppliersDAO supplierDAO = new SuppliersDAO();
    SupplierInvoicesDAO supplierInvoiceDAO = new SupplierInvoicesDAO();
    
    @RequestMapping(value = "/setUserId.htm", method = RequestMethod.POST)
    public @ResponseBody
    String setUserId(HttpServletRequest request, HttpServletResponse response) {
        int accountId = Integer.parseInt(request.getParameter("catererId"));
        //catererIdPublic = catererId;        
        Accounts accountLogin = accountDAO.findAccount(accountId);
        if (accountLogin.getUserGroup().equals("caterer") || accountLogin.getUserGroup().equals("admin")) {
            if (!accountLogin.getUserGroup().equals("admin")) {
                supplierIdPublic = supplierDAO.findSupplierByAccountId(accountId).getId();
            }
            if (accountLogin.getUserGroup().equals("admin")) {
                supplierIdPublic = accountLogin.getId();
            }
            return "success";
        } else {
            return "error";
        }

    }
    
    //listbooking
    @RequestMapping(value = "/listbooking", method = RequestMethod.GET)
    public String listBooking(HttpSession sessions, ModelMap modelMap) {
        List<SupplierInvoices> listbooking = supplierInvoiceDAO.getAllSupplierInvoiceBySupplierId(supplierIdPublic, "waiting");
        modelMap.put("bookings", listbooking);
//        List<MaterialCategories> listCategories = materialCategoriesDAO.getCatergories(supplierIdPublic);
//        modelMap.put("categories", listCategories);
        return "supplier/booking";
    }
    
}
