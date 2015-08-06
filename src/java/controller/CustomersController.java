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
import model.dao.CaterersDAO;
import model.pojo.Accounts;
import model.pojo.Materials;
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
@RequestMapping(value = "/customers")
public class CustomersController {
    
    AccountsDAO accountsDAO = new AccountsDAO();
    CaterersDAO catererDAO = new CaterersDAO();
    
    @RequestMapping(value = "/setUserId.htm", method = RequestMethod.POST)
    public @ResponseBody
    String setUserId(HttpServletRequest request, HttpServletResponse response) {
        int accountId = Integer.parseInt(request.getParameter("catererId"));
        //catererIdPublic = catererId;        
        Accounts accountLogin = accountsDAO.findAccount(accountId);
        if (accountLogin.getUserGroup().equals("admin")) {            
            return "success";
        } else {
            return "error";
        }

    }
    //listAccountsBlock
    @RequestMapping(value = "/listCustomers", method = RequestMethod.GET)
    public String showAllCustomers(ModelMap modelMap) {
        List<Accounts> listAccounts = accountsDAO.findAllCustomers();
        modelMap.put("customers", listAccounts);
        return "admin/customers_showall";
    }
    @RequestMapping(value = "/listCustomersBlock", method = RequestMethod.GET)
    public String showAllCustomersBlock(ModelMap modelMap) {
        List<Accounts> listAccounts = accountsDAO.findAllCustomersBlocked();
        modelMap.put("customersBlocked", listAccounts);
        return "admin/customers_block_showall";
    }
    @RequestMapping(value = "/block/{id}", method = RequestMethod.GET)
    public String block(@PathVariable(value = "id") int id, ModelMap modelMap, HttpSession sessions) {
        accountsDAO.block(id);
        List<Accounts> listAccounts = accountsDAO.findAllCustomers();
        sessions.setAttribute("customers", listAccounts);
        return "redirect:/customers/listCustomers.htm";
    }
    @RequestMapping(value = "/active/{id}", method = RequestMethod.GET)
    public String active(@PathVariable(value = "id") int id, ModelMap modelMap, HttpSession sessions) {
        accountsDAO.active(id);
        List<Accounts> listAccounts = accountsDAO.findAllCustomersBlocked();
        sessions.setAttribute("customersBlocked", listAccounts);
        return "redirect:/customers/listCustomersBlock.htm";
    }
}
