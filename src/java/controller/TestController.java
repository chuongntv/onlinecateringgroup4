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
import model.dao.TestDAO;
import model.dao.WorkerTypesDAO;
import model.pojo.WorkerTypes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author admin
 */
@Controller
@RequestMapping(value = "/test")
public class TestController {
    TestDAO testDAO = new TestDAO();
    public String catererIdPublic;
    @RequestMapping(value = "/listTypes", method = RequestMethod.GET)
    public String showAll(HttpSession sessions, ModelMap modelMap) {
        try {
            //List<MaterialCategories> listCategories = matcatDAO.getCatergories(supplierId);
            List<WorkerTypes> listTypes = testDAO.getTypes();
            List<WorkerTypes> listEncrypt = new ArrayList<WorkerTypes>();
            for (WorkerTypes item : listTypes) {
                WorkerTypes type = new WorkerTypes();
                type.setIdStr(EncryptAndDecrypt.encrypt(item.getId()));
                type.setWorkerTypeName(item.getWorkerTypeName());
                type.setPayPerDay(item.getPayPerDay());
                type.setId(item.getId());
                listEncrypt.add(type);
            }
            sessions.setAttribute("types", listEncrypt);
            modelMap.put("typeEdit", new WorkerTypes());
            modelMap.put("typeCreate", new WorkerTypes());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "test/showall";
    }
    
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable(value = "id") String id, ModelMap modelMap, HttpSession sessions) throws Exception {
        
        modelMap.put("typeEdit", testDAO.findType(Integer.parseInt(EncryptAndDecrypt.decrypt(id))));
        return "test/update";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String edit(@ModelAttribute(value = "typeEdit") @Valid WorkerTypes type, ModelMap modelMap, BindingResult bindingResult, HttpSession sessions) throws Exception {
        if (bindingResult.hasErrors()) {
            sessions.setAttribute("messageEdit", "Edit Type failed !");
            return "redirect:/manageworker/listTypes/" + catererIdPublic + ".htm";
        } else {
            if (!"".equals(type.getPayPerDay()) && !"".equals(type.getWorkerTypeName())) {
                testDAO.edit(type);
                sessions.setAttribute("messageEdit", "Success");
            } else {
                sessions.setAttribute("messageEdit", "Type Name or Pay Per Day not null");
            }
            
            return "redirect:/test/listTypes.htm";
        }
    }
    
    
    
}
