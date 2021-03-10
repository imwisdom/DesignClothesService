package com.example.designclothes.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Design {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;
    private String fileRoute;
    private Integer price;

    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id = id;
    }
    public String getUserName(){
        return userName;
    }
    public void setUserName(String userName){
        this.userName = userName;
    }
    public String getFileRoute(){
        return fileRoute;
    }
    public void setFileRoute(String fileRoute){
        this.fileRoute = fileRoute;
    }
    public int getPrice(){
        return price;
    }
    public void setPrice(Integer price){
        this.price = price;
    }


}
