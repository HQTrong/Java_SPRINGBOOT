package com.example.connectdb_spring.Model.DAO;

import com.example.connectdb_spring.Model.POJO.Account;
import com.example.connectdb_spring.Model.POJO.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountDao extends JpaRepository<Account, Integer> {
    public Account findAccountByUserAndPass(String user, String pass);

}
