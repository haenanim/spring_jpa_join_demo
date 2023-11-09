package com.example.order_jpa.repository;

import com.example.order_jpa.entity.Order;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JPAOrderRepository {
    @PersistenceContext
    private final EntityManager em;

    public List<Order> findAll(){
        return em.createQuery("select o from Order o", Order.class).getResultList();
    }
    
    public Optional<Order> findById(Long orderId) {
        return Optional.ofNullable(em.find(Order.class, orderId));
    }

    public void save(Order order) {
        em.persist(order);
    }

    public void delete(Order order) {
        em.remove(order);
    }

    public List<Order> findOrdersByUserId(Long userId) {
        return null;
    }
}
