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
import model.dao.WorkerSalariesDAO;
import model.dao.WorkerTypesDAO;
import model.dao.WorkersDAO;
import model.pojo.Accounts;
import model.pojo.Caterers;
import model.pojo.WorkerSalaries;
import model.pojo.WorkerTypes;
import model.pojo.Workers;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author admin
 *
 */
@Controller
@RequestMapping(value = "/manageworker")
public class ManageWorkerController {

    public int catererIdPublic;
    public int typeIdPublic;
    WorkerTypesDAO typesDAO = new WorkerTypesDAO();
    WorkersDAO workerDAO = new WorkersDAO();
    WorkerSalariesDAO workerSalariesDAO = new WorkerSalariesDAO();
    AccountsDAO accountDAO = new AccountsDAO();
    CaterersDAO catererDAO = new CaterersDAO();

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

    @RequestMapping(value = "/listTypes/{catererId}", method = RequestMethod.GET)
    public String showAll(@PathVariable(value = "catererId") int catererId, HttpSession sessions, ModelMap modelMap) {

        Accounts accountLogin = accountDAO.findAccount(catererId);
        if (accountLogin.getUserGroup().equals("caterer") || accountLogin.getUserGroup().equals("admin")) {
            if (!accountLogin.getUserGroup().equals("admin")) {
                Caterers catererCheck = catererDAO.findCatererByAccount(catererId);
                catererIdPublic = catererCheck.getId();
            }

            try {
                //List<MaterialCategories> listCategories = matcatDAO.getCatergories(supplierId);
                List<WorkerTypes> listTypes = typesDAO.getTypes(catererId);
                catererIdPublic = catererId;
                sessions.setAttribute("types", listTypes);
                modelMap.put("typeEdit", new WorkerTypes());
                modelMap.put("typeCreate", new WorkerTypes());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return "caterer/managementworkertypes_showall";
        } else {
            return "redirect:/account/login.htm";
        }

    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@ModelAttribute(value = "typeCreate") WorkerTypes typeCreate, ModelMap modelMap, BindingResult bindingResult, HttpSession sessions) throws Exception {
        if (bindingResult.hasErrors()) {
            modelMap.put("message", "Create failed !");
            return "manageworker/managementworkertype_showall";
        } else {
            if (!"".equals(typeCreate.getWorkerTypeName()) && !"".equals(typeCreate.getPayPerDay())) {
                typesDAO.create(typeCreate, catererIdPublic);
                sessions.setAttribute("messageCreate", "");
            } else {
                sessions.setAttribute("messageCreate", "Type Name or PayPerDay not null");
            }
            List<WorkerTypes> listTypes = typesDAO.getTypes(catererIdPublic);
            modelMap.put("types", listTypes);
            sessions.setAttribute("messageCreate", "");
            return "redirect:/manageworker/listTypes/" + catererIdPublic + ".htm";
        }
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable(value = "id") int id, ModelMap modelMap, HttpSession sessions) throws Exception {

        modelMap.put("typeEdit", typesDAO.findType(id));
        return "caterer/managementworkertypes_showall";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String edit(@ModelAttribute(value = "typeEdit") @Valid WorkerTypes type, ModelMap modelMap, BindingResult bindingResult, HttpSession sessions) throws Exception {
        if (bindingResult.hasErrors()) {
            sessions.setAttribute("messageEdit", "Edit Type failed !");
            return "redirect:/manageworker/listTypes/" + catererIdPublic + ".htm";
        } else {
            if (!"".equals(type.getPayPerDay()) && !"".equals(type.getWorkerTypeName())) {
                typesDAO.edit(type, catererIdPublic);
                sessions.setAttribute("messageEdit", "");
            } else {
                sessions.setAttribute("messageEdit", "Type Name or Pay Per Day not null");
            }
            List<WorkerTypes> listTypes = typesDAO.getTypes(catererIdPublic);
            List<WorkerTypes> listEncrypt = new ArrayList<WorkerTypes>();
            for (WorkerTypes item : listTypes) {
                WorkerTypes wtype = new WorkerTypes();
                wtype.setWorkerTypeName(item.getWorkerTypeName());
                wtype.setId(item.getId());
                listEncrypt.add(wtype);
            }
            modelMap.put("types", listEncrypt);
            return "redirect:/manageworker/listTypes/" + catererIdPublic + ".htm";
        }
    }

    @RequestMapping(value = "/cancel", method = RequestMethod.POST)
    public String cancelEdit(HttpSession sessions, ModelMap modelMap) {
        return "redirect:/manageworker/listTypes/" + catererIdPublic + ".htm";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable(value = "id") int id, ModelMap modelMap, HttpSession sessions) {
        typesDAO.delete(typesDAO.findType(id));
        List<WorkerTypes> listTypes = typesDAO.getTypes(catererIdPublic);
        sessions.setAttribute("types", listTypes);
        return "redirect:/manageworker/listTypes/" + catererIdPublic + ".htm";
    }

    @RequestMapping(value = "/timekeeping", method = RequestMethod.POST)
    public String timeKeeping(@RequestParam(value = "idString") String idStr, ModelMap modelMap, HttpSession sessions) throws Exception {

        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        Date today = Calendar.getInstance().getTime();
        String reportDate = df.format(today);
        modelMap.put("toDay", reportDate);
        return "/caterer/managementworkers_showall";
    }

    //Worker Management
    @RequestMapping(value = "/listworkers/{typeId}", method = RequestMethod.GET)
    public String listWorkers(@PathVariable(value = "typeId") int typeId, ModelMap modelMap, HttpSession sessions) throws Exception {
        List<Workers> list = workerDAO.getWorkers(typeId);
        typeIdPublic = typeId;
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        Date today = Calendar.getInstance().getTime();
        String reportDate = df.format(today);
        modelMap.put("toDay", reportDate);
        modelMap.put("workers", list);
        modelMap.put("workerCreate", new Workers());
        return "caterer/timekeeping";
    }

    @RequestMapping(value = "/blockworker/{idworker}", method = RequestMethod.GET)
    public String blockWorker(@PathVariable(value = "idworker") int idWorker, HttpSession sessions) {
        Workers workerBlock = workerDAO.findWorker(idWorker);
        workerBlock.setStatus(1);
        workerDAO.edit(workerBlock);
        return "redirect:/manageworker/listworkers/" + typeIdPublic + ".htm";
    }

    @RequestMapping(value = "/createworker", method = RequestMethod.GET)
    public String createWorker(ModelMap modelMap) {
        modelMap.put("workerCreate", new Workers());
        return "caterer/createworker";
    }

    @RequestMapping(value = "/createworker", method = RequestMethod.POST)
    public String createWorker(@ModelAttribute(value = "workerCreate") Workers workerCreate, ModelMap modelMap, BindingResult bindingResult, HttpSession sessions) throws Exception {
        if (bindingResult.hasErrors()) {
            modelMap.put("message", "Create failed !");
            return "manageworker/managementworkertype_showall";
        } else {
            if (!"".equals(workerCreate.getWorkerName())) {
                DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
                Date today = Calendar.getInstance().getTime();
                String reportDate = df.format(today);
                workerCreate.setWorkerDateOfJoin(reportDate);
                workerCreate.setStatus(0);
                workerDAO.create(workerCreate, typeIdPublic);
                sessions.setAttribute("messageCreate", "");
            } else {
                sessions.setAttribute("messageCreate", "Type Name or PayPerDay not null");
            }
            modelMap.put("workerCreate", new Workers());
//            modelMap.put("workerEdit", new Workers());
            sessions.setAttribute("messageCreate", "");
            return "redirect:/manageworker/listworkers/" + typeIdPublic + ".htm";
        }
    }

    @RequestMapping(value = "/addDate.htm", method = RequestMethod.POST)
    public @ResponseBody
    String addDateForWoker(HttpServletRequest request, HttpServletResponse response) {
        WorkerSalaries workerSalaries = new WorkerSalaries();
        int idWorker = Integer.parseInt(request.getParameter("workerId"));
        workerSalaries = workerSalariesDAO.findWorkerToPayment(idWorker);
        if (workerSalaries != null) {
            workerSalaries.setWorkingDay(workerSalaries.getWorkingDay() + 1);
            workerSalariesDAO.edit(workerSalaries);
        } else {
            workerSalariesDAO.create(workerSalaries, idWorker);
        }
        return "<strong>Success</strong>";
    }

    @RequestMapping(value = "/payment/{id}", method = RequestMethod.GET)
    public String paymentForWorker(@PathVariable(value = "id") int idWorker, ModelMap modelMap) {
        modelMap.put("paymentworker", workerSalariesDAO.findWorkerToPayment(idWorker));
        Workers worker = workerDAO.findWorker(idWorker);
        if (worker != null) {
            modelMap.put("workerNameSalary", worker.getWorkerName());
            modelMap.put("workerPhoneNumberSalary", worker.getWorkerPhoneNumber());
            modelMap.put("workerEmailSalary", worker.getWorkerEmail());
            modelMap.put("workingDay", workerSalariesDAO.findWorkerToPayment(idWorker).getWorkingDay());
        }
        return "caterer/payment";
    }

    @RequestMapping(value = "/payment", method = RequestMethod.POST)
    public String paymentForWorker(@ModelAttribute(value = "paymentworker") @Valid WorkerSalaries workerSalariesUpdate, ModelMap modelMap, BindingResult bindingResult, HttpSession sessions) throws Exception {
        if (bindingResult.hasErrors()) {
            sessions.setAttribute("messagePaymentNoti", "Worker's not Payment");
            return "redirect:/manageworker/listworkers/" + typeIdPublic + ".htm";
        } else {
            if (!"".equals(workerSalariesUpdate.getPayType())) {
                WorkerSalaries workerSalary = new WorkerSalaries();
                workerSalary = workerSalariesDAO.findSalary(workerSalariesUpdate.getId());
                int workingDay = workerSalary.getWorkingDay();
                String typePayment = workerSalariesUpdate.getPayType();
                try {
                    if (typePayment.equals("week")) {
                        if (workingDay >= 6) {
                            workerSalary.setWorkingDay(workerSalary.getWorkingDay() - 6);
                            workerSalary.setPayType("week");
                            workerSalariesDAO.edit(workerSalary);
                            sessions.setAttribute("messagePaymentNoti", "" + workerDAO.findWorker(workerSalary.getWorkers().getId()).getWorkerName() + " had been  payment a week");
                        } else {
                            modelMap.put("messagePayment", "Working Day must be larger than 6 days");
                        }
                    }
                    if (typePayment.equals("month")) {
                        if (workingDay >= (6 * 4)) {
                            workerSalary.setWorkingDay(workerSalary.getWorkingDay() - (6 * 4));
                            workerSalary.setPayType("month");
                            workerSalariesDAO.edit(workerSalary);
                            sessions.setAttribute("messagePaymentNoti", "Worker had been  payment a month");
                        } else {
                            sessions.setAttribute("messagePayment", "Working Day must be larger than 24 days");
                        }
                    }
                    if (typePayment.equals("quarter")) {
                        if (workingDay >= (72)) {
                            workerSalary.setWorkingDay(workerSalary.getWorkingDay() - (72));
                            workerSalary.setPayType("week");
                            workerSalariesDAO.edit(workerSalary);
                            sessions.setAttribute("messagePaymentNoti", "Worker had been  payment a quarter");
                        } else {
                            sessions.setAttribute("messagePayment", "Working Day must be larger than all days working of a quarter (72 days)");
                        }
                    }
                    if (typePayment.equals("year")) {
                        if (workingDay >= (280)) {
                            workerSalary.setWorkingDay(workerSalary.getWorkingDay() - (280));
                            workerSalary.setPayType("week");
                            workerSalariesDAO.edit(workerSalary);
                            sessions.setAttribute("messagePaymentNoti", "" + workerDAO.findWorker(workerSalary.getWorkers().getId()).getWorkerName() + " had been  payment a year");
                        } else {
                            sessions.setAttribute("messagePayment", "Working Day must be larger than all days working of a year (280 days)");
                        }
                    }
                } catch (Exception e) {
                    sessions.setAttribute("messagePaymentNoti", "Worker's not Payment");
                }

            } else {
                sessions.setAttribute("messagePaymentNoti", "Worker's not Payment");
            }

            DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
            Date today = Calendar.getInstance().getTime();
            String reportDate = df.format(today);
            sessions.setAttribute("toDay", reportDate);
            return "redirect:/manageworker/listworkers/" + typeIdPublic + ".htm";
        }
    }

    @RequestMapping(value = "/editworker/{id}", method = RequestMethod.GET)
    public String editWorker(@PathVariable(value = "id") int id, ModelMap modelMap, HttpSession sessions) throws Exception {

        modelMap.put("workerEdit", workerDAO.findWorker(id));
        return "caterer/editworker";
    }

    @RequestMapping(value = "/editworker", method = RequestMethod.POST)
    public String editWorker(@ModelAttribute(value = "workerEdit")
            @Valid Workers workerUpdate, ModelMap modelMap, BindingResult bindingResult, HttpSession sessions) throws Exception {
        if (bindingResult.hasErrors()) {
            sessions.setAttribute("messageEdit", "Edit Type failed !");
            return "redirect:/manageworker/listTypes/" + catererIdPublic + ".htm";
        } else {
            if (!"".equals(workerUpdate.getWorkerEmail()) && !"".equals(workerUpdate.getWorkerName())) {
                workerDAO.edit(workerUpdate);
                sessions.setAttribute("messageEdit", "");
            } else {
                sessions.setAttribute("messageEdit", "Type Name or Pay Per Day not null");
            }
            List<Workers> list = workerDAO.getWorkers(typeIdPublic);

            modelMap.put("workers", list);
            return "redirect:/manageworker/listworkers/" + typeIdPublic + ".htm";
        }
    }

    @RequestMapping(value = "/cancelworker", method = RequestMethod.POST)
    public String cancelEditWorker(HttpSession sessions, ModelMap modelMap) throws Exception {

        return "redirect:/manageworker/listworkers/" + typeIdPublic + ".htm";
    }

    @RequestMapping(value = "/deleteworker/{id}", method = RequestMethod.GET)
    public String deleteWorker(@PathVariable(value = "id") String id, ModelMap modelMap, HttpSession sessions) throws Exception {
        workerDAO.delete(workerDAO.findWorker(Integer.parseInt(EncryptAndDecrypt.decrypt(id))));

        return "redirect:/manageworker/listworkers/" + typeIdPublic + ".htm";
    }
}
