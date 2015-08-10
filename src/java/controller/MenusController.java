/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import model.dao.CategoriesDAO;
import model.dao.CatererDAO;
import model.dao.FoodsDAO;
import model.dao.MenuDao;
import model.dao.SubMenusDAO;
import model.pojo.Accounts;
import model.pojo.CategoryTypes;
import model.pojo.Caterers;
import model.pojo.Foods;
import model.pojo.Menus;
import model.pojo.SubMenus;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author MSI
 */
@Controller
@RequestMapping(value = "/menus")
public class MenusController {

    MenuDao menuDao = new MenuDao();
    SubMenusDAO subMenuDao = new SubMenusDAO();
    FoodsDAO foodsDAO = new FoodsDAO();
    CatererDAO CatererDAO = new CatererDAO();

    @RequestMapping(value = "/listmenus", method = RequestMethod.GET)
    public String listMenus(ModelMap modelMap, HttpSession session) {
        try {
            if (session.getAttribute("user") == null) {
                return "redirect:/account/login.htm";
            } else {
                Accounts acc = (Accounts) session.getAttribute("user");
                if (!acc.getUserGroup().equals("caterer")) {
                    return "redirect:/account/login.htm";
                }
            }
            Accounts user = (Accounts) session.getAttribute("user");
            Caterers caterer = CatererDAO.findCatererByAccount(user.getId());
            List<Menus> listMenu = menuDao.listMenuByCaterer(caterer.getId());
            modelMap.put("listmenu", listMenu);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "caterer/menus_list";
    }

    @RequestMapping(value = "/editimage/{id}", method = RequestMethod.GET)
    public String editImage(@PathVariable(value = "id") int id, ModelMap modelMap, HttpSession session) {
        try {
            Menus menu = menuDao.findMenu(id);
            modelMap.put("menu", menu);

        } catch (Exception ex) {

        }
        return "caterer/menus_editimage";
    }

    @RequestMapping(value = "/editimage", method = RequestMethod.POST)
    public String editImage(@ModelAttribute(value = "menu") Menus menu, @RequestParam(value = "file", required = false) MultipartFile file, ModelMap mm, BindingResult bindingResult, HttpSession session, HttpServletRequest request) {
        try {
            if (bindingResult.hasErrors()) {
                mm.put("message", "Update Astist failed !");
                //return "admin/editArtist";
            } else {
                if (file != null) {
                    try {
                        InputStream inputStream = file.getInputStream();
                        if (inputStream == null) {
                            System.out.println("File inputstream is null");
                        }
                        String path = request.getServletContext().getRealPath("upload/menuimage");

                        FileUtils.forceMkdir(new File(path));
                        String filename = file.getOriginalFilename();
                        String[] slitFileName = filename.split("[.]");
                        String type = "." + slitFileName[1];
                        File upload = new File(path + File.separator + menu.getId() + type);

                        // Luồng ghi dữ liệu vào file trên Server.
                        BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(upload));
                        stream.write(file.getBytes());

                        stream.close();

                        String imagePath = "/upload/menuimage/" + menu.getId() + type;
                        //         artist.setImage(imagePath);
                        menu.setImage(imagePath);

                        IOUtils.closeQuietly(inputStream);
                    } catch (IOException ex) {
                        System.out.println("Error saving uploaded file");
                        ex.printStackTrace();
                    }
                    menuDao.editImage(menu);
               // artistModel.edit(artist);

                    //    mm.put("message", "Edit Artist successful !");
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "redirect:../menus/listmenus.htm";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(ModelMap modelMap, HttpSession session) {
        try {
            if (session.getAttribute("user") == null) {
                return "redirect:/account/login.htm";
            } else {
                Accounts acc = (Accounts) session.getAttribute("user");
                if (!acc.getUserGroup().equals("caterer")) {
                    return "redirect:/account/login.htm";
                }
            }
            //  Accounts user = (Accounts) session.getAttribute("user");
            //  Caterers caterer = CatererDAO.findCatererByAccount(user.getId());
            CategoriesDAO categoriesDAO = new CategoriesDAO();
            List<CategoryTypes> listCategories = categoriesDAO.getCategoryTypes();
            session.setAttribute("categories", listCategories);

            Menus menu = new Menus();
            modelMap.put("menu", menu);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "caterer/menus_create";

    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@ModelAttribute(value = "menu") Menus menu, ModelMap modelMap, HttpSession session) {
        try {
            if (session.getAttribute("user") == null) {
                return "redirect:/account/login.htm";
            } else {
                Accounts acc = (Accounts) session.getAttribute("user");
                if (!acc.getUserGroup().equals("caterer")) {
                    return "redirect:/account/login.htm";
                }
            }
            if (menu.getMinPlate() >= menu.getMaxPlate()) {
                modelMap.put("minplateerror", "Min plate must be < max plate");
                return "caterer/menus_create";

            }
            if (menu.getMinPlate() <= 0) {
                modelMap.put("minplateerror", "Min plate must be >0");
                return "caterer/menus_create";
            }
            if (menu.getMaxPlate() <= 0) {
                modelMap.put("maxplateerror", "Max plate must be >0");
                return "caterer/menus_create";
            }

            Accounts user = (Accounts) session.getAttribute("user");
            Caterers caterer = CatererDAO.findCatererByAccount(user.getId());
            CategoriesDAO categoriesDAO = new CategoriesDAO();
            menu.setCaterers(caterer);
            menu.setImage("/upload/menuimage/default");

            menuDao.create(menu);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "redirect:menus/listmenus.htm";

    }

    @RequestMapping(value = "/editmenu/{id}", method = RequestMethod.GET)
    public String editMenu(@PathVariable(value = "id") int id, ModelMap modelMap, HttpSession session) {

        if (session.getAttribute("user") == null) {
            return "redirect:/account/login.htm";
        } else {
            Accounts acc = (Accounts) session.getAttribute("user");
            if (!acc.getUserGroup().equals("caterer")) {
                return "redirect:/account/login.htm";
            }
        }
        CategoriesDAO categoriesDAO = new CategoriesDAO();
        List<CategoryTypes> listCategories = categoriesDAO.getCategoryTypes();
        session.setAttribute("categories", listCategories);

        Menus menu = menuDao.findMenu(id);
        modelMap.put("menu", menu);

        return "caterer/menus_editmenu";
    }

    @RequestMapping(value = "/editmenu", method = RequestMethod.POST)
    public String editMenu(@ModelAttribute(value = "menu") Menus menu, ModelMap modelMap, HttpSession session, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            modelMap.put("message", "Update Astist failed !");
            return "caterer/menus_editmenu";
        }

        if (menu.getMinPlate() >= menu.getMaxPlate()) {
            modelMap.put("minplateerror", "Min plate must be < max plate");
            return "caterer/menus_editmenu";

        }
        if (menu.getMinPlate() <= 0) {
            modelMap.put("minplateerror", "Min plate must be >0");
            return "caterer/menus_editmenu";
        }
        if (menu.getMaxPlate() <= 0) {
            modelMap.put("maxplateerror", "Max plate must be >0");
            return "caterer/menus_editmenu";
        }
        Menus menuTemp = menuDao.findMenu(menu.getId());
        menuTemp.setMenuName(menu.getMenuName());
        menuTemp.setCategoryTypes(menu.getCategoryTypes());
        menuTemp.setCostPerPlate(menu.getCostPerPlate());
        menuTemp.setMaxPlate(menu.getMaxPlate());
        menuTemp.setMinPlate(menu.getMinPlate());
        menuDao.edit(menuTemp);

        return "redirect:../menus/listmenus.htm";
    }

    @RequestMapping(value = "/menudetail/{id}", method = RequestMethod.GET)
    public String menudetail(@PathVariable(value = "id") int id, ModelMap modelMap, HttpSession session) {

        try {
            //  idMenu = id;
            if (session.getAttribute("user") == null) {
                return "redirect:/account/login.htm";
            } else {
                Accounts acc = (Accounts) session.getAttribute("user");
                if (!acc.getUserGroup().equals("caterer")) {
                    return "redirect:/account/login.htm";
                }
            }
            // session.setAttribute("idmenu", id);
            FoodsDAO foodDao = new FoodsDAO();
            List<Foods> listFoods = foodDao.getFoodsByMenu(id);
            SubMenusDAO subMenuDao = new SubMenusDAO();
            List<SubMenus> listSubMenus = subMenuDao.getSubMenuByMenu(id);
            session.setAttribute("menuid", id);

            modelMap.put("menu", menuDao.findMenu(id));
            modelMap.put("listsubmenus", listSubMenus);
            modelMap.put("listfoods", listFoods);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "caterer/menus_menudetail";
    }

    @RequestMapping(value = "/createfood/{id}", method = RequestMethod.GET)
    public String createfood(@PathVariable(value = "id") int id, ModelMap modelMap, HttpSession session) {
        try {
            Foods foods = new Foods();
            modelMap.put("submenuid", id);
            modelMap.put("food", foods);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "caterer/menus_createfood";
    }

    @RequestMapping(value = "/createfood", method = RequestMethod.POST)
    public String createfood(@ModelAttribute(value = "food") Foods food, @RequestParam(value = "require") boolean require, ModelMap modelMap, HttpSession session) {
        try {
            if (require) {
                food.setIsRequire(1);
            } else {
                food.setIsRequire(0);
            }

            food.setImage("/upload/foodimage/default");

            foodsDAO.create(food);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        SubMenus subMenus = subMenuDao.findSubMenuById(food.getSubMenus().getId());
        return "redirect:../menus/menudetail/" + subMenus.getMenus().getId() + ".htm";
    }

    @RequestMapping(value = "/editfoodimage/{id}", method = RequestMethod.GET)
    public String editFoodImage(@PathVariable(value = "id") int id, ModelMap modelMap, HttpSession session) {
        Foods foods = new Foods();
        foods = foodsDAO.findFoodById(id);
        modelMap.put("food", foods);

        return "caterer/menus_editfoodimage";
    }

    @RequestMapping(value = "/editfoodimage", method = RequestMethod.POST)
    public String editFoodImage(@ModelAttribute(value = "food") Foods foods, @RequestParam(value = "file", required = false) MultipartFile file, ModelMap mm, BindingResult bindingResult, HttpSession session, HttpServletRequest request) {
        try {
            if (bindingResult.hasErrors()) {
                mm.put("message", "Update Astist failed !");
                //return "admin/editArtist";
            } else {
                if (file != null) {
                    try {
                        InputStream inputStream = file.getInputStream();
                        if (inputStream == null) {
                            System.out.println("File inputstream is null");
                        }
                        String path = request.getServletContext().getRealPath("upload/foodimage");

                        FileUtils.forceMkdir(new File(path));
                        String filename = file.getOriginalFilename();
                        String[] slitFileName = filename.split("[.]");
                        String type = "." + slitFileName[1];
                        File upload = new File(path + File.separator + foods.getId() + type);

                        // Luồng ghi dữ liệu vào file trên Server.
                        BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(upload));
                        stream.write(file.getBytes());

                        stream.close();

                        String imagePath = "/upload/foodimage/" + foods.getId() + type;
                        //         artist.setImage(imagePath);
                        foods.setImage(imagePath);

                        IOUtils.closeQuietly(inputStream);
                    } catch (IOException ex) {
                        System.out.println("Error saving uploaded file");
                        ex.printStackTrace();
                    }
                    // menuDao.editImage(menu);
                    // artistModel.edit(artist);
                    foodsDAO.editImage(foods);

                    //    mm.put("message", "Edit Artist successful !");
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (session.getAttribute("menuid") != null) {
            return "redirect:../menus/menudetail/" + session.getAttribute("menuid") + ".htm";
        } else {
            return "redirect:../menus/listmenus.htm";
        }

    }

    @RequestMapping(value = "/editfood/{id}", method = RequestMethod.GET)
    public String editFood(@PathVariable(value = "id") int id, ModelMap modelMap, HttpSession session) {
          if (session.getAttribute("user") == null) {
                return "redirect:/account/login.htm";
            } else {
                Accounts acc = (Accounts) session.getAttribute("user");
                if (!acc.getUserGroup().equals("caterer")) {
                    return "redirect:/account/login.htm";
                }
            }
        
        Foods food = foodsDAO.findFoodById(id);
        modelMap.put("food", food);

        return "caterer/menus_editfood";
    }

    @RequestMapping(value = "/editfood", method = RequestMethod.POST)
    public String editFood(@ModelAttribute(value = "food") Foods food, @RequestParam(value = "require") boolean require, ModelMap modelMap, HttpSession session) {
        try {
            if (require) {
                food.setIsRequire(1);
            } else {
                food.setIsRequire(0);
            }

            Foods foodTemp = foodsDAO.findFoodById(food.getId());
            foodTemp.setFoodName(food.getFoodName());
            foodTemp.setIsRequire(food.getIsRequire());
            foodsDAO.edit(foodTemp);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        //SubMenus subMenus = subMenuDao.findSubMenuById(food.getSubMenus().getId());
        return "redirect:../menus/menudetail/" + session.getAttribute("menuid") + ".htm";
    }

    @RequestMapping(value = "/deletefood/{id}", method = RequestMethod.GET)
    public String deleteFood(@PathVariable(value = "id") int id, ModelMap modelMap, HttpSession session) {
          if (session.getAttribute("user") == null) {
                return "redirect:/account/login.htm";
            } else {
                Accounts acc = (Accounts) session.getAttribute("user");
                if (!acc.getUserGroup().equals("caterer")) {
                    return "redirect:/account/login.htm";
                }
            }
        
        Foods food = foodsDAO.findFoodById(id);
        foodsDAO.delete(food);
        // modelMap.put("message", "Delete success");

        return "redirect:../../menus/menudetail/" + session.getAttribute("menuid") + ".htm";

    }

    @RequestMapping(value = "/createsubmenu", method = RequestMethod.GET)
    public String createSubmenu(ModelMap modelMap, HttpSession session) {
        try {
            
              if (session.getAttribute("user") == null) {
                return "redirect:/account/login.htm";
            } else {
                Accounts acc = (Accounts) session.getAttribute("user");
                if (!acc.getUserGroup().equals("caterer")) {
                    return "redirect:/account/login.htm";
                }
            }
            SubMenus subMenus = new SubMenus();
            Menus menu = menuDao.findMenu((int) session.getAttribute("menuid"));

            subMenus.setMenus(menu);
            modelMap.put("submenu", subMenus);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "caterer/menus_createsubmenu";
    }

    @RequestMapping(value = "/createsubmenu", method = RequestMethod.POST)
    public String createSubmenu(@ModelAttribute(value = "submenu") SubMenus submenu, ModelMap modelMap, HttpSession session, BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()) {
                modelMap.put("message", "Create failed !");
                return "countries_create";
            } else {
                subMenuDao.create(submenu);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return "redirect:../menus/menudetail/" + submenu.getMenus().getId() + ".htm";
    }

    @RequestMapping(value = "/editsubmenu/{id}", method = RequestMethod.GET)
    public String editSubMenu(@PathVariable(value = "id") int id, ModelMap modelMap, HttpSession session) {
        
          if (session.getAttribute("user") == null) {
                return "redirect:/account/login.htm";
            } else {
                Accounts acc = (Accounts) session.getAttribute("user");
                if (!acc.getUserGroup().equals("caterer")) {
                    return "redirect:/account/login.htm";
                }
            }

        SubMenus subMenus = new SubMenus();
        // subMenus.setId(id);
        subMenus = subMenuDao.findSubMenuById(id);
        modelMap.put("submenu", subMenus);

        return "caterer/menus_editsubmenu";
    }

    @RequestMapping(value = "/editsubmenu", method = RequestMethod.POST)
    public String editSubMenu(@ModelAttribute(value = "submenu") SubMenus submenu, ModelMap modelMap, HttpSession session, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            modelMap.put("message", "Update Astist failed !");
            return "careter/menus_editsubmenu";
        }
        SubMenus subMenuTemp = subMenuDao.findSubMenuById(submenu.getId());
        subMenuTemp.setSubMenuName(submenu.getSubMenuName());
        subMenuTemp.setNumberOfFood(submenu.getNumberOfFood());
        subMenuDao.edit(subMenuTemp);
        return "redirect:../menus/menudetail/" + subMenuTemp.getMenus().getId() + ".htm";
    }

    @RequestMapping(value = "/deletesubmenu/{id}", method = RequestMethod.GET)
    public String deleteSubmenu(@PathVariable(value = "id") int id, ModelMap modelMap, HttpSession session) {
          if (session.getAttribute("user") == null) {
                return "redirect:/account/login.htm";
            } else {
                Accounts acc = (Accounts) session.getAttribute("user");
                if (!acc.getUserGroup().equals("caterer")) {
                    return "redirect:/account/login.htm";
                }
            }
        
        List<Foods> listFoods = foodsDAO.listFoodsBySubMenu(id);
        SubMenus subMenus = subMenuDao.findSubMenuById(id);
        if (listFoods.isEmpty()) {
            
            subMenuDao.delete(subMenus);
          //  modelMap.put("message", "Sub menu " + subMenus.getSubMenuName() + " is success deleted");
            return "redirect:../../menus/menudetail/" + session.getAttribute("menuid") + ".htm";
        } else {
            int number = listFoods.size();
            modelMap.put("submenu",subMenus);
            modelMap.put("listfoods", listFoods);
            modelMap.put("number", number);
            return "caterer/menus_resultsubmenudelete";
            
        }

       
    }
}
