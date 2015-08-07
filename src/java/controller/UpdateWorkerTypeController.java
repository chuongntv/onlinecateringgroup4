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
@RequestMapping(value = "/updateworkertype")
public class UpdateWorkerTypeController {
    
    WorkerTypesDAO typesDAO = new WorkerTypesDAO();
    
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable(value = "id") int id, ModelMap modelMap, HttpSession sessions) throws Exception {
        modelMap.put("typeEdit", typesDAO.findType(id));
        return "caterer/managementworkertypes_update";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String edit(@PathVariable(value = "catererId") int catererId,@ModelAttribute(value = "typeEdit") @Valid WorkerTypes type, ModelMap modelMap, BindingResult bindingResult, HttpSession sessions) throws Exception {
        if (bindingResult.hasErrors()) {
            sessions.setAttribute("messageEdit", "Edit Type failed !");
            return "redirect:/manageworker/listTypes/" + catererId + ".htm";
        } else {
            if (!"".equals(type.getPayPerDay()) && !"".equals(type.getWorkerTypeName())) {
                typesDAO.edit(type, catererId);
                sessions.setAttribute("messageEdit", "");
            } else {
                sessions.setAttribute("messageEdit", "Type Name or Pay Per Day not null");
            }            
            List<WorkerTypes> listTypes = typesDAO.getTypes(catererId);
            modelMap.put("types", listTypes);
            return "caterer/managementworkertypes_update";
        }
    }
}
