package core;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OnlineOrderApp {
    private Scanner in;
    private List<String> itemCatalog;
    private List<String> cartItems;
    private List<Float> cartPrices;

    public OnlineOrderApp() {
        in = new Scanner(System.in);
        itemCatalog = new ArrayList<>();
        cartItems = new ArrayList<>();
        cartPrices = new ArrayList<>();
        
        initializeCatalog();
        startOrderingProcess();
    }

    private void initializeCatalog() {
        // Adding items to catalog (normally would be fetched from a database)
        itemCatalog.add("Milk - $3.00");
        itemCatalog.add("Eggs - $2.50");
        itemCatalog.add("Bread - $2.00");
        itemCatalog.add("Apples - $1.50 per lb");
        itemCatalog.add("Bananas - $0.50 per lb");
        itemCatalog.add("Chicken Breast - $5.00 per lb");
    }

    private void startOrderingProcess() {
        System.out.println("Welcome to the Online Ordering App!");

        boolean isOrdering = true;
        while (isOrdering) {
            System.out.println("Options: 1. View Catalog  2. Add to Cart  3. View Cart  4. Checkout  5. Cancel Order");
            System.out.print("Select an option: ");
            int option = Integer.parseInt(in.nextLine());

            switch (option) {
                case 1:
                    viewCatalog();
                    break;
                case 2:
                    addToCart();
                    break;
                case 3:
                    viewCart();
                    break;
                case 4:
                    checkout();
                    isOrdering = false;
                    break;
                case 5:
                    cancelOrder();
                    isOrdering = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void viewCatalog() {
        System.out.println("Available items:");
        for (int i = 0; i < itemCatalog.size(); i++) {
            System.out.println((i + 1) + ". " + itemCatalog.get(i));
        }
    }

    private void addToCart() {
        viewCatalog();
        System.out.print("Enter item number to add to cart: ");
        int itemNumber = Integer.parseInt(in.nextLine());

        if (itemNumber < 1 || itemNumber > itemCatalog.size()) {
            System.out.println("Invalid item number. Please try again.");
            return;
        }

        String selectedItem = itemCatalog.get(itemNumber - 1);
        cartItems.add(selectedItem.split(" - ")[0]);
        float price = Float.parseFloat(selectedItem.split(" - ")[1].substring(1));
        cartPrices.add(price);
        
        System.out.println(selectedItem + " has been added to your cart.");
    }

    private void viewCart() {
        if (cartItems.isEmpty()) {
            System.out.println("Your cart is empty.");
            return;
        }

        System.out.println("Items in your cart:");
        float subtotal = 0f;
        for (int i = 0; i < cartItems.size(); i++) {
            System.out.printf("%-20s $%.2f%n", cartItems.get(i), cartPrices.get(i));
            subtotal += cartPrices.get(i);
        }
        System.out.printf("Subtotal: $%.2f%n", subtotal);
    }

    private void checkout() {
        if (cartItems.isEmpty()) {
            System.out.println("Your cart is empty. Please add items to your cart before checking out.");
            return;
        }

        float subtotal = 0f;
        for (float price : cartPrices) {
            subtotal += price;
        }
        
        float tax = subtotal * 0.07f;
        float total = subtotal + tax;
        System.out.printf("Subtotal: $%.2f%nTax: $%.2f%nTotal: $%.2f%n", subtotal, tax, total);

        System.out.print("Please enter your preferred pickup time (e.g., 3:00 PM): ");
        String pickupTime = in.nextLine();

        System.out.println("Order confirmed! Please arrive for curbside pickup at " + pickupTime);
        printReceipt(subtotal, tax, total);
    }

    private void cancelOrder() {
        System.out.println("Order canceled. Thank you for using the Online Ordering App.");
        cartItems.clear();
        cartPrices.clear();
    }

    private void printReceipt(float subtotal, float tax, float total) {
        System.out.println("\n--- Receipt ---");
        for (int i = 0; i < cartItems.size(); i++) {
            System.out.printf("%-20s $%.2f%n", cartItems.get(i), cartPrices.get(i));
        }
        System.out.printf(
            "---------------------------%n" +
            "Subtotal:         $%.2f%n" +
            "Tax:              $%.2f%n" +
            "Total:            $%.2f%n" +
            "---------------------------%n" +
            "Thank you for your order!%n", 
            subtotal, tax, total
        );
        cartItems.clear();
        cartPrices.clear();
    }

    public static void main(String[] args) {
        new OnlineOrderApp();
    }
}
