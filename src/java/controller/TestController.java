/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author admin
 */
@Controller
@RequestMapping(value = "/test")
public class TestController {
    @RequestMapping(value = "/testmapping", method = RequestMethod.GET)
    public String test(ModelMap mm){
        mm.put("title", "TEST cmn");
        return "gototestpage";
    }
    
}
