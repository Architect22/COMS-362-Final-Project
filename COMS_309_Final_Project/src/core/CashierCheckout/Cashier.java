package core.CashierCheckout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import core.Employee;
import core.InventoryManager;
import core.PriceManager;

public class Cashier extends Employee {
    private Scanner in;
    private InventoryManager invManager;
    private PriceManager priceManager;
    
    public Cashier(InventoryManager invManager, PriceManager priceManager) {
        super(invManager, priceManager, null, null, null); // Pass null for unused managers in this context
        this.invManager = invManager;
        this.priceManager = priceManager;
        this.in = new Scanner(System.in);
    }

    public void startCheckout() {
        System.out.println("Starting Checkout Process...");
        
        List<String> itemNames = new ArrayList<>();
        List<Float> itemPrices = new ArrayList<>();
        Map<String, Integer> cart = new HashMap<>();
        float subtotal = 0;
        
        // Item Scanning Loop
        while (true) {
            System.out.print("Scan item (enter 'done' to finish or 'cancel' to abort): ");
            String itemName = in.nextLine();
            
            if ("done".equalsIgnoreCase(itemName)) {
                break;
            }
            
            if ("cancel".equalsIgnoreCase(itemName)) {
                System.out.println("Checkout canceled.");
                return;
            }
            
            // Check if item exists in inventory
            float price = invManager.inventorySystem.getPrice(itemName);
            int stockLevel = invManager.inventorySystem.getStockLevel(itemName);
            
            if (Float.isNaN(price) || stockLevel <= 0) {
                System.out.printf("Error: Item '%s' not found or out of stock.%n", itemName);
                continue;
            }
            
            // Add item to cart
            itemNames.add(itemName);
            itemPrices.add(price);
            cart.merge(itemName, 1, Integer::sum);
            subtotal += price;
            
            System.out.printf("Item '%s' added. Price: $%.2f%n", itemName, price);
        }
        
        // Calculate Total with Tax
        subtotal = Math.round(subtotal * 100f) / 100f; // rounding subtotal
        float tax = Math.round(subtotal * 0.07f * 100f) / 100f; // 7% tax
        float total = subtotal + tax;
        
        System.out.printf("Subtotal: $%.2f%nTax: $%.2f%nTotal: $%.2f%n", subtotal, tax, total);
        
        // Payment Process
        System.out.print("Select payment method (card/cash): ");
        String paymentMethod = in.nextLine().toLowerCase();
        
        if ("card".equals(paymentMethod)) {
            processCardPayment(total);
        } else if ("cash".equals(paymentMethod)) {
            processCashPayment(total);
        } else {
            System.out.println("Invalid payment method. Transaction canceled.");
            return;
        }
        
        // Update Inventory after successful payment
        for (Map.Entry<String, Integer> entry : cart.entrySet()) {
            invManager.inventorySystem.updateStock(entry.getKey(),
                    invManager.inventorySystem.getStockLevel(entry.getKey()) - entry.getValue());
        }
        
        // Print Receipt
        System.out.println("Transaction completed. Printing receipt...");
        printReceipt(itemNames, itemPrices, subtotal, tax, total);
    }
    
    private void processCardPayment(float total) {
        while (true) {
            System.out.print("Enter card number: ");
            String cardNumber = in.nextLine();
            
            // Simulate card processing (90% success rate)
            if (Math.random() < 0.9) {
                System.out.printf("Payment of $%.2f approved.%n", total);
                break;
            } else {
                System.out.println("Payment declined. Please try again.");
            }
        }
    }
    
    private void processCashPayment(float total) {
        while (true) {
            System.out.printf("Enter cash amount (Total: $%.2f): ", total);
            float cashReceived = Float.parseFloat(in.nextLine());
            
            if (cashReceived < total) {
                System.out.println("Insufficient cash. Please provide the correct amount.");
            } else {
                float change = cashReceived - total;
                System.out.printf("Payment accepted. Change: $%.2f%n", change);
                break;
            }
        }
    }
    
    private void printReceipt(List<String> itemNames, List<Float> itemPrices, float subtotal, float tax, float total) {
        System.out.println("\n--- Receipt ---");
        for (int i = 0; i < itemNames.size(); i++) {
            System.out.printf("%s: $%.2f%n", itemNames.get(i), itemPrices.get(i));
        }
        System.out.printf("Subtotal: $%.2f%nTax: $%.2f%nTotal: $%.2f%n", subtotal, tax, total);
        System.out.println("Thank you for shopping with us!");
    }
}
