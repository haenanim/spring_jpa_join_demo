package com.example.order_jpa.repository;
//
//import com.example.order_jpa.entity.Product;
//import org.springframework.data.jpa.repository.JpaRepository;
//
//public interface ProductRepository extends JpaRepository<Product, String> {
//}

import com.example.order_jpa.entity.Product;
import com.example.order_jpa.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductRepository {
  @PersistenceContext
  private final EntityManager em;

  public void save(Product product) {
    em.persist(product);
  }

  public List<Product> findAll() {
    return em.createQuery("select p from Product p", Product.class).getResultList();
  }
  public Product findById(String id) {
    return em.find(Product.class, id);
  }
  public void deleteById(String id) {
    em.remove(findById(id));
  }
}