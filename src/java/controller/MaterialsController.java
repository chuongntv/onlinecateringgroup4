/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import model.dao.MaterialsDAO;
import model.pojo.Materials;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author admin
 */
@Controller
@RequestMapping("/materials")
public class MaterialsController {
    
    MaterialsDAO materialDAO = new MaterialsDAO();
    public int categoryIdPublic;
    
    @RequestMapping(value = "/listmaterials/{categoryId}", method = RequestMethod.GET)
    public String showAllByCatergoryId(@PathVariable(value = "categoryId") int categoryId,HttpSession sessions, ModelMap modelMap) {
        try {
            categoryIdPublic = categoryId;
            List<Materials> listMaterials = materialDAO.getAllMaterialsByCategoryId(categoryIdPublic);
            sessions.setAttribute("materials", listMaterials);
            modelMap.put("materialCreate", new Materials());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "supplier/materials_showall";
    }
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String creteMaterial( ModelMap modelMap,HttpSession sessions){        
            modelMap.put("materialCreate", new Materials());
        return "supplier/creatematerial";
    }
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@ModelAttribute(value = "materialCreate") Materials material, ModelMap modelMap, BindingResult bindingResult, HttpSession sessions) throws Exception {
        if (bindingResult.hasErrors()) {
            modelMap.put("message", "Create failed !");
            return "supplier/materialcategories_showall";
        } else {
            if (!"".equals(material.getMaterialName())) {
                materialDAO.create(material,categoryIdPublic);
                sessions.setAttribute("messageCreate", "");
            } else {
                sessions.setAttribute("messageCreate", "Material Name not null");
            }
//            List<Materials> listMaterials = materialDAO.getAllMaterials();
//            sessions.setAttribute("materials", listMaterials);
            return "redirect:/materials/listmaterials/"+categoryIdPublic+".htm";
        }
    }
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable(value = "id") int id, ModelMap modelMap, HttpSession sessions) {
//        List<Materials> listMaterials = materialDAO.getAllMaterials();
//        sessions.setAttribute("materials", listMaterials);
        modelMap.put("materialEdit", materialDAO.findMaterial(id));
        return "supplier/editmaterial";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String edit(@ModelAttribute(value = "materialEdit") @Valid Materials material, ModelMap modelMap, BindingResult bindingResult, HttpSession sessions){
        if (bindingResult.hasErrors()) {
            sessions.setAttribute("messageEdit", "Edit country failed !");
            return "supplier/materials_showall";
        } else {
            if (!"".equals(material.getMaterialName())) {
                try {
                    materialDAO.edit(material,categoryIdPublic);
                } catch (Exception ex) {
                    Logger.getLogger(MaterialsController.class.getName()).log(Level.SEVERE, null, ex);
                }
                sessions.setAttribute("messageEdit", "");
            } else {
                sessions.setAttribute("messageEdit", "Material Name not null");
            }
            List<Materials> listMaterials = materialDAO.getAllMaterials();
            sessions.setAttribute("categories", listMaterials);
            return "redirect:/materials/listmaterials/"+categoryIdPublic+".htm";
        }
    }
    
    @RequestMapping(value = "/cancel", method = RequestMethod.POST)
    public String cancelEdit(HttpSession sessions, ModelMap modelMap) {        
        List<Materials> listMaterials = materialDAO.getAllMaterials();
        sessions.setAttribute("materials", listMaterials);
        return "redirect:/materials/listmaterials/"+categoryIdPublic+".htm";
    }
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable(value = "id") int id, ModelMap modelMap, HttpSession sessions) {
        materialDAO.delete(materialDAO.findMaterial(id));
        List<Materials> listMaterials = materialDAO.getAllMaterials();
        sessions.setAttribute("materials", listMaterials);
        return "redirect:/materials/listmaterials/"+categoryIdPublic+".htm";
    }
}
