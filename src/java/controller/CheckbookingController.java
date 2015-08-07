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
import model.dao.SupplierChildInvoicesDAO;
import model.dao.SupplierInvoicesDAO;
import model.dao.SuppliersDAO;
import model.pojo.Accounts;
import model.pojo.SupplierChildInvoices;
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
    SupplierChildInvoicesDAO supplierChildInvoiceDAO = new SupplierChildInvoicesDAO();

    @RequestMapping(value = "/setUserId.htm", method = RequestMethod.POST)
    public @ResponseBody
    String setUserId(HttpServletRequest request, HttpServletResponse response) {
        int accountId = Integer.parseInt(request.getParameter("catererId"));
        //catererIdPublic = catererId;      
        //if (supplierIdPublic == 0) {
        Accounts accountLogin = accountDAO.findAccount(accountId);
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
        //}
        //return "success";
    }

    //listbooking
    @RequestMapping(value = "/listbooking", method = RequestMethod.GET)
    public String listBooking(HttpSession sessions, ModelMap modelMap) {
        if (supplierIdPublic != 0) {
            List<SupplierInvoices> listbooking = supplierInvoiceDAO.getAllSupplierInvoiceBySupplierId(supplierIdPublic, "waiting");
            modelMap.put("listbookings", listbooking);
//        List<MaterialCategories> listCategories = materialCategoriesDAO.getCatergories(supplierIdPublic);
//        modelMap.put("categories", listCategories);
            return "supplier/showalllistbooking";
        } else {
            return "redirect:/account/login.htm";
        }

    }

    @RequestMapping(value = "/showdetails.htm", method = RequestMethod.POST)
    public @ResponseBody
    String showListMaterials(HttpServletRequest request, HttpServletResponse response) {
        int supplierInvoice = Integer.parseInt(request.getParameter("idbooking"));
        List<SupplierChildInvoices> listChild = supplierChildInvoiceDAO.findSupplierChildInvoiceBySupplierInvoices(supplierInvoice);
        //String result = "<br>Next Random # is <b>" + r + "</b>. Generated on <b>" + new Date().toString() + "</b>";
        float totalPrice = 0;
        String result = "<h1>Details</h1>" + "<table border='1' >"
                + "<tr>"
                + "<th> Material Name </th>"
                + "<th> Price/Unit</th>"
                + "<th>Quantity</th>"
                + "</tr>" + "";
        for (SupplierChildInvoices item : listChild) {
            result += "<tr><td>" + item.getMaterialName() + "</td>"
                    + "<td>" + item.getMaterialPricePerUnit() + "</td>"
                    + "<td>" + item.getQuantity() + "</td>"
                    + "</tr>";
            totalPrice += item.getMaterialPricePerUnit() * item.getQuantity();
        }
        result += "</table><br>";
        result += "<strong>Total Price: " + totalPrice + "</strong>";
        //result += "<input type=\"button\" value=\"Add to bill\" onclick='addToBill();'/>";
        //result += "<button onclick='showMaterials("+categoryId+");'>Add To Bill</button>";
        return result;
    }

    @RequestMapping(value = "/shipping/{bookingId}", method = RequestMethod.GET)
    public String shipBill(@PathVariable(value = "bookingId") int bookingId, ModelMap modelMap, HttpSession sessions) {
        supplierInvoiceDAO.updateStatusForInvoice(bookingId, "shipping");
        List<SupplierInvoices> listbooking = supplierInvoiceDAO.getAllSupplierInvoiceBySupplierId(supplierIdPublic, "waiting");
        sessions.setAttribute("listbookings", listbooking);
        return "redirect:/checkbooking/listbooking.htm";
    }

    @RequestMapping(value = "/cancel/{id}", method = RequestMethod.GET)
    public String cancelBill(@PathVariable(value = "id") int bookingId, ModelMap modelMap, HttpSession sessions) {
        supplierInvoiceDAO.updateStatusForInvoice(bookingId, "cancel");
        List<SupplierInvoices> listbooking = supplierInvoiceDAO.getAllSupplierInvoiceBySupplierId(supplierIdPublic, "waiting");
        sessions.setAttribute("listbookings", listbooking);
        return "redirect:/checkbooking/listbooking.htm";
    }

    //showlistshipping
    @RequestMapping(value = "/showlistshipping", method = RequestMethod.GET)
    public String listBillShipping(HttpSession sessions, ModelMap modelMap) {
        List<SupplierInvoices> listbooking = supplierInvoiceDAO.getAllSupplierInvoiceBySupplierId(supplierIdPublic, "shipping");
        modelMap.put("listbookingsshipping", listbooking);
//        List<MaterialCategories> listCategories = materialCategoriesDAO.getCatergories(supplierIdPublic);
//        modelMap.put("categories", listCategories);
        return "supplier/showalllistbookingshipping";
    }

    @RequestMapping(value = "/completed/{id}", method = RequestMethod.GET)
    public String completedBill(@PathVariable(value = "id") int bookingId, ModelMap modelMap, HttpSession sessions) {
        supplierInvoiceDAO.updateStatusForInvoice(bookingId, "completed");
        List<SupplierInvoices> listbooking = supplierInvoiceDAO.getAllSupplierInvoiceBySupplierId(supplierIdPublic, "completed");
        sessions.setAttribute("listbookings", listbooking);
        return "redirect:/checkbooking/showlistshipping.htm";
    }
    //completedshipping
    @RequestMapping(value = "/completedshipping/{id}", method = RequestMethod.GET)
    public String completedBillShipping(@PathVariable(value = "id") int bookingId, ModelMap modelMap, HttpSession sessions) {
        supplierInvoiceDAO.updateStatusForInvoice(bookingId, "completed");
        List<SupplierInvoices> listbooking = supplierInvoiceDAO.getAllSupplierInvoiceBySupplierId(supplierIdPublic, "shipping");
        sessions.setAttribute("listbookings", listbooking);
        return "redirect:/checkbooking/showlistshipping.htm";
    }
    //showlistcompleted

    @RequestMapping(value = "/showlistcompleted", method = RequestMethod.GET)
    public String listBillCompleted(HttpSession sessions, ModelMap modelMap) {
        List<SupplierInvoices> listbooking = supplierInvoiceDAO.getAllSupplierInvoiceBySupplierId(supplierIdPublic, "completed");
        modelMap.put("listbookingscompleted", listbooking);
        return "supplier/showalllistbookingcompleted";
    }
    //showlistcancel
    @RequestMapping(value = "/showlistcancel", method = RequestMethod.GET)
    public String listBillCancel(HttpSession sessions, ModelMap modelMap) {
        List<SupplierInvoices> listbooking = supplierInvoiceDAO.getAllSupplierInvoiceBySupplierId(supplierIdPublic, "cancel");
        modelMap.put("listbookingscancel", listbooking);
        return "supplier/showalllistbookingcancel";
    }

}
