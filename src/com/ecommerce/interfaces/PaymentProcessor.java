package com.ecommerce.interfaces;

public interface PaymentProcessor {
    boolean processPayment(double amount);
}