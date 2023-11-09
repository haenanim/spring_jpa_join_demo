package com.example.order_jpa.service;

import com.example.order_jpa.entity.OrderProduct;
import com.example.order_jpa.entity.Product;
import com.example.order_jpa.repository.OrderProductRepository;
import com.example.order_jpa.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
  private final ProductRepository productRepository;
  @Autowired
  public ProductService(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }
  public void save(Product product) {
    productRepository.save(product);
  }
  public List<Product> findAll() {
    return productRepository.findAll();
  }
  public Product findById(String id) {
    return productRepository.findById(id);
  }
  public void deleteById(String id) {
    productRepository.deleteById(id);
  }


}
