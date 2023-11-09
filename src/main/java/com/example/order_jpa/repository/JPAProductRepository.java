package com.example.order_jpa.repository;

import com.example.order_jpa.entity.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
//@RequiredArgsConstructor
public class JPAProductRepository {
    @PersistenceContext
    private final EntityManager em;

    @Autowired
    public JPAProductRepository(EntityManager em) {
        this.em = em;
    }
    public void save(Product product) {
        em.persist(product);
    }

    public List<Product> findAll() {
        return em.createQuery("SELECT p FROM Product p", Product.class).getResultList();
    }

    public Product findById(Long productId) {
        return em.find(Product.class, productId);
    }

    public void remove(Product product) {
        em.remove(product);
    }
}
