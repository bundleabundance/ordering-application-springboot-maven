package com.example.ordering.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.stereotype.Service;

import com.example.ordering.entity.Cart;
import com.example.ordering.repository.CartRepository;
import com.example.ordering.repository.ProductRepository;
import com.example.ordering.repository.CustomerRepository;
import com.example.ordering.entity.CartItem;
import com.example.ordering.entity.Customer;
import com.example.ordering.entity.Product;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CustomerRepository customerRepository;

    // ... other methods

    @Override
    public Cart getCart(Long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found with id: " + customerId));

        return cartRepository.findByCustomer(customer)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cart not found for customer: " + customerId));
    }

    @Override
    public Cart updateCart(Long customerId, List<CartItem> cartItems) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found with id: " + customerId));

        Cart cart = cartRepository.findByCustomer(customer)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cart not found for customer: " + customerId));

        // Add logic to update cart items based on the provided list
        cart.setCartItems(cartItems);

        return cartRepository.save(cart);
    }

    @Override
    public void emptyCart(Long customerId) {
        Cart cart = getCart(customerId);
        cart.getCartItems().clear();
        cartRepository.save(cart);
    }

    @Override
    public void addProductToCart(Long customerId, Long productId, int quantity) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found with id: " + customerId));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found with id: " + productId));

        Cart cart = cartRepository.findByCustomer(customer)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cart not found for customer: " + customerId));

        // Check if the product is already in the cart
        CartItem existingCartItem = findCartItemByProduct(cart, product);

        if (existingCartItem != null) {
            // If the product is already in the cart, update the quantity
            existingCartItem.setQuantity(existingCartItem.getQuantity() + quantity);
        } else {
            // If the product is not in the cart, create a new cart item
            CartItem newCartItem = new CartItem(product, quantity);
            cart.getCartItems().add(newCartItem);
        }

        cartRepository.save(cart);
    }

    @Override
    public void removeProductFromCart(Long customerId, Long productId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found with id: " + customerId));

        Cart cart = cartRepository.findByCustomer(customer)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cart not found for customer: " + customerId));

        // Find and remove the cart item based on the provided product id
        CartItem cartItemToRemove = findCartItemByProductId(cart, productId);

        if (cartItemToRemove != null) {
            cart.getCartItems().remove(cartItemToRemove);
            cartRepository.save(cart);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found in the cart: " + productId);
        }
    }

    // Helper method to find a CartItem in the cart based on the product
    private CartItem findCartItemByProduct(Cart cart, Product product) {
        return cart.getCartItems().stream()
                .filter(cartItem -> cartItem.getProduct().equals(product))
                .findFirst()
                .orElse(null);
    }

    // Helper method to find a CartItem in the cart based on the product id
    private CartItem findCartItemByProductId(Cart cart, Long productId) {
        return cart.getCartItems().stream()
                .filter(cartItem -> cartItem.getProduct().getId().equals(productId))
                .findFirst()
                .orElse(null);
    }
}
