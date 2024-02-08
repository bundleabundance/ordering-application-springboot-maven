package com.example.ordering.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

import java.math.BigDecimal;



@Entity
public class CartItem extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private int quantity;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    
    private BigDecimal previousPrice;

    //Constructor for JPA
    public CartItem() {
    }

    // Constructor with arguments
    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
    
    public BigDecimal getPreviousPrice() {
        return previousPrice;
    }

    public void setPreviousPrice(BigDecimal previousPrice) {
        this.previousPrice = previousPrice;
    }
}


