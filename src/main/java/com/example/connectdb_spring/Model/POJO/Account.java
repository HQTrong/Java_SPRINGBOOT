package com.example.connectdb_spring.Model.POJO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "account")

public class Account {
    @Id
    @Column(name = "username")
    private String user;

    @Column(name = "pass")
    private String pass;
    @Column(name = "email")
    private String email;

    public Account() {
    }

    public Account(String username, String pass, String email) {
        this.user = username;
        this.pass = pass;
        this.email = email;
    }


    public String getUsername() {
        return user;
    }

    public void setUsername(String username) {
        this.user = username;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
