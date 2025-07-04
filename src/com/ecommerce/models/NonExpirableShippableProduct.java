package com.ecommerce.models;

import com.ecommerce.interfaces.Shippable;

public class NonExpirableShippableProduct extends Product implements Shippable {
    private double weight;

    public NonExpirableShippableProduct(String name, double price, int quantity, double weight) {
        super(name, price, quantity);
        this.weight = weight;
    }

    @Override
    public double getWeight() {
        return weight;
    }

    @Override
    public boolean requiresShipping() {
        return true;
    }
}