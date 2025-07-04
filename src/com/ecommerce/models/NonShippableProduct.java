package com.ecommerce.models;

public class NonShippableProduct extends Product {
    public NonShippableProduct(String name, double price, int quantity) {
        super(name, price, quantity);
    }

    public boolean requiresShipping() {
        return false;
    }
}