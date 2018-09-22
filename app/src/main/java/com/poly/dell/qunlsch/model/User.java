package com.poly.dell.qunlsch.model;

public class User {
    String username,password,name,phonenumber;

    public User(String username, String password, String name, String phonenumber) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.phonenumber = phonenumber;
    }

    public User() {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }
}
