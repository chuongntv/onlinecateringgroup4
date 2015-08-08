package controller;

import org.springframework.stereotype.*;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "mytemplate")
public class MyTemplateController {

    @RequestMapping(method = RequestMethod.GET)
    public String index(ModelMap mm) {
        mm.put("title", "Home");
        return "index";
    }

    @RequestMapping(value = "/aboutus", method = RequestMethod.GET)
    public String aboutus(ModelMap mm) {
        mm.put("title", "About Us");
        return "aboutus";
    }

    @RequestMapping(value = "/news", method = RequestMethod.GET)
    public String news(ModelMap mm) {
        mm.put("title", "News");
        return "news";
    }

}
