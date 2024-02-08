package com.example.ordering.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ordering.entity.Customer;
import com.example.ordering.entity.OrderEntity;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    // Additional query methods if needed
    Optional<OrderEntity> findById(Long id);
    List<OrderEntity> findByCustomer(Customer customer);
}