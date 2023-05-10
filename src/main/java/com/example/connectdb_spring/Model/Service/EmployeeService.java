package com.example.connectdb_spring.Model.Service;

import com.example.connectdb_spring.Model.POJO.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    public List<Employee> findAll();

    public Employee findById();

    public boolean save();

    public void deleteById(int theId);

    public void edit();

}
