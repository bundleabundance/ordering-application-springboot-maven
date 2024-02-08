package com.example.ordering.service;

import org.springframework.stereotype.Service;
import com.example.ordering.entity.OrderEntity;
import com.example.ordering.entity.OrderItem;
import com.example.ordering.entity.Product;

import java.util.List;

@Service
public interface OrderService {
    OrderEntity placeOrder(Long customerId, List<OrderItem> orderItems);

    OrderEntity getOrderForCode(Long orderCode);

    List<OrderEntity> getAllOrdersForCustomer(Long customerId);

    void updatePreviousPriceInCart(Product product);

}