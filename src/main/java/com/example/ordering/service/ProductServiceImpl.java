package com.example.ordering.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.stereotype.Service;

import com.example.ordering.entity.Product;
import com.example.ordering.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {
    
    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product createProduct(String name, BigDecimal price, int stock) {
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setStock(stock);
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Long productId, String name, BigDecimal price, int stock) {
        Product existingProduct = getProduct(productId);
        existingProduct.setName(name);
        existingProduct.setPrice(price);
        existingProduct.setStock(stock);
        return productRepository.save(existingProduct);
    }

    @Override
    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }

    @Override
    public Product getProduct(Long productId) {
        return productRepository.findById(productId)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found with id: " + productId));
    }
}
