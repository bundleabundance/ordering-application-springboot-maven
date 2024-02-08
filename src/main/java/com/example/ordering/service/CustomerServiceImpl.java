package com.example.ordering.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.stereotype.Service;

import com.example.ordering.entity.Customer;
import com.example.ordering.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Customer addCustomer(String name, String email) {
        Customer customer = new Customer();
        customer.setName(name);
        customer.setEmail(email);
        return customerRepository.save(customer);
    }

    @Override
    public Customer getCustomer(Long customerId) {
        return customerRepository.findById(customerId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found with id: " + customerId));
    }
}

