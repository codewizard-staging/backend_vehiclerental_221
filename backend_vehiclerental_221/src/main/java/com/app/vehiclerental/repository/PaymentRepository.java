package com.app.vehiclerental.repository;

import com.app.vehiclerental.model.Payment;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.List;


@Component
public class PaymentRepository extends SimpleJpaRepository<Payment, String> {
    private final EntityManager em;
    public PaymentRepository(EntityManager em) {
        super(Payment.class, em);
        this.em = em;
    }
    @Override
    public List<Payment> findAll() {
        return em.createNativeQuery("Select * from \"vehiclerental_070\".\"Payment\"", Payment.class).getResultList();
    }
}