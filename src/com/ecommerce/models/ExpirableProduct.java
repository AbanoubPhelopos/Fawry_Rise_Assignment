package com.ecommerce.models;

import com.ecommerce.interfaces.Expirable;
import com.ecommerce.interfaces.Shippable;
import java.time.LocalDate;

public class ExpirableProduct extends Product implements Expirable, Shippable {
    private LocalDate expirationDate;
    private double weight;

    public ExpirableProduct(String name, double price, int quantity, 
                           LocalDate expirationDate, double weight) {
        super(name, price, quantity);
        this.expirationDate = expirationDate;
        this.weight = weight;
    }

    @Override
    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    @Override
    public boolean isExpired() {
        return LocalDate.now().isAfter(expirationDate);
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