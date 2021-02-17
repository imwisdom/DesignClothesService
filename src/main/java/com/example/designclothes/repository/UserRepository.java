package com.example.designclothes.repository;

import com.example.designclothes.domain.User;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class UserRepository {

    private final EntityManager em;

    public UserRepository(EntityManager em){
        this.em = em;
    }

    public User save(User user){
        em.persist(user);
        return user;
    }
    public Optional<User> findById(Long id){
        User user = em.find(User.class, id);
        return Optional.ofNullable(user);
    }
    public Optional<User> findByName(String name){
        List<User> result = em.createQuery("select u from User where u.name = :name",
                User.class)
                .setParameter("name", name)
                .getResultList();
        return result.stream().findAny();
    }
}
