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
import javax.validation.Valid;
import model.dao.AccountsDAO;
import model.dao.MaterialCategoriesDAO;
import model.dao.SuppliersDAO;
import model.pojo.Accounts;
import model.pojo.MaterialCategories;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author admin
 */
@Controller
@RequestMapping("/materialcategories")
public class MaterialCategoriesController {

    MaterialCategoriesDAO matcatDAO = new MaterialCategoriesDAO();
    SuppliersDAO supplierDAO = new SuppliersDAO();
    AccountsDAO accountsDAO = new AccountsDAO();
    public int supplierIdPublic;

    @RequestMapping(value = "/setUserId.htm", method = RequestMethod.POST)
    public @ResponseBody
    String setUserId(HttpServletRequest request, HttpServletResponse response) {
        int accountId = Integer.parseInt(request.getParameter("catererId"));
        //catererIdPublic = catererId;        
        Accounts accountLogin = accountsDAO.findAccount(accountId);
        if (accountLogin.getUserGroup().equals("supplier") || accountLogin.getUserGroup().equals("admin")) {
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

    @RequestMapping(value = "/listCategories/{supplierId}", method = RequestMethod.GET)
    public String showAll(@PathVariable(value = "supplierId") int supplierId, HttpSession sessions, ModelMap modelMap) {
        Accounts accountLogin = accountsDAO.findAccount(supplierId);
        if (accountLogin.getUserGroup().equals("supplier") || accountLogin.getUserGroup().equals("admin")) {
            try {
                supplierIdPublic = supplierDAO.findSupplierByAccountId(supplierId).getId();
                List<MaterialCategories> listCategories = matcatDAO.getCatergories(supplierIdPublic);                
                sessions.setAttribute("categories", listCategories);
                modelMap.put("category", new MaterialCategories());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return "supplier/materialcategories_showall";
        } else {
            return "redirect:/account/login.htm";
        }

    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(ModelMap modelMap, HttpSession sessions) {
        modelMap.put("category", new MaterialCategories());
        return "supplier/materialcategories_create";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@ModelAttribute(value = "category") MaterialCategories category, ModelMap modelMap, BindingResult bindingResult, HttpSession sessions) throws Exception {
        if (bindingResult.hasErrors()) {
            modelMap.put("message", "Create failed !");
            return "supplier/materialcategories_showall";
        } else {
            if (!"".equals(category.getCategoryName())) {
                matcatDAO.create(category, supplierIdPublic);
                sessions.setAttribute("messageCreate", "");
            } else {
                sessions.setAttribute("messageCreate", "Category Name not null");
            }
            List<MaterialCategories> listMaterialCategories = matcatDAO.getCatergories(supplierIdPublic);
            sessions.setAttribute("categories", listMaterialCategories);
            return "redirect:/materialcategories/listCategories/" + supplierIdPublic + ".htm";
        }
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable(value = "id") int id, ModelMap modelMap, HttpSession sessions) {
        List<MaterialCategories> listCategories = matcatDAO.getCatergories(supplierIdPublic);
        sessions.setAttribute("categories", listCategories);
        MaterialCategories test = matcatDAO.findCategory(id);
        sessions.setAttribute("categoryEdit", matcatDAO.findCategory(id));
        modelMap.put("messageEdit", test.getId());
        return "supplier/materialcategories_edit";
        //return "redirect:/materialcategories/listCategories/"+supplierIdPublic+".htm";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String edit(@ModelAttribute(value = "categoryEdit") @Valid MaterialCategories category, ModelMap modelMap, BindingResult bindingResult, HttpSession sessions) throws Exception {
        if (bindingResult.hasErrors()) {
            sessions.setAttribute("messageEdit", "Edit category failed !");
            return "redirect:/materialcategories/listCategories/" + supplierIdPublic + ".htm";
        } else {
            if (!"".equals(category.getCategoryName())) {
                matcatDAO.edit(category, supplierIdPublic);
                sessions.setAttribute("messageEdit", "");
            } else {
                sessions.setAttribute("messageEdit", "Category Name not null");
            }
            List<MaterialCategories> listCategories = matcatDAO.getCatergories(supplierIdPublic);
            sessions.setAttribute("categories", listCategories);
            return "redirect:/materialcategories/listCategories/" + supplierIdPublic + ".htm";
        }
    }

    @RequestMapping(value = "/cancel", method = RequestMethod.POST)
    public String cancelEdit(HttpSession sessions, ModelMap modelMap) throws Exception {
        List<MaterialCategories> listCategories = matcatDAO.getCatergories(supplierIdPublic);
        sessions.setAttribute("categories", listCategories);
        return "redirect:/materialcategories/listCategories/" + supplierIdPublic + ".htm";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable(value = "id") int id, ModelMap modelMap, HttpSession sessions) {
        matcatDAO.delete(matcatDAO.findCategory(id));
        List<MaterialCategories> listCategories = matcatDAO.getCatergories(supplierIdPublic);
        sessions.setAttribute("categories", listCategories);
        return "redirect:/materialcategories/listCategories/" + supplierIdPublic + ".htm";
    }
}
