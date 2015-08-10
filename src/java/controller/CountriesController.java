/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.List;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import model.dao.CountriesDAO;
import model.pojo.Countries;
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
@RequestMapping(value = "/country")
public class CountriesController {
    CountriesDAO countryDAO = new CountriesDAO();    

    @RequestMapping(value = "/listCountries",method = RequestMethod.GET)
    public String showAll(HttpSession sessions) {
        try {
            List<Countries> listCountries = countryDAO.getCountries();
            sessions.setAttribute("countries", listCountries);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "countries_showall";
    }
    
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(ModelMap modelMap){
        modelMap.put("country", new Countries());
        return "countries_create";
    }
    
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@ModelAttribute(value = "country")Countries country, ModelMap modelMap, BindingResult bindingResult, HttpSession sessions) {
        if (bindingResult.hasErrors()) {
            modelMap.put("message", "Create failed !");
            return "countries_create";
        } else {
             
            country.setCitieses(null);
            country.setSupplierses(null);
            countryDAO.create(country);
            modelMap.put("message", "Create success !");
            List<Countries> listCountries = countryDAO.getCountries();
            sessions.setAttribute("countries", listCountries);
            return "countries_showall";
        }        
    }
    
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable(value = "id") int id,ModelMap modelMap){
        modelMap.put("country",  countryDAO.findCountry(id));
        return "countries_edit";
    }
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String edit(@ModelAttribute(value = "country") @Valid Countries country, ModelMap modelMap, BindingResult bindingResult, HttpSession sessions) {
        if (bindingResult.hasErrors()) {
            modelMap.put("message", "Edit country failed !");
            return "countries_edit";
        } else {
            countryDAO.edit(country);
            modelMap.put("countries", countryDAO.getCountries());
            return "countries_showall";
        }
    }
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable(value = "id") int id, ModelMap modelMap) {

        countryDAO.delete(countryDAO.findCountry(id));

        modelMap.put("countries", countryDAO.getCountries());
        return "countries_showall";
    }
}
