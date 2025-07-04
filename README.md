# E-Commerce System

A comprehensive e-commerce system built in Java that demonstrates all SOLID principles and good Object-Oriented Programming (OOP) design patterns. This project showcases a clean, maintainable, and extensible architecture for handling products, customers, shopping carts, and checkout processes.

## 🚀 Features

- **Product Management**: Support for different product types (expirable, non-expirable, shippable, non-shippable)
- **Shopping Cart**: Add products with quantity validation and stock checking
- **Checkout System**: Complete payment processing with receipt generation
- **Shipping Service**: Automated shipping calculations and shipment processing
- **Inventory Management**: Real-time stock updates and availability checking
- **Payment Processing**: Customer balance management and transaction handling

## 🏗️ Architecture & Design Patterns

### SOLID Principles Implementation

#### 1. **Single Responsibility Principle (SRP)**
- `Product`: Manages product data and basic operations
- `Cart`: Handles shopping cart functionality
- `CheckoutService`: Processes checkout operations
- `ShippingService`: Manages shipping calculations and notifications

#### 2. **Open/Closed Principle (OCP)**
- New product types can be added without modifying existing code
- Payment processors can be extended without changing core logic
- Shipping strategies can be implemented without altering base classes

#### 3. **Liskov Substitution Principle (LSP)**
- All product subtypes can be used interchangeably where `Product` is expected
- Payment processors can be substituted without breaking functionality

#### 4. **Interface Segregation Principle (ISP)**
- `Expirable`: For products with expiration dates
- `Shippable`: For products requiring shipping
- `PaymentProcessor`: For payment handling capabilities

#### 5. **Dependency Inversion Principle (DIP)**
- High-level modules depend on abstractions (interfaces)
- `CheckoutService` depends on `PaymentProcessor` interface, not concrete implementations

## Project Structure

```text
FawryAssignment/
├── src/
│   └── com/
│       └── ecommerce/
│           ├── ECommerceApp.java                    # Main application entry point
│           ├── interfaces/
│           │   ├── Expirable.java                   # Interface for expirable products
│           │   ├── Shippable.java                   # Interface for shippable products
│           │   └── PaymentProcessor.java            # Interface for payment processing
│           ├── models/
│           │   ├── Product.java                     # Abstract base product class
│           │   ├── ExpirableProduct.java            # Expirable and shippable products
│           │   ├── NonExpirableShippableProduct.java # Non-expirable shippable products
│           │   ├── NonShippableProduct.java         # Digital/non-shippable products
│           │   ├── CartItem.java                    # Cart item representation
│           │   └── Customer.java                    # Customer with payment capabilities
│           └── services/
│               ├── Cart.java                        # Shopping cart service
│               ├── CheckoutService.java             # Checkout processing service
│               └── ShippingService.java             # Shipping calculation service
└── bin/                                             # Compiled class files
```


## 📋 Usage Example

The main application demonstrates a complete e-commerce workflow:

```java
// Create products
Product cheese = new ExpirableProduct("Cheese", 100.0, 10, 
    LocalDate.now().plusDays(30), 0.2);
Product tv = new NonExpirableShippableProduct("TV", 500.0, 5, 15.0);
Product scratchCard = new NonShippableProduct("ScratchCard", 50.0, 20);

// Create customer
Customer customer = new Customer("John Doe", 1000.0);

// Add items to cart and checkout
Cart cart = new Cart();
cart.add(cheese, 2);
cart.add(tv, 1);
cart.add(scratchCard, 1);

CheckoutService checkoutService = new CheckoutService(new ShippingService());
checkoutService.checkout(customer, cart);
```

## 📊 Sample Output
```text
Customer balance before checkout: $1000.0

** Shipment notice **
1x Cheese 200g
1x Cheese 200g
1x Biscuits 700g
1x TV 15000g
Total package weight: 16.1kg

** Checkout receipt **
2x Cheese 200
1x Biscuits 150
1x TV 500
1x ScratchCard 50
Subtotal 900
Shipping 30
Amount 930

Customer balance after checkout: $70.0
```

