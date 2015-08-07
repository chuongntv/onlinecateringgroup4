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
import model.pojo.Caterers;
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
@RequestMapping(value = "/managercaterer")
public class ManagementCatererController {
    
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
    
    @RequestMapping(value = "/listCaterers", method = RequestMethod.GET)
    public String showAllCustomers(ModelMap modelMap) {
        List<Caterers> listCaterers = catererDAO.getAllCaterersWithStatus(0);
        modelMap.put("caterers", listCaterers);
        return "admin/caterer_showall";
    }
    //listCaterersBlock
    @RequestMapping(value = "/listCaterersBlock", method = RequestMethod.GET)
    public String showAllCustomersBlock(ModelMap modelMap) {
        List<Caterers> listCaterers = catererDAO.getAllCaterersWithStatus(1);
        modelMap.put("caterersblock", listCaterers);
        return "admin/caterer_block_showall";
    }
    
    
    @RequestMapping(value = "/block/{id}", method = RequestMethod.GET)
    public String block(@PathVariable(value = "id") int id, ModelMap modelMap, HttpSession sessions) {
        accountsDAO.block(id);
        List<Caterers> listCaterers = catererDAO.getAllCaterersWithStatus(0);
        sessions.setAttribute("caterers", listCaterers);
        return "redirect:/managercaterer/listCaterers.htm";
    }
    
    @RequestMapping(value = "/active/{id}", method = RequestMethod.GET)
    public String active(@PathVariable(value = "id") int id, ModelMap modelMap, HttpSession sessions) {
        accountsDAO.active(id);
        List<Caterers> listCaterers = catererDAO.getAllCaterersWithStatus(1);
        modelMap.put("caterersblock", listCaterers);
        return "redirect:/managercaterer/listCaterersBlock.htm";
    }
    
}
