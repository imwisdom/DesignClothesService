package com.example.designclothes.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UserOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;
    private Long designId;
    private boolean isChecked;

    public Long getId(){
        return this.id;
    }
    public void setId(Long id){
        this.id = id;
    }
    public String getUserName(){
        return this.userName;
    }
    public void setUserName(String userName){
        this.userName = userName;
    }
    public Long getDesignId(){
        return this.designId;
    }
    public void setDesignId(Long designId){
        this.designId = designId;
    }
    public boolean getIsChecked(){
        return this.isChecked;
    }
    public void setIsChecked(boolean isChecked){
        this.isChecked = isChecked;
    }

}
