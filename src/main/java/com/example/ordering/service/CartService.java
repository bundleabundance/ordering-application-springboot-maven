package com.example.ordering.service;

import org.springframework.stereotype.Service;
import com.example.ordering.entity.Cart;
import com.example.ordering.entity.CartItem;
import java.util.List;

@Service
public interface CartService {
    Cart getCart(Long customerId);

    Cart updateCart(Long customerId, List<CartItem> cartItems);

    void emptyCart(Long customerId);

    void addProductToCart(Long customerId, Long productId, int quantity);

    void removeProductFromCart(Long customerId, Long productId);
}

