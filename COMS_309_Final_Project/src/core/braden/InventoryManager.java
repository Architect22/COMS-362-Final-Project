package core.braden;

import core.Tasks.Task;
import core.Tasks.TaskType;

import java.util.ArrayList;
import java.util.Scanner;

public class InventoryManager extends Task {

    public InventoryManager(String name, TaskType type, ArrayList<String> taskSteps) {
        super(name, type, taskSteps);
        generateTasklistUI(taskSteps, name);
    }

    @Override
    public void executeTask(int taskCode) {
        if (taskCode == 1) {
            viewInventoryDashboard();
        } else if (taskCode == 2) {
            adjustReorderQuantities();
        } else if (taskCode == 3) {
            placeOrder();
        } else if (taskCode == 4) {
            checkStockDiscrepancies();
        } else if (taskCode == 5) {
            manuallyAdjustStock();
        } else if (taskCode == 6) {
            requestAudit();
        } else if (taskCode == 7) {
            handleOrderFailure();
        } else if (taskCode == 8) {
            rescheduleDelivery();
        } else if (taskCode == 9) {
            handleDamagedProduct();
        } else {
            System.out.print("Task Complete!");
        }
    }

    public void viewInventoryDashboard() {
        if (!InventorySystem.getInstance().isSystemOperational()) {
            handleDashboardFailure();
        } else {
            InventorySystem.getInstance().displayInventoryDashboard();
            checkForAlerts();
        }
    }

    private void handleDashboardFailure() {
        System.out.println("Dashboard failed to load. Attempting to reload or contact IT...");
    }

    private void checkForAlerts() {
        for (String product : InventorySystem.getInstance().getStockLevels().keySet()) {
            if (InventorySystem.getInstance().checkForDiscrepancies(product)) {
                System.out.println("Alert: Discrepancy detected for " + product);
            }
        }
    }

    @SuppressWarnings("resource")
    public void adjustReorderQuantities() {
        System.out.print("Enter the product to adjust reorder quantity: ");
        String product = new Scanner(System.in).nextLine();
        System.out.print("Enter the new reorder quantity: ");
        int quantity = Integer.parseInt(new Scanner(System.in).nextLine());
        InventorySystem.getInstance().updateReorderQuantity(product, quantity);
        System.out.println("Reorder quantity for " + product + " adjusted to " + quantity);
    }

    @SuppressWarnings("resource")
    public void placeOrder() {
        System.out.print("Enter the product to order: ");
        String product = new Scanner(System.in).nextLine();
        System.out.print("Enter the quantity to order: ");
        int quantity = Integer.parseInt(new Scanner(System.in).nextLine());
        InventorySystem.getInstance().placeOrder(product, quantity);
    }

    @SuppressWarnings("resource")
    public void checkStockDiscrepancies() {
        System.out.print("Enter product to check for discrepancies: ");
        String product = new Scanner(System.in).nextLine();
        System.out.println("Checking for discrepancies in stock for " + product + "...");
        checkForAlerts();
    }

    @SuppressWarnings("resource")
    public void manuallyAdjustStock() {
        System.out.print("Enter product to manually adjust stock: ");
        String product = new Scanner(System.in).nextLine();
        System.out.print("Enter the adjustment amount (positive or negative): ");
        int adjustment = Integer.parseInt(new Scanner(System.in).nextLine());
        InventorySystem.getInstance().updateStock(product, adjustment);
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