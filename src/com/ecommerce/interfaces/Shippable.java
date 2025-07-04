package com.ecommerce.interfaces;

public interface Shippable {
    String getName();
    double getWeight();
    boolean requiresShipping();
}