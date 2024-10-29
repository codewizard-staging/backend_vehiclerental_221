package com.app.vehiclerental.repository;

import com.app.vehiclerental.model.RoadsideAssistance;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.List;


@Component
public class RoadsideAssistanceRepository extends SimpleJpaRepository<RoadsideAssistance, String> {
    private final EntityManager em;
    public RoadsideAssistanceRepository(EntityManager em) {
        super(RoadsideAssistance.class, em);
        this.em = em;
    }
    @Override
    public List<RoadsideAssistance> findAll() {
        return em.createNativeQuery("Select * from \"vehiclerental_070\".\"RoadsideAssistance\"", RoadsideAssistance.class).getResultList();
    }
}