package com.example.connectdb_spring.Model.Service;

import com.example.connectdb_spring.Model.DAO.EmployeeDao;
import com.example.connectdb_spring.Model.POJO.Employee;
import jakarta.servlet.ServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceEmployeeImpl implements EmployeeService {
    private EmployeeDao employeeDao;
    private final ServletRequest request;

    @Autowired
    public ServiceEmployeeImpl(EmployeeDao theEmployeeDao, ServletRequest request) {
        employeeDao = theEmployeeDao;
        this.request = request;
    }

    @Override
    public List<Employee> findAll() {
        return employeeDao.findAllByOrderByLastNameAsc();
    }

    @Override
    public Employee findById() {
        String id = request.getParameter("id");
        boolean result = true;
        if (id != "") {
            Optional<Employee> employee = employeeDao.findById(Integer.parseInt(id));
            Employee theEmployee = null;
            if (employee.isPresent()) {
                theEmployee = employee.get();
            }
            return theEmployee;
        }
        return null;
    }

    @Override
    public boolean save() {
        String id = request.getParameter("id");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        if (id != "" && firstName != "" && lastName != "" && email != "") {
            Optional<Employee> employee1 = employeeDao.findById(Integer.parseInt(id));
            if (employee1.isEmpty()) {
                Employee employee = new Employee(Integer.parseInt(id), firstName, lastName, email);
                employeeDao.save(employee);
                return true;
            }
        }
        return false;
    }

    @Override
    public void deleteById(int theId) {
        employeeDao.deleteById(theId);
    }

    @Override
    public void edit() {
        String id = request.getParameter("id");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        Employee employee = new Employee(Integer.parseInt(id), firstName, lastName, email);
        employeeDao.save(employee);
    }
}
