package com.example.order_jpa.service;

import com.example.order_jpa.dto.ProductUpdateDto;
import com.example.order_jpa.entity.Product;
import com.example.order_jpa.repository.JPAProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductService {
    private final JPAProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public void addProduct(Product product) {
        productRepository.save(product);
    }

    public void deleteProduct(long productId) {
        Product product = productRepository.findById(productId);
        productRepository.remove(product);
    }

    public Product getProductInfo(long productId) {
        return productRepository.findById(productId);
    }

    public void updateProduct(ProductUpdateDto productUpdateDto) {
        Product product = productRepository.findById(productUpdateDto.getProductId());
        product.setName(productUpdateDto.getName());
        product.setPrice(productUpdateDto.getPrice());
//        product.setQuantity(productUpdateDto.getQuantity());
        productRepository.save(product);
    }
}
