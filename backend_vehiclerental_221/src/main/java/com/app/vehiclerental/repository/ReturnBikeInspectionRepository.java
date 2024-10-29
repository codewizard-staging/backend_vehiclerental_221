package com.app.vehiclerental.repository;

import com.app.vehiclerental.model.ReturnBikeInspection;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.List;


@Component
public class ReturnBikeInspectionRepository extends SimpleJpaRepository<ReturnBikeInspection, String> {
    private final EntityManager em;
    public ReturnBikeInspectionRepository(EntityManager em) {
        super(ReturnBikeInspection.class, em);
        this.em = em;
    }
    @Override
    public List<ReturnBikeInspection> findAll() {
        return em.createNativeQuery("Select * from \"vehiclerental_070\".\"ReturnBikeInspection\"", ReturnBikeInspection.class).getResultList();
    }
}