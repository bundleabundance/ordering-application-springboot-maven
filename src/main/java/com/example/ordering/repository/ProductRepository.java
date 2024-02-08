package com.example.ordering.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.ordering.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    // Additional query methods if needed
}



