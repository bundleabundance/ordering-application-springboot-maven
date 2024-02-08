package com.example.ordering.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.stereotype.Service;


import com.example.ordering.repository.CustomerRepository;
import com.example.ordering.repository.OrderRepository;
import com.example.ordering.repository.CartRepository;
import com.example.ordering.entity.Customer;
import com.example.ordering.entity.OrderEntity;
import com.example.ordering.entity.OrderItem;
import com.example.ordering.entity.Product;
import com.example.ordering.entity.Cart;
import com.example.ordering.entity.CartItem;

import java.math.BigDecimal;

import java.util.List;


@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CartRepository cartRepository;

    // ... other methods

    @Override
    public OrderEntity placeOrder(Long customerId, List<OrderItem> orderItems) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found with id: " + customerId));

        OrderEntity order = new OrderEntity();
        order.setCustomer(customer);
        order.setOrderItems(orderItems);  // Assume orderItems are passed from the client

        // Additional logic
        calculateOrderTotal(order);

        // Update product stock
        updateProductStock(order);

        return orderRepository.save(order);
    }

    @Override
    public OrderEntity getOrderForCode(Long orderCode) {
        return orderRepository.findById(orderCode)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found with code: " + orderCode));
    }

    @Override
    public List<OrderEntity> getAllOrdersForCustomer(Long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found with id: " + customerId));

        return orderRepository.findByCustomer(customer);
    }

    // Additional method to calculate order total
    private void calculateOrderTotal(OrderEntity order) {
        BigDecimal total = BigDecimal.ZERO;
        for (OrderItem orderItem : order.getOrderItems()) {
            BigDecimal itemTotal = orderItem.getProduct().getPrice().multiply(BigDecimal.valueOf(orderItem.getQuantity()));
            total = total.add(itemTotal);
        }
        order.setTotal(total);
    }

    // Additional method to update product stock
    private void updateProductStock(OrderEntity order) {
        for (OrderItem orderItem : order.getOrderItems()) {
            Product product = orderItem.getProduct();
            int quantityOrdered = orderItem.getQuantity();
            int currentStock = product.getStock();

            // Check if there is enough stock
            if (currentStock < quantityOrdered) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "Not enough stock for product: " + product.getName());
            }

            // Update stock
            product.setStock(currentStock - quantityOrdered);

            // Update previous price in the cart
            updatePreviousPriceInCart(product);
        }
    }

    @Override
    public void updatePreviousPriceInCart(Product product) {
        List<Cart> carts = cartRepository.findByCartItemsProduct(product);
        for (Cart cart : carts) {
            for (CartItem cartItem : cart.getCartItems()) {
                if (cartItem.getProduct().equals(product)) {
                    // Update previous price in the cart item
                    cartItem.setPreviousPrice(product.getPreviousPrice());
                }
            }
            // Save the cart with updated cart items
            cartRepository.save(cart);
        }
    }
}

