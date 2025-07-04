package com.ecommerce.services;

import com.ecommerce.models.CartItem;
import com.ecommerce.models.Product;
import com.ecommerce.interfaces.Expirable;
import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<CartItem> items;

    public Cart() {
        this.items = new ArrayList<>();
    }

    public void add(Product product, int quantity) {
        if (!product.isInStock(quantity)) {
            throw new IllegalArgumentException("Insufficient stock for " + product.getName());
        }
        
        if (product instanceof Expirable && ((Expirable) product).isExpired()) {
            throw new IllegalArgumentException("Product " + product.getName() + " is expired");
        }
        
        items.add(new CartItem(product, quantity));
    }

    public List<CartItem> getItems() {
        return new ArrayList<>(items);
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public double getSubtotal() {
        return items.stream()
                .mapToDouble(CartItem::getTotalPrice)
                .sum();
    }
}