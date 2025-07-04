package com.ecommerce.services;

import com.ecommerce.models.CartItem;
import com.ecommerce.models.Product;
import com.ecommerce.interfaces.PaymentProcessor;
import com.ecommerce.interfaces.Shippable;
import com.ecommerce.interfaces.Expirable;
import java.util.List;
import java.util.ArrayList;

public class CheckoutService {
    private ShippingService shippingService;

    public CheckoutService(ShippingService shippingService) {
        this.shippingService = shippingService;
    }

    public void checkout(PaymentProcessor customer, Cart cart) {
        // Validation
        if (cart.isEmpty()) {
            throw new IllegalArgumentException("Cart is empty");
        }

        // Check stock and expiration
        for (CartItem item : cart.getItems()) {
            Product product = item.getProduct();
            
            if (!product.isInStock(item.getQuantity())) {
                throw new IllegalArgumentException("Product " + product.getName() + " is out of stock");
            }
            
            if (product instanceof Expirable && ((Expirable) product).isExpired()) {
                throw new IllegalArgumentException("Product " + product.getName() + " is expired");
            }
        }

        // Calculate costs
        double subtotal = cart.getSubtotal();
        List<Shippable> shippableItems = getShippableItems(cart);
        double shippingFee = shippingService.calculateShippingFee(shippableItems);
        double totalAmount = subtotal + shippingFee;

        // Process payment
        if (!customer.processPayment(totalAmount)) {
            throw new IllegalArgumentException("Insufficient balance");
        }

        // Update inventory
        for (CartItem item : cart.getItems()) {
            item.getProduct().reduceQuantity(item.getQuantity());
        }

        // Process shipping
        if (!shippableItems.isEmpty()) {
            shippingService.processShipment(shippableItems);
        }

        // Print receipt
        printReceipt(cart, subtotal, shippingFee, totalAmount);
    }

    private List<Shippable> getShippableItems(Cart cart) {
        List<Shippable> shippableItems = new ArrayList<>();
        
        for (CartItem item : cart.getItems()) {
            Product product = item.getProduct();
            if (product instanceof Shippable && ((Shippable) product).requiresShipping()) {
                // Add multiple instances for quantity
                for (int i = 0; i < item.getQuantity(); i++) {
                    shippableItems.add((Shippable) product);
                }
            }
        }
        
        return shippableItems;
    }

    private void printReceipt(Cart cart, double subtotal, double shippingFee, double totalAmount) {
        System.out.println("** Checkout receipt **");
        
        for (CartItem item : cart.getItems()) {
            System.out.printf("%dx %-12s %d%n", 
                item.getQuantity(), 
                item.getProduct().getName(), 
                (int)item.getTotalPrice());
        }
        
        System.out.println("----------------------");
        System.out.printf("Subtotal         %d%n", (int)subtotal);
        System.out.printf("Shipping         %d%n", (int)shippingFee);
        System.out.printf("Amount           %d%n", (int)totalAmount);
        System.out.println();
    }
}