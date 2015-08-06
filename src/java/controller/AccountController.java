/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.List;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import model.dao.AccountsDAO;
import model.pojo.Accounts;
import model.pojo.Caterers;
import model.pojo.Countries;
import model.pojo.Suppliers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author admin
 */
@Controller
@RequestMapping("/account")
public class AccountController {

    AccountsDAO accDAO = new AccountsDAO();

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String edit(ModelMap modelMap) {
        modelMap.put("account", new Accounts());
        return "account/register";
        //return "register";
    }


    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String create(@ModelAttribute(value = "account") Accounts account, ModelMap modelMap, BindingResult bindingResult, HttpSession sessions) throws Exception {
        if (bindingResult.hasErrors()) {
            sessions.setAttribute("message", "Register failed !");
            return "account/register";
        } else {
            Accounts temp = new Accounts();
            temp = accDAO.findByUsername(account.getUsername());
            if (temp != null) {
                sessions.setAttribute("message", "Register failed! Username existed");
                return "account/register";
            } else {
                accDAO.register(account);
                sessions.setAttribute("message", "Register success !");
                sessions.setAttribute("user", account);
                //sesssion by admin
                sessions.setAttribute("userId",account.getId());
                if (account.getUserGroup().equals("customer")) {
                    return "redirect:../index.htm";
                }
                if (account.getUserGroup().equals("supplier")) {
                    return "redirect:create_supplier.htm";
                }
                if (account.getUserGroup().equals("caterer")) {
                    return "redirect:create_caterer.htm";
                } else {
                    List<Accounts> list = accDAO.getAccounts();
                    sessions.setAttribute("accounts", list);
                    return "redirect:/account/index";
                }
            }
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(ModelMap modelMap) {
        modelMap.put("account", new Accounts());
        return "account/login";
    }

    
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String indexpage( HttpSession sessions){
        List<Accounts> list = accDAO.getAccounts();
        sessions.setAttribute("accounts", list);
        return "account/index";

    }
    
    
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@ModelAttribute(value = "account") Accounts account, ModelMap modelMap, BindingResult bindingResult, HttpSession sessions) throws Exception {
        if (bindingResult.hasErrors()) {
            modelMap.put("message", "login failed !");
            return "account/login";
        } else {
            Accounts temp = new Accounts();
            temp = accDAO.findAccounts(account.getUsername(), account.getPassword());
            if (temp != null) {
                List<Accounts> list = accDAO.getAccounts();
                sessions.setAttribute("accounts", list);
                sessions.setAttribute("user", temp);
                sessions.setAttribute("userId",temp.getId());
                if (temp.getUserGroup().equals("customer") || temp.getUserGroup().equals("supplier") || temp.getUserGroup().equals("caterer")) {
                    return "redirect:/index.htm";
                } else {
                    return "redirect:/account/index.htm";
                }
            } else {
                modelMap.put("message", "login failed !");
                return "account/login";
            }
        }
    }

    @Autowired
    private JavaMailSender mailSender;

    @RequestMapping(value = "/forgot", method = RequestMethod.GET)
    public String forgot(ModelMap modelMap) {
        modelMap.put("account", new Accounts());
        return "account/forgot";
    }

    @RequestMapping(value = "/forgot", method = RequestMethod.POST)
    public String forgot(@ModelAttribute(value = "account") Accounts account, ModelMap modelMap, BindingResult bindingResult, HttpSession sessions) {
        if (bindingResult.hasErrors()) {
            sessions.setAttribute("message", "Reset password failed! Wrong Information !");
            return "account/login";
        } else {
            String username = account.getUsername();
            String email = account.getEmail();
            Accounts temp = accDAO.findByUsername(username);
            if (temp != null) {
                SimpleMailMessage mail = new SimpleMailMessage();
                mail.setTo(email);
                mail.setSubject("Caterer Online");
                mail.setText("Here is an account that you forgot password! Username: " + username + ". Your password:" + temp.getPassword());
                // sends the e-mail
                mailSender.send(mail);
                sessions.setAttribute("message", "Mail has been sent!");
                return "redirect:../index";
            } else {
                sessions.setAttribute("message", "Username not found!");
                return "account/forgot";
            }
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String update(ModelMap modelMap, HttpSession sessions) {
        Accounts accUpdate = (Accounts) sessions.getAttribute("user");
        modelMap.put("account", accUpdate);
        return "account/update";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@ModelAttribute(value = "account") @Valid Accounts account, ModelMap modelMap, BindingResult bindingResult, HttpSession sessions) {
        if (bindingResult.hasErrors()) {
            sessions.setAttribute("message", "Update failed !");
            return "countries_edit";
        } else {
            accDAO.update(account);
            Accounts temp = (Accounts) sessions.getAttribute("user");
            Accounts newUser = accDAO.findByUsername(temp.getUsername());
            sessions.setAttribute("user", newUser);
            return "redirect:/account/index.htm";
        }
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(ModelMap modelMap, @PathVariable(value = "id") int id) {
        modelMap.put("account", accDAO.findById(id));
        return "account/edit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String edit(@ModelAttribute(value = "account") @Valid Accounts account, ModelMap modelMap, BindingResult bindingResult, HttpSession sessions) {
        if (bindingResult.hasErrors()) {
            sessions.setAttribute("message", "Update failed !");
            return "countries_edit";
        } else {
            accDAO.edit(account);
            Accounts temp = (Accounts) sessions.getAttribute("user");
            Accounts newUser = accDAO.findByUsername(temp.getUsername());
            sessions.setAttribute("user", newUser);
            return "redirect:/account/index.htm";
        }
    }

    @RequestMapping(value = "/getCountries", method = RequestMethod.GET)
    public @ResponseBody
    List<Countries> getCountries(@RequestParam("term") String name) {
        List<Countries> countries = accDAO.findCountries(name);
        return countries;
    }

    @RequestMapping(value = "/create_supplier", method = RequestMethod.GET)
    public String create_supplier(ModelMap modelMap, HttpSession sessions) {
        Suppliers sup = new Suppliers();
        Accounts acc = (Accounts) sessions.getAttribute("user");
        sup.setAccounts(acc);
        modelMap.put("supplier", sup);
        return "account/create_supplier";
    }

    @RequestMapping(value = "/create_supplier", method = RequestMethod.POST)
    public String create_supplier(@ModelAttribute(value = "supplier") Suppliers supplier, ModelMap modelMap, BindingResult bindingResult, HttpSession sessions) {
        if (bindingResult.hasErrors()) {
            sessions.setAttribute("message", "Create supplier failed !");
            return "redirect:account/create_supplier";
        } else {
            accDAO.addSupplier(supplier);
            sessions.setAttribute("user", accDAO.findById(supplier.getAccounts().getId()));
            sessions.setAttribute("message", "Create supplier completed!");
            return "redirect:/index.htm";
        }
    }

    @RequestMapping(value = "/create_caterer", method = RequestMethod.GET)
    public String create_caterer(ModelMap modelMap, HttpSession sessions) {
        Caterers cat = new Caterers();
        Accounts acc = (Accounts) sessions.getAttribute("user");
        cat.setAccounts(acc);
        modelMap.put("caterer", cat);
        return "account/create_caterer";
    }

    @RequestMapping(value = "/create_caterer", method = RequestMethod.POST)
    public String create_caterer(@ModelAttribute(value = "caterer") Caterers caterer, ModelMap modelMap, BindingResult bindingResult, HttpSession sessions) {
        if (bindingResult.hasErrors()) {
            sessions.setAttribute("message", "Create caterer failed !");
            return "redirect:account/create_caterer";
        } else {
            accDAO.addCaterer(caterer);
            sessions.setAttribute("user", accDAO.findById(caterer.getAccounts().getId()));
            sessions.setAttribute("message", "Create caterer completed!");
            return "redirect:/index.htm";
        }
    }
}
