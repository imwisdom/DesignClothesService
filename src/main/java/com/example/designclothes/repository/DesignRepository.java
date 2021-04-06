package com.example.designclothes.repository;
import com.example.designclothes.domain.Design;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class DesignRepository {
    private final EntityManager em;

    public DesignRepository(EntityManager em){
        this.em = em;
    }

    public Design save(Design design){
        em.persist(design);
        return design;
    }
    public Optional<Design> findById(Long id){
        Design design = em.find(Design.class, id);
        return Optional.ofNullable(design);
    }
    public Optional<List<Design>> findByUserName(String userName){
        List<Design> result = em.createQuery("select d from Design d where d.userName = :user_name",
                Design.class)
                .setParameter("user_name", userName)
                .getResultList();
        return Optional.ofNullable(result);
    }
}
