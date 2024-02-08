package com.example.ordering.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.example.ordering.entity.Product;

@Service
public interface ProductService {
    // Implement methods like createProduct, updateProduct, deleteProduct, etc.
    Product createProduct(String name, BigDecimal price, int stock);

    Product updateProduct(Long productId, String name, BigDecimal price, int stock);

    void deleteProduct(Long productId);

    Product getProduct(Long productId);
}
