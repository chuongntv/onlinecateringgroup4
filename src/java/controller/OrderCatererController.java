/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import model.dao.AccountsDAO;
import model.dao.CaterersDAO;
import model.dao.MaterialCategoriesDAO;
import model.dao.MaterialsDAO;
import model.dao.SupplierChildInvoicesDAO;
import model.dao.SupplierInvoicesDAO;
import model.dao.SuppliersDAO;
import model.pojo.Accounts;
import model.pojo.MaterialCategories;
import model.pojo.Materials;
import model.pojo.SupplierChildInvoices;
import model.pojo.SupplierInvoices;
import model.pojo.Suppliers;
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
@RequestMapping(value = "/ordercaterer")
public class OrderCatererController {

    public int supplierIdPublic;
    public int supplierInvoiceIdPublic;
    public int catererIdPublic;
    public int categoryIdPPublic;
    CaterersDAO catererDAO = new CaterersDAO();
    MaterialCategoriesDAO materialCategoriesDAO = new MaterialCategoriesDAO();
    MaterialsDAO materialsDAO = new MaterialsDAO();
    SuppliersDAO supplierDAO = new SuppliersDAO();
    SupplierInvoicesDAO supplierInvoicesDAO = new SupplierInvoicesDAO();
    SupplierChildInvoicesDAO supplierChildInvoicesDAO = new SupplierChildInvoicesDAO();
    AccountsDAO accountDAO = new AccountsDAO();
    public List<SupplierChildInvoices> listInvoiceChildPublic = new ArrayList<>();
    public SupplierInvoices invoicePublic = new SupplierInvoices();
    public boolean checkInvoiceIsExist;

    @RequestMapping(value = "/listsuppliers", method = RequestMethod.GET)
    public String listSupplier(ModelMap modelMap) {
        List<Suppliers> listSuppliers = supplierDAO.getListSupplierToOrder();
        modelMap.put("suppliers", listSuppliers);
        return "caterer/supplierinvoice_showallsupplier";
    }

    @RequestMapping(value = "/setUserId.htm", method = RequestMethod.POST)
    public @ResponseBody
    String setUserId(HttpServletRequest request, HttpServletResponse response) {
        int accountId = Integer.parseInt(request.getParameter("catererId"));
        //catererIdPublic = catererId;        
        Accounts accountLogin = accountDAO.findAccount(accountId);
        if (accountLogin.getUserGroup().equals("caterer") || accountLogin.getUserGroup().equals("admin")) {
            if (!accountLogin.getUserGroup().equals("admin")) {
                catererIdPublic = catererDAO.findCatererByAccount(accountId).getId();
            }
            if (accountLogin.getUserGroup().equals("admin")) {
                catererIdPublic = accountLogin.getId();
            }
            return "success";
        } else {
            return "error";
        }

    }

    @RequestMapping(value = "/listmaterialcategories/{supplierId}", method = RequestMethod.GET)
    public String listMaterialCategoriesBySup(@PathVariable(value = "supplierId") int supplierId, HttpSession sessions, ModelMap modelMap) {
        //sessions.setAttribute("catererid", catererIdPublic);
        //sessions.setAttribute("suppid", supplierId);
        supplierIdPublic = supplierId;
        List<MaterialCategories> listCategories = materialCategoriesDAO.getCatergories(supplierId);
        List<SupplierInvoices> supplierInvoicesDB = supplierInvoicesDAO.findSupplierInvoiceByCaterer(catererIdPublic);
        modelMap.put("listinvoices", supplierInvoicesDB);
        modelMap.put("categories", listCategories);
        return "caterer/supplierinvoice_material";
    }//listmaterials

    @RequestMapping(value = "/listmaterials/{categoryId}", method = RequestMethod.GET)
    public String listMaterialByCategory(@PathVariable(value = "categoryId") int categoryId, HttpSession sessions, ModelMap modelMap) {
        List<Materials> listMaterials = materialsDAO.getAllMaterialsByCategoryId(categoryId);
        modelMap.put("materials", listMaterials);
        List<MaterialCategories> listCategories = materialCategoriesDAO.getCatergories(supplierIdPublic);
        modelMap.put("categories", listCategories);
        return "caterer/supplierinvoice_material";
    }

    @RequestMapping(value = "/addtobill.htm", method = RequestMethod.POST)
    public @ResponseBody
    String addToBill(HttpServletRequest request, HttpServletResponse response) {
        String valueAddToBill = request.getParameter("valueaddtobill");
        //value regex: amount;amount;...
        //int categoryId = Integer.parseInt(request.getParameter("categoryId"));
        String result = "";
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        Date today = Calendar.getInstance().getTime();
        String reportDate = df.format(today);
        String[] amount = valueAddToBill.split("-");
        int[] materialId = new int[amount.length];
        List<Materials> listMaterial = materialsDAO.getAllMaterialsByCategoryId(categoryIdPPublic);
        int count = 0;
        for (Materials listMaterialId : listMaterial) {
            materialId[count] = listMaterialId.getId();
            count++;
        }
        //get listInvoiceChildPublic from DB
        SupplierInvoices supplierInvoicesDB = supplierInvoicesDAO.checkInvoice(supplierIdPublic, catererIdPublic, reportDate);
        if (supplierInvoicesDB != null) {
            listInvoiceChildPublic = supplierChildInvoicesDAO.getAllInvoiceChildBySupplierInvoiceId(supplierInvoicesDB.getId());
        }

        if (listInvoiceChildPublic.isEmpty()) {
            SupplierInvoices supplierInvoicesExist = supplierInvoicesDAO.checkInvoice(supplierIdPublic, catererIdPublic, reportDate);

            if (supplierInvoicesExist != null) {
                supplierInvoiceIdPublic = supplierInvoicesExist.getId();
                invoicePublic = supplierInvoicesExist;
                //checkInvoiceIsExist = true;
                for (int i = 0; i < amount.length; i++) {
                    String amountStr = amount[i];
                    if (Integer.parseInt(amountStr) > 0) {
                        Materials material = materialsDAO.findMaterial(materialId[i]);
                        SupplierChildInvoices supplierChildInvoices = new SupplierChildInvoices();
                        supplierChildInvoices.setSupplierInvoices(supplierInvoicesExist);
                        supplierChildInvoices.setMaterialName(material.getMaterialName());
                        supplierChildInvoices.setQuantity(Integer.parseInt(amountStr));
                        supplierChildInvoices.setMaterialPricePerUnit(material.getMaterialPricePerUnit());
                        supplierChildInvoicesDAO.create(supplierChildInvoices, supplierInvoiceIdPublic);
                        listInvoiceChildPublic.add(supplierChildInvoices);
                    }

                }

            } else {
                SupplierInvoices supplierInvoices = new SupplierInvoices();
                supplierInvoices.setCaterers(catererDAO.findCaterer(catererIdPublic));
                supplierInvoices.setSuppliers(supplierDAO.findSupplier(supplierIdPublic));
                supplierInvoices.setInvoiceDate(reportDate);
                supplierInvoices.setStatus("waiting");
                supplierInvoicesDAO.create(supplierInvoices, catererIdPublic);
                supplierInvoiceIdPublic = supplierInvoices.getId();

                invoicePublic = supplierInvoices;
                //checkInvoiceIsExist = true;
                for (int i = 0; i < amount.length; i++) {
                    String amountStr = amount[i];
                    if (Integer.parseInt(amountStr) > 0) {
                        Materials material = materialsDAO.findMaterial(materialId[i]);
                        SupplierChildInvoices supplierChildInvoices = new SupplierChildInvoices();
                        supplierChildInvoices.setSupplierInvoices(supplierInvoices);
                        supplierChildInvoices.setMaterialName(material.getMaterialName());
                        supplierChildInvoices.setQuantity(Integer.parseInt(amountStr));
                        supplierChildInvoices.setMaterialPricePerUnit(material.getMaterialPricePerUnit());
                        supplierChildInvoicesDAO.create(supplierChildInvoices, supplierInvoiceIdPublic);
                        listInvoiceChildPublic.add(supplierChildInvoices);
                    }

                }
            }
        } else {
            SupplierInvoices supplierInvoicesExist = supplierInvoicesDAO.checkInvoice(supplierIdPublic, catererIdPublic, reportDate);

            if (supplierInvoicesExist != null) {
                supplierInvoiceIdPublic = supplierInvoicesExist.getId();
                invoicePublic = supplierInvoicesExist;
                //checkInvoiceIsExist = true;
                for (int i = 0; i < amount.length; i++) {
                    String amountStr = amount[i];
                    if (Integer.parseInt(amountStr) > 0) {
                        Materials material = materialsDAO.findMaterial(materialId[i]);
                        String idTest = "" + supplierInvoiceIdPublic;
                        SupplierChildInvoices supplierChildInvoices = supplierChildInvoicesDAO.findSupplierChildInvoiceBySupplierInvoiceIdAndMaterialName(idTest, material.getMaterialName());

                        if (supplierChildInvoices == null) {
                            //supplierChildInvoices.setSupplierInvoices(supplierInvoicesExist);
                            SupplierChildInvoices supplierChildInvoicesAdd = new SupplierChildInvoices();
                            String name = material.getMaterialName();
                            supplierChildInvoicesAdd.setMaterialName(name);
                            supplierChildInvoicesAdd.setQuantity(Integer.parseInt(amountStr));
                            supplierChildInvoicesAdd.setMaterialPricePerUnit(material.getMaterialPricePerUnit());
                            supplierChildInvoicesDAO.createHaveMaterialName(supplierChildInvoicesAdd, supplierInvoiceIdPublic, name);
                            listInvoiceChildPublic.add(supplierChildInvoicesAdd);
                        } else {
                            supplierChildInvoices.setQuantity(Integer.parseInt(amountStr));
                            supplierChildInvoicesDAO.update(supplierChildInvoices);
                            SupplierInvoices supplierInvoicesDBCheck = supplierInvoicesDAO.checkInvoice(supplierIdPublic, catererIdPublic, reportDate);
                            if (supplierInvoicesDBCheck != null) {
                                listInvoiceChildPublic = supplierChildInvoicesDAO.getAllInvoiceChildBySupplierInvoiceId(supplierInvoicesDBCheck.getId());
                            }
                            //for (SupplierChildInvoices childInvoiceRemove : listInvoiceChildPublic) {
                            //if (childInvoiceRemove.getId() == supplierChildInvoices.getId()) {
                            //listInvoiceChildPublic.remove(childInvoiceRemove);
                            //listInvoiceChildPublic.add(supplierChildInvoices);
                            //}
                            //}
                        }
                    }

                }

            } else {
                SupplierInvoices supplierInvoices = new SupplierInvoices();
                supplierInvoices.setCaterers(catererDAO.findCaterer(catererIdPublic));
                supplierInvoices.setSuppliers(supplierDAO.findSupplier(supplierIdPublic));
                supplierInvoices.setInvoiceDate(reportDate);
                supplierInvoices.setStatus("waiting");
                supplierInvoicesDAO.create(supplierInvoices, catererIdPublic);
                supplierInvoiceIdPublic = supplierInvoices.getId();

                invoicePublic = supplierInvoices;
                for (int i = 0; i < amount.length; i++) {
                    String amountStr = amount[i];
                    if (Integer.parseInt(amountStr) > 0) {
                        Materials material = materialsDAO.findMaterial(materialId[i]);
                        SupplierChildInvoices supplierChildInvoices = new SupplierChildInvoices();
                        supplierChildInvoices.setSupplierInvoices(supplierInvoices);
                        supplierChildInvoices.setMaterialName(material.getMaterialName());
                        supplierChildInvoices.setQuantity(Integer.parseInt(amountStr));
                        supplierChildInvoices.setMaterialPricePerUnit(material.getMaterialPricePerUnit());
                        supplierChildInvoicesDAO.create(supplierChildInvoices, supplierInvoiceIdPublic);
                        listInvoiceChildPublic.add(supplierChildInvoices);
                    }

                }
            }
        }

        result += "<h1>My Bill</h1>";
        result += "<table border='1' >"
                + "<tr>"
                + "<th> Material Name </th>"
                + "<th> Price/Unit</th>"
                + "<th>Quantity</th>"
                + "</tr>" + "";
        for (SupplierChildInvoices item : listInvoiceChildPublic) {
            result += "<tr>"
                    + "<td>" + item.getMaterialName() + "</td>"
                    + "<td>" + item.getMaterialPricePerUnit() + "</td>"
                    + "<td>" + item.getQuantity() + "</td></tr>";
        }
        result += "</table><br>";
        result += "<button onclick='chooseDeliveryDate();'>Completed</button>";
        return result;
    }

    @RequestMapping(value = "/listMyBill", method = RequestMethod.GET)
    public String listMyBill(ModelMap modelMap) {
        //List<SupplierInvoices> listSuppliersInvoiceses = supplierInvoicesDAO.findSupplierInvoiceByCaterer(catererIdPublic);
        //modelMap.put("suppliers", listSuppliers);
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        Date today = Calendar.getInstance().getTime();
        String reportDate = df.format(today);
        SupplierInvoices supplierInvoicesDB = supplierInvoicesDAO.checkInvoice(supplierIdPublic, catererIdPublic, reportDate);
        if (supplierInvoicesDB != null) {
            listInvoiceChildPublic = supplierChildInvoicesDAO.getAllInvoiceChildBySupplierInvoiceId(supplierInvoicesDB.getId());
        }
        modelMap.put("invoice", supplierInvoicesDB);
        modelMap.put("childinvoices", listInvoiceChildPublic);
        return "caterer/supplierinvoice_listbill";
    }

    @RequestMapping(value = "/listMyBill.htm", method = RequestMethod.POST)
    public String updateDeliveryDate(@ModelAttribute(value = "invoice") @Valid SupplierInvoices supplierInvoices, ModelMap modelMap, BindingResult bindingResult, HttpSession sessions) {
        String dateInString = supplierInvoices.getDeliveryDate();
        String[] dateArr = dateInString.split("-");
        String delivaryDate = dateArr[1] + "/" + dateArr[2] + "/" + dateArr[0];
        supplierInvoicesDAO.updateDeliveryDateForInvoice(supplierInvoices, delivaryDate);
        List<MaterialCategories> listCategories = materialCategoriesDAO.getCatergories(supplierIdPublic);
        List<SupplierInvoices> supplierInvoicesDB = supplierInvoicesDAO.findSupplierInvoiceByCaterer(catererIdPublic);
        sessions.setAttribute("listinvoices", supplierInvoicesDB);
        sessions.setAttribute("categories", listCategories);
        return "redirect:/ordercaterer/listmaterialcategories/" + supplierIdPublic + ".htm";
    }

    @RequestMapping(value = "/listmaterials.htm", method = RequestMethod.POST)
    public @ResponseBody
    String showListMaterials(HttpServletRequest request, HttpServletResponse response) {
        int categoryId = Integer.parseInt(request.getParameter("categoryId"));
        categoryIdPPublic = categoryId;
        List<Materials> listMaterials = materialsDAO.getAllMaterialsByCategoryId(categoryId);
        //String result = "<br>Next Random # is <b>" + r + "</b>. Generated on <b>" + new Date().toString() + "</b>";
        String result = "<table border='1' >"
                + "<tr>"
                + "<th> ID </th>"
                + "<th> Name </th>"
                + "<th><strong> Price/Unit</strong></th>"
                + "<th>Amount</th>"
                + "</tr>" + "";
        for (Materials material : listMaterials) {
            result += "<tr><td>" + material.getId() + "</td>"
                    + "<td>" + material.getMaterialName() + "</td>"
                    + "<td>" + material.getMaterialPricePerUnit() + "/" + material.getMaterialUnit() + "</td>"
                    + "<td>" + "<input class='amount' type='number' value='0' placeholder='Number of " + material.getMaterialUnit() + "' " + "</td></tr>";
        }
        result += "</table><br>";
        //result += "<input type='hidden' id='categoryId' value='" + categoryId + "'/>";
        //result += "<input type=\"button\" value=\"Add to bill\" onclick='addToBill();'/>";
        result += "<button onclick='addToBill();'>Add To Bill</button>";
        //result += "<button onclick='showMaterials("+categoryId+");'>Add To Bill</button>";
        return result;
    }
}
