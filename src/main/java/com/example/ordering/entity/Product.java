package com.example.ordering.entity;

import jakarta.persistence.Entity;
import java.math.BigDecimal;

@Entity
public class Product extends BaseEntity {
    private String name;
    private BigDecimal price;
    private int stock;

    // Add this field for previous price
    private BigDecimal previousPrice;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public BigDecimal getPreviousPrice(){
        return previousPrice;
    }

    public void setPreviousPrice(BigDecimal previousprice){
        this.previousPrice = previousprice;
    }

}

