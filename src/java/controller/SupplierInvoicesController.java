/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.List;
import javax.servlet.http.HttpSession;
import model.dao.AccountsDAO;
import model.dao.CaterersDAO;
import model.dao.SupplierChildInvoicesDAO;
import model.dao.SupplierInvoicesDAO;
import model.pojo.Accounts;
import model.pojo.Caterers;
import model.pojo.SupplierChildInvoices;
import model.pojo.SupplierInvoices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author MSI
 */
@Controller
@RequestMapping(value = "/supplierinvoice")
public class SupplierInvoicesController {

    SupplierInvoicesDAO supplierInvoicesDAO = new SupplierInvoicesDAO();

    @RequestMapping(value = "/listinvoices", method = RequestMethod.GET)
    public String listinvoice(ModelMap modelMap, HttpSession session) {
       AccountsDAO accountsDAO = new AccountsDAO(); 
        if (session.getAttribute("user") == null) {
            return "redirect:/account/login.htm";
        } else {
            Accounts acc = (Accounts) session.getAttribute("user");
            if (!acc.getUserGroup().equals("caterer") ) {
                return "redirect:/account/login.htm";
            }
        }


        Accounts account = (Accounts) session.getAttribute("user");
        CaterersDAO catererDAO = new CaterersDAO();
        Caterers caterer = catererDAO.findCatererByAccount(account.getId());
        List<SupplierInvoices> listInvoices = supplierInvoicesDAO.findSupplierInvoiceByCaterer(caterer.getId());
        modelMap.put("listinvoices", listInvoices);

        return "invoicesup/caterer_manage_list";
    }

    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    public String detail(@PathVariable(value = "id") int id,ModelMap modelMap, HttpSession session) {
        if (session.getAttribute("user") == null) {
            return "redirect:/account/login.htm";
        } else {
            Accounts acc = (Accounts) session.getAttribute("user");
            if (!acc.getUserGroup().equals("caterer") ) {
                return "redirect:/account/login.htm";
            }
        }
        SupplierChildInvoicesDAO supplierChildInvoicesDAO = new SupplierChildInvoicesDAO();
        List<SupplierChildInvoices> listSuppChildInvoices = supplierChildInvoicesDAO.findSupplierChildInvoiceBySupplierInvoices(id);
        modelMap.put("listsupplierchildinvoices", listSuppChildInvoices);
        double price=0;
        for(SupplierChildInvoices temp : listSuppChildInvoices){
            price = price + (temp.getQuantity() * temp.getMaterialPricePerUnit());
            modelMap.put("invoice", temp.getSupplierInvoices());
        }
        modelMap.put("totalprice",price);
        
        

        return "invoicesup/caterer_manage_detail";
    }

}
