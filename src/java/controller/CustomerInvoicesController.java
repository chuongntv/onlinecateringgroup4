/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.dao.CustomerChildInvoicesDAO;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import model.dao.CaterersDAO;
import model.dao.CustomerInvoicesDAO;
import model.dao.WorkerTypesDAO;
import model.dao.WorkersDAO;
import model.pojo.Accounts;
import model.pojo.Caterers;
import model.pojo.CustomerChildInvoices;
import model.pojo.CustomerInvoices;
import model.pojo.Workers;
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
 * @author Nathan Tran
 */
@Controller
@RequestMapping(value = "/customerInvoice")
public class CustomerInvoicesController {

    CustomerInvoicesDAO cusDAO = new CustomerInvoicesDAO();

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String showAll(HttpSession sessions) {
        Accounts user = (Accounts) sessions.getAttribute("user");
        if (user != null) {
            if (user.getUserGroup().equals("caterer")) {
                CaterersDAO catDAO = new CaterersDAO();
                Caterers cat = catDAO.findCatererByAccount(user.getId());
                List<CustomerInvoices> listInvoices = cusDAO.getListByCaterer(cat.getId());
                sessions.setAttribute("listInvoices", listInvoices);
                return "h_invoicecus/index";
            }
            if (user.getUserGroup().equals("customer")) {
                List<CustomerInvoices> list = cusDAO.getList(user.getId());
                sessions.setAttribute("listInvoices", list);
                return "h_invoicecus/index";
            }
            else return "redirect:/index.htm";
        } else {
            return "redirect:/account/login.htm";
        }
    }

    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    public String detail(@PathVariable(value = "id") int id, ModelMap modelMap, HttpSession session) {
        Accounts user = (Accounts) session.getAttribute("user");
        if (user == null) {
            return "redirect:/account/login.htm";
        } else {
//            if (!(user.getUserGroup().equals("caterer") || user.getUserGroup().equals("admin"))) {
//            if (!(user.getUserGroup().equals("caterer")||user.getUserGroup().equals("customer"))) {
//                return "redirect:/login.htm";
//            } else {
            CustomerChildInvoicesDAO cusChildDAO = new CustomerChildInvoicesDAO();
            List<CustomerChildInvoices> listCusChildInvoices = cusChildDAO.findCusChildInvoiceByCusInvoices(id);
            modelMap.put("listchild", listCusChildInvoices);
            CustomerInvoices customerInvoice = cusDAO.findById(id);
            modelMap.put("invoice", customerInvoice);
//            }
        }
        return "h_invoicecus/child_detail";
    }


    @RequestMapping(value = "/chooseWorker.htm", method = RequestMethod.POST)
    public @ResponseBody
    String showListMaterials(HttpServletRequest request, HttpServletResponse response) {
        int workerTypeId = Integer.parseInt(request.getParameter("workerTypeId"));
        WorkersDAO workDAO = new WorkersDAO();
        List<Workers> list = workDAO.getWorkers(workerTypeId);
        String result = "";
        result += "Worker Name: <select name='workers.id'>";
        for (Workers item : list) {
            result += "<option value='" + item.getId() + "'>" + item.getWorkerName() + "</option>";
        }
        result += "</select>";
        return result;
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable(value = "id") int id, ModelMap modelMap, HttpSession session) {
        Accounts user = (Accounts) session.getAttribute("user");
        if (user == null) {
            return "redirect:/account/login.htm";
        } else {
            if (!(user.getUserGroup().equals("caterer"))) {
                return "redirect:/login.htm";
            } else {
                modelMap.put("invoice", cusDAO.findById(id));
                WorkerTypesDAO workerTypesDAO = new WorkerTypesDAO();
                modelMap.put("workerType", workerTypesDAO.getListWorkerType());
                return "h_invoicecus/edit";
            }
        }
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String edit(@ModelAttribute(value = "invoice") @Valid CustomerInvoices invoice, BindingResult bindingResult, ModelMap modelMap, HttpSession sessions) {
        if (bindingResult.hasErrors()) {
            sessions.setAttribute("message", "Edit failed !");
            return "redirect:/index.htm";
        } else {
            cusDAO.edit(invoice);
            return "redirect:/customerInvoice/index.htm";
        }
    }

    @RequestMapping(value = "/editStatus/{id}", method = RequestMethod.GET)
    public String editStatus(@PathVariable(value = "id") int id, ModelMap modelMap, HttpSession session) {
        Accounts user = (Accounts) session.getAttribute("user");
        if (user == null) {
            return "redirect:/account/login.htm";
        } else {
            if (!(user.getUserGroup().equals("caterer")||user.getUserGroup().equals("customer"))) {
                return "redirect:/login.htm";
            } else {
                modelMap.put("invoice", cusDAO.findById(id));
                return "h_invoicecus/editStatus";
            }
        }
    }

    @RequestMapping(value = "/editStatus", method = RequestMethod.POST)
    public String editStatus(@ModelAttribute(value = "invoice") @Valid CustomerInvoices invoice, BindingResult bindingResult, ModelMap modelMap, HttpSession sessions) {
        if (bindingResult.hasErrors()) {
            sessions.setAttribute("message", "Edit failed !");
            return "redirect:/index.htm";
        } else {
            cusDAO.editStatus(invoice);
            return "redirect:/customerInvoice/index.htm";
        }
    }
}
