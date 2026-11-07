package week_eight.practice_systems;

import java.util.*;

/**
 * Question 5: Payment Processing for a Shopping System
 */
interface PaymentMethod {
    String getMethodName();
    boolean processPayment(double amount);
}

class CreditCardPayment implements PaymentMethod {
    public String getMethodName() { return "Credit Card"; }
    public boolean processPayment(double amount) {
        return true; // Successful
    }
}

class PayPalPayment implements PaymentMethod {
    private final boolean shouldSucceed;

    public PayPalPayment(boolean shouldSucceed) {
        this.shouldSucceed = shouldSucceed;
    }

    public String getMethodName() { return "PayPal"; }
    public boolean processPayment(double amount) {
        return shouldSucceed;
    }
}

class Product {
    private final String name;
    private final double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public double getPrice() { return price; }
}

class Order {
    enum Status { PENDING, PAID }

    private final String customerName;
    private final Map<Product, Integer> items = new HashMap<>();
    private Status status = Status.PENDING;

    public Order(String customerName) {
        this.customerName = customerName;
    }

    public void addItem(Product product, int quantity) {
        items.put(product, items.getOrDefault(product, 0) + quantity);
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public double calculateTotal() {
        double total = 0;
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            total += entry.getKey().getPrice() * entry.getValue();
        }
        return total;
    }

    public void attemptPayment(PaymentMethod method) {
        if (isEmpty()) {
            System.out.println("Cannot process payment for an empty order.");
            return;
        }

        System.out.println("Payment initiated via " + method.getMethodName() + " for Order " + extractInitial(customerName) + ".");
        boolean success = method.processPayment(calculateTotal());

        if (success) {
            this.status = Status.PAID;
            System.out.println("Payment for Order " + extractInitial(customerName) + " successful. Order status: Paid.");
        } else {
            System.out.println("Payment for Order " + extractInitial(customerName) + " failed. Order status: Pending.");
        }
    }

    private String extractInitial(String fullName) {
        String[] parts = fullName.split(" ");
        return parts[parts.length - 1]; // "Customer X" -> "X"
    }
}

public class ShoppingPaymentSystem {
    public static void main(String[] args) {
        Product prodA = new Product("Product A", 50.0);
        Product prodB = new Product("Product B", 100.0);
        Product prodC = new Product("Product C", 75.0);

        // Customer X creates order with Product A (qty 2) and Product B (qty 1)
        System.out.println("Order created for Customer X.");
        Order orderX = new Order("Customer X");
        orderX.addItem(prodA, 2);
        orderX.addItem(prodB, 1);
        orderX.attemptPayment(new CreditCardPayment());

        // Customer Y creates empty order
        Order orderY = new Order("Customer Y");
        orderY.attemptPayment(new CreditCardPayment());

        // Customer Z creates order with Product C (qty 1)
        System.out.println("Order created for Customer Z.");
        Order orderZ = new Order("Customer Z");
        orderZ.addItem(prodC, 1);
        orderZ.attemptPayment(new PayPalPayment(false)); // Fails
    }
}