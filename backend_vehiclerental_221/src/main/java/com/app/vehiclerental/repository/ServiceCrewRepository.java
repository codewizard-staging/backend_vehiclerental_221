package com.app.vehiclerental.repository;

import com.app.vehiclerental.model.ServiceCrew;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.List;


@Component
public class ServiceCrewRepository extends SimpleJpaRepository<ServiceCrew, String> {
    private final EntityManager em;
    public ServiceCrewRepository(EntityManager em) {
        super(ServiceCrew.class, em);
        this.em = em;
    }
    @Override
    public List<ServiceCrew> findAll() {
        return em.createNativeQuery("Select * from \"vehiclerental_070\".\"ServiceCrew\"", ServiceCrew.class).getResultList();
    }
}