package com.ecommerce.services;

import com.ecommerce.interfaces.Shippable;
import java.util.List;

public class ShippingService {
    private static final double SHIPPING_RATE_PER_KG = 30.0;

    public void processShipment(List<Shippable> shippableItems) {
        if (shippableItems.isEmpty()) {
            return;
        }

        System.out.println("** Shipment notice **");
        double totalWeight = 0.0;
        
        for (Shippable item : shippableItems) {
            double itemWeight = item.getWeight();
            totalWeight += itemWeight;
            System.out.printf("1x %-12s %dg%n", item.getName(), (int)(itemWeight * 1000));
        }
        
        System.out.printf("Total package weight %.1fkg%n", totalWeight);
        System.out.println();
    }

    public double calculateShippingFee(List<Shippable> shippableItems) {
        double totalWeight = shippableItems.stream()
                .mapToDouble(Shippable::getWeight)
                .sum();
        return totalWeight > 0 ? SHIPPING_RATE_PER_KG : 0.0;
    }
}
