package com.example.designclothes.domain;

import javax.persistence.*;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String password;
    private boolean isAdmin;

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id = id;
    }
    public String getPassword(){
        return password;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public boolean getIsAdmin(){return isAdmin;}
    public void setIsAdmin(boolean isAdmin){this.isAdmin = isAdmin;}

}
