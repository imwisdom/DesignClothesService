package com.example.designclothes.repository;

import com.example.designclothes.domain.UserOrder;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class UserOrderRepository {
    private final EntityManager em;

    public UserOrderRepository(EntityManager em){
        this.em = em;
    }
    public UserOrder save(UserOrder userOrder){
        em.persist(userOrder);
        return userOrder;
    }
    public Optional<UserOrder> findById(Long id){
        UserOrder userOrder = em.find(UserOrder.class, id);
        return Optional.ofNullable(userOrder);
    }
    public Optional<List<UserOrder>> findByUserName(String userName){
        List<UserOrder> result = em.createQuery("select o from UserOrder o where o.userName = :user_name",
                UserOrder.class)
                .setParameter("user_name", userName)
                .getResultList();
        return Optional.ofNullable(result);
    }
    public Optional<List<UserOrder>> findAll(){
        List<UserOrder> result = em.createQuery("select o from UserOrder o",
                UserOrder.class).getResultList();
        return Optional.ofNullable(result);
    }
    public UserOrder updateChecked(Long id){
        UserOrder userOrder = em.find(UserOrder.class, id);
        userOrder.setIsChecked(true);
        em.persist(userOrder);
        return userOrder;
    }
    public UserOrder remove(Long id){
        UserOrder userOrder = em.find(UserOrder.class, id);
        em.remove(userOrder);
        return userOrder;
    }

}
