package com.example.finance.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.List;

@Entity
public class UserModel {
    @Id
    @GeneratedValue
    int id;

    @Column
    String name;

    @Column
    String password;

    @Column
    String mobileno;

    @Column
    String email;

    public UserModel() {
    }

    public UserModel(String name, String password, String mobileno, String email) {
        this.name = name;
        this.password = password;
        this.mobileno = mobileno;
        this.email = email;
    }

    // getter and Setter Method
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobileno() {
        return mobileno;
    }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
