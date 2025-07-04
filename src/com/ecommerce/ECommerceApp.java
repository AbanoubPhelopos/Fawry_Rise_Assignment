package com.ecommerce;

import com.ecommerce.models.*;
import com.ecommerce.services.*;
import java.time.LocalDate;

public class ECommerceApp {
    public static void main(String[] args) {

        Product cheese = new ExpirableProduct("Cheese", 100.0, 10, 
                                            LocalDate.now().plusDays(30), 0.2);
        Product tv = new NonExpirableShippableProduct("TV", 500.0, 5, 15.0);
        Product scratchCard = new NonShippableProduct("ScratchCard", 50.0, 20);
        Product biscuits = new ExpirableProduct("Biscuits", 150.0, 8, 
                                               LocalDate.now().plusDays(60), 0.7);

        // Create customer
        Customer customer = new Customer("John Doe", 1000.0);

        // Create services
        ShippingService shippingService = new ShippingService();
        CheckoutService checkoutService = new CheckoutService(shippingService);

        // Create cart and add items
        Cart cart = new Cart();
        
        try {
            cart.add(cheese, 2);
            cart.add(biscuits, 1);
            cart.add(tv, 1);
            cart.add(scratchCard, 1);

            System.out.println("Customer balance before checkout: $" + customer.getBalance());
            System.out.println();

            // Checkout
            checkoutService.checkout(customer, cart);

            System.out.println("Customer balance after checkout: $" + customer.getBalance());
            
        } catch (Exception e) {
            System.err.println("Checkout failed: " + e.getMessage());
        }
    }
}