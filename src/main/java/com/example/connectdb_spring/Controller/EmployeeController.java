package com.example.connectdb_spring.Controller;

import com.example.connectdb_spring.Model.POJO.Account;
import com.example.connectdb_spring.Model.POJO.Employee;
import com.example.connectdb_spring.Model.Service.AccountService;
import com.example.connectdb_spring.Model.Service.EmployeeService;

import jakarta.servlet.ServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class EmployeeController {
    private final ServletRequest request;
    private EmployeeService employeeService;
    private AccountService accountService;

    @Autowired
    public EmployeeController(EmployeeService employeeService, AccountService accountService, @Qualifier("httpServletRequest") ServletRequest request) {
        this.employeeService = employeeService;
        this.accountService = accountService;
        this.request = request;

    }

    @RequestMapping(value = "/listAll", method = RequestMethod.GET)
    public String getEmployeeAll(Model model) {

        List<Employee> theEmployees = employeeService.findAll();
        model.addAttribute("list_employees", theEmployees);
        return "index";
    }

    @RequestMapping(value = "/listId", method = RequestMethod.GET)
    public String getEmployeeId(Model model) {
        Employee employees = employeeService.findById();
        if (employees != null) {
            model.addAttribute("employees", employees);

        } else {
            String status = "Vui lòng nhập ID";
            model.addAttribute("status", status);
        }

        return "index";

    }

    @RequestMapping(value = "/addEmployee", method = RequestMethod.POST)
    public String addUser(Model model) {
        boolean result = employeeService.save();
        if (result == true) {
            List<Employee> theEmployees = employeeService.findAll();
            model.addAttribute("list_employees", theEmployees);
        } else {
            String status = "Thêm nhân viên không thành công. Vui lòng kiểm tra lại!!";
            model.addAttribute("status", status);
        }
        return "index";
    }

    @RequestMapping(value = "/add")
    public String add() {
        return "add";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String deleteUser(@RequestParam(value = "id") int id, Model model) {
        employeeService.deleteById(id);
        List<Employee> theEmployees = employeeService.findAll();
        model.addAttribute("list_employees", theEmployees);
        return "index";
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String update(Model model) {
        Employee employee = employeeService.findById();
        model.addAttribute("employees", employee);
        return "edit";
    }

    @RequestMapping(value = "/updateEmployee", method = RequestMethod.POST)
    public String updateEmployee(Model model) {
        employeeService.edit();
        List<Employee> theEmployees = employeeService.findAll();
        model.addAttribute("list_employees", theEmployees);
        return "index";
    }

    @RequestMapping(value = "/login")
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/loginAccount", method = RequestMethod.GET)
    public String loginAccount(Model model) {
        Account account = accountService.findAccountByUserAndPass();
        if (account != null) {
            return "index";
        } else {
            String status = "Không thể đăng nhập. Vui lòng kiểm tra lại!!";
            model.addAttribute("status", status);
            return "login";
        }

    }

}

