package com.example.ordering.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.ordering.entity.Customer;
import com.example.ordering.entity.Cart;
import com.example.ordering.entity.Product;
import java.util.Optional;
import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByCustomer(Customer customer);
    List<Cart> findByCartItemsProduct(Product product);
}

