package com.example.connectdb_spring.Model.DAO;

import com.example.connectdb_spring.Model.POJO.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeDao extends JpaRepository<Employee, Integer> {

    public List<Employee> findAllByOrderByLastNameAsc();

    public Optional<Employee> findById(int id);

    public Employee deleteById(int id);

    public Employee save(Employee employee);


}
