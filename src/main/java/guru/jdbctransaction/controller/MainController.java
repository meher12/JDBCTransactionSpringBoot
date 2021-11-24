package guru.jdbctransaction.controller;

import guru.jdbctransaction.dao.EmployeeSalaryDAO;
import guru.jdbctransaction.exception.EmployeeTransactionException;
import guru.jdbctransaction.form.SendMoneyForm;
import guru.jdbctransaction.model.EmployeeSalaryInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    private EmployeeSalaryDAO employeeSalaryDAO;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showEmployeeSalaryPage(Model model){
        List<EmployeeSalaryInfo> employeeSalaryInfos = employeeSalaryDAO.getEmployeeSalary();
        model.addAttribute("employeeinfos", employeeSalaryInfos);
        return "employeePage";
    }

    @RequestMapping(value = "/sendMoney", method = RequestMethod.GET)
    public String viewSendMoneyPage(Model model){
        SendMoneyForm form = new SendMoneyForm(1L, 2L, 700);
        model.addAttribute("sendMoneyForm", form);
        return "sendMoneyPage";
    }

    @RequestMapping(value = "/sendMoney", method = RequestMethod.POST)
    public String processSendMoney(Model model, SendMoneyForm moneyForm){

        System.out.println("Send Money:: " + moneyForm.getAmount());
        try {
            employeeSalaryDAO.sendMoney(moneyForm.getFromEmployeeId(), moneyForm.getToEmployeeId(), moneyForm.getAmount());
        } catch (EmployeeTransactionException e) {
            model.addAttribute("errorMessage", "Error: " + e.getMessage());
            return "sendMoneyPage";
        }
        return "redirect:/";
    }
}
