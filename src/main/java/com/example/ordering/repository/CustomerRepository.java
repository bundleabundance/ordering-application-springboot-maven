package com.example.ordering.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.ordering.entity.Customer;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByEmail(String email);
}

