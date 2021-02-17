package com.example.designclothes.controller;

public class UserForm {

    private String name;
    private String password;
    private String cpassword;

    public String getName(){
        return name;
    }
    public String getPassword(){
        return password;
    }
    public String getCpassword(){
        return cpassword;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public void setCpassword(String cpassword){
        this.cpassword = cpassword;
    }

}
