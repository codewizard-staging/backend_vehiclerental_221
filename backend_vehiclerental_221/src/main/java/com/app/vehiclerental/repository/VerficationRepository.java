package com.app.vehiclerental.repository;

import com.app.vehiclerental.model.Verfication;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.List;


@Component
public class VerficationRepository extends SimpleJpaRepository<Verfication, String> {
    private final EntityManager em;
    public VerficationRepository(EntityManager em) {
        super(Verfication.class, em);
        this.em = em;
    }
    @Override
    public List<Verfication> findAll() {
        return em.createNativeQuery("Select * from \"vehiclerental_070\".\"Verfication\"", Verfication.class).getResultList();
    }
}