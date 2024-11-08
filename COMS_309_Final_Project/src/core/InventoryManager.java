package core;

import java.util.Scanner;

public class InventoryManager {
    public final InventorySystem inventorySystem;

    public InventoryManager(InventorySystem inventorySystem) {
        this.inventorySystem = inventorySystem;
    }

    public void login() {
        System.out.println("Inventory Manager logged in successfully.");
    }

    public void viewInventoryDashboard() {
        if (!inventorySystem.isSystemOperational()) {
            handleDashboardFailure();
        } else {
            inventorySystem.displayInventoryDashboard();
            checkForAlerts();
        }
    }

    private void handleDashboardFailure() {
        System.out.println("Dashboard failed to load. Attempting to reload or contact IT...");
    }

    private void checkForAlerts() {
        for (String product : inventorySystem.getStockLevels().keySet()) {
            if (inventorySystem.checkForDiscrepancies(product)) {
                System.out.println("Alert: Discrepancy detected for " + product);
            }
        }
    }

    public void adjustReorderQuantities() {
        System.out.print("Enter the product to adjust reorder quantity: ");
        String product = new Scanner(System.in).nextLine();
        System.out.print("Enter the new reorder quantity: ");
        int quantity = Integer.parseInt(new Scanner(System.in).nextLine());
        inventorySystem.updateReorderQuantity(product, quantity);
        System.out.println("Reorder quantity for " + product + " adjusted to " + quantity);
    }

    public void placeOrder() {
        System.out.print("Enter the product to order: ");
        String product = new Scanner(System.in).nextLine();
        System.out.print("Enter the quantity to order: ");
        int quantity = Integer.parseInt(new Scanner(System.in).nextLine());
        inventorySystem.placeOrder(product, quantity);
    }

    public void checkStockDiscrepancies() {
        System.out.print("Enter product to check for discrepancies: ");
        String product = new Scanner(System.in).nextLine();
        System.out.println("Checking for discrepancies in stock for " + product + "...");
        checkForAlerts();
    }

    public void manuallyAdjustStock() {
        System.out.print("Enter product to manually adjust stock: ");
        String product = new Scanner(System.in).nextLine();
        System.out.print("Enter the adjustment amount (positive or negative): ");
        int adjustment = Integer.parseInt(new Scanner(System.in).nextLine());
        inventorySystem.updateStock(product, adjustment);
        System.out.println("Manually adjusted stock for " + product + " by " + adjustment);
    }

    public void requestAudit() {
        System.out.println("Requesting inventory audit due to discrepancies.");
    }

    public void handleOrderFailure() {
        System.out.println("Order failed! Contacting suppliers directly...");
    }

    public void rescheduleDelivery() {
        System.out.println("Rescheduling delayed delivery and informing stakeholders...");
    }

    public void handleDamagedProduct() {
        System.out.println("Handling damaged or incorrect product. Initiating returns/replacements...");
    }
}