package com.example.ordering.service;

import org.springframework.stereotype.Service;
import com.example.ordering.entity.Customer;

@Service
public interface CustomerService {
    Customer addCustomer(String name, String email);

    Customer getCustomer(Long customerId);
}

