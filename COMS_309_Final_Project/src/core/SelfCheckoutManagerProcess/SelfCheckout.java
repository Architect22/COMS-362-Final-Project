package core.SelfCheckoutManagerProcess;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SelfCheckout {
    private Scanner in;
    private List<String> itemNames;
    private List<Float> itemPrices;
    private boolean ageRestrictedItem;
    private boolean scannerIssue;
    private boolean issueResolved;
    private boolean available;

    public SelfCheckout() {
        in = new Scanner(System.in);
        itemNames = new ArrayList<>();
        itemPrices = new ArrayList<>();
        ageRestrictedItem = false;
        scannerIssue = false;
        issueResolved = true;
        available = true;
        // startCheckout();
    }

    private void startCheckout() {
        System.out.println("Welcome to the self-checkout system!");
        boolean isCheckingOut = true;

        while (isCheckingOut) {
            System.out.println("Options: 1. Scan Item  2. View Cart  3. Checkout  4. Cancel Transaction");
            System.out.print("Select an option: ");
            int option = Integer.parseInt(in.nextLine());

            switch (option) {
                case 1:
                    scanItem();
                    break;
                case 2:
                    viewCart();
                    break;
                case 3:
                    processPayment();
                    isCheckingOut = false;
                    break;
                case 4:
                    cancelTransaction();
                    isCheckingOut = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void scanItem() {
        System.out.print("Enter item name: ");
        String name = in.nextLine();
        System.out.print("Enter item price: $");
        float price = Float.parseFloat(in.nextLine());

        itemNames.add(name);
        itemPrices.add(price);
        System.out.println(name + " added to cart at $" + price);
    }

    private void viewCart() {
        if (itemNames.isEmpty()) {
            System.out.println("Your cart is empty.");
            return;
        }

        System.out.println("Items in your cart:");
        float subtotal = 0f;
        for (int i = 0; i < itemNames.size(); i++) {
            System.out.printf("%-20s $%.2f%n", itemNames.get(i), itemPrices.get(i));
            subtotal += itemPrices.get(i);
        }
        System.out.printf("Subtotal: $%.2f%n", subtotal);
    }

    private void processPayment() {
        float subtotal = 0f;
        for (float price : itemPrices) {
            subtotal += price;
        }

        float tax = subtotal * 0.07f;
        float total = subtotal + tax;
        System.out.printf("Subtotal: $%.2f%nTax: $%.2f%nTotal: $%.2f%n", subtotal, tax, total);

        System.out.print("Enter payment amount: $");
        float payment = Float.parseFloat(in.nextLine());

        if (payment >= total) {
            System.out.printf("Payment accepted. Change: $%.2f%n", payment - total);
            printReceipt(subtotal, tax, total);
        } else {
            System.out.println("Insufficient payment. Transaction canceled.");
        }
    }

    private void cancelTransaction() {
        System.out.println("Transaction canceled. Thank you for using self-checkout.");
        itemNames.clear();
        itemPrices.clear();
    }

    private void printReceipt(float subtotal, float tax, float total) {
        System.out.println("\n--- Receipt ---");
        for (int i = 0; i < itemNames.size(); i++) {
            System.out.printf("%-20s $%.2f%n", itemNames.get(i), itemPrices.get(i));
        }
        System.out.printf(
                "---------------------------%n" +
                        "Subtotal:         $%.2f%n" +
                        "Tax:              $%.2f%n" +
                        "Total:            $%.2f%n" +
                        "---------------------------%n" +
                        "Thank you for shopping with us!%n",
                subtotal, tax, total);
        itemNames.clear();
        itemPrices.clear();
    }

    public boolean hasIssue() {
        return !issueResolved || scannerIssue || ageRestrictedItem;
    }

    public boolean hasAgeRestrictedItem() {
        return ageRestrictedItem;
    }

    public void authorizeRestrictedItem() {
        if (ageRestrictedItem) {
            System.out.println("Manager has authorized the purchase of the age-restricted item.");
            ageRestrictedItem = false;
        }
    }

    public boolean hasScannerIssue() {
        return scannerIssue;
    }

    public void resolveIssue() {
        System.out.println("Manager resolved the issue at this station.");
        issueResolved = true;
        scannerIssue = false;
    }

    public boolean rescanItem() {
        System.out.println("Attempting to rescan item...");
        if (Math.random() < 0.5) { // 50% chance the rescan succeeds
            scannerIssue = false;
            System.out.println("Rescan successful.");
            return true;
        } else {
            System.out.println("Rescan failed.");
            return false;
        }
    }

    public boolean enterBarcodeManually() {
        System.out.println("Manager manually entered the barcode.");
        scannerIssue = false;
        return true;
    }

    public boolean isAvailable() {
        return available && !hasIssue();
    }

    public void performRoutineCheck() {
        System.out.println("Performing routine check on the self-checkout station.");
        issueResolved = true;
        scannerIssue = false;
        ageRestrictedItem = false;
    }

    public static void main(String[] args) {
        new SelfCheckout();
    }
}
