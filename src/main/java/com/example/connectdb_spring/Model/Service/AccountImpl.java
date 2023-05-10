package com.example.connectdb_spring.Model.Service;

import com.example.connectdb_spring.Model.DAO.AccountDao;
import com.example.connectdb_spring.Model.POJO.Account;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.ServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountImpl implements AccountService {

    private final ServletRequest request;

    private AccountDao accountDao;

    @Autowired
    private AccountImpl(ServletRequest request, AccountDao accountDao) {
        this.request = request;
        this.accountDao = accountDao;
    }

    @Override
    public Account findAccountByUserAndPass() {
        String username = request.getParameter("user");
        String password = request.getParameter("pass");
        if (StringUtils.isNotBlank(username) && StringUtils.isNotBlank(password)) {
            Account account = accountDao.findAccountByUserAndPass(username, password);
            if (account != null && account.getUsername().equals(username) && account.getPass().equals(password)) {
                return account;
            }
        }
        return null;
    }

}
