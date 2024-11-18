package core.braden;

import core.Tasks.Task;
import core.Tasks.TaskType;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PriceManager extends Task {

    private Scanner in;
    private InventorySystem inventorySystem = new InventorySystem();

    public PriceManager(String name, TaskType type, ArrayList<String> taskSteps) {
        super(name, type, taskSteps);
        generateTasklistUI(taskSteps, name);
    }

    @Override
    public void executeTask(int taskCode) {
        if (taskCode == 1) {
            priceUpdateProcess();
        } else {
            System.out.print("Task Complete!");
        }
    }

    public void priceUpdateProcess() {

        System.out.println("Accessing Price Update Section...");
        inventorySystem.displayInventoryDashboard();

        System.out.print("Enter filter criteria (category, supplier, or promo): ");
        String filter = in.nextLine();
        List<String> filteredItems = filterItems(filter);

        if (filteredItems.isEmpty()) {
            System.out.println("No items found with the given filter. Please try again with different criteria.");
            return;
        }

        System.out.println("Filtered items:");
        for (String item : filteredItems) {
            System.out.println(item);
        }

        System.out.println("Do you want to update prices for these items? (y/n)");
        String response = in.nextLine();

        if (response.equalsIgnoreCase("y")) {
            updatePrices(filteredItems);
        } else {
            System.out.println("Price update cancelled.");
        }
    }

    private List<String> filterItems(String filter) {
        return inventorySystem.getStockLevels().keySet().stream()
                .filter(product -> product.toLowerCase().contains(filter.toLowerCase()))
                .toList();
    }

    private void updatePrices(List<String> itemsToUpdate) {
        System.out.println("Enter new price for each item (ensure it meets minimum markup rules):");
        for (String item : itemsToUpdate) {
            float currentPrice = inventorySystem.getStockPrices().getOrDefault(item, 0f);
            System.out.print("Current price of " + item + ": " + currentPrice + " -> ");
            float newPrice = in.nextFloat();
            in.nextLine();

            if (newPrice < currentPrice * 1.1) {
                System.out.println("Error: New price must be at least 10% higher than the current price.");
                continue;
            }

            if (inventorySystem.checkForDiscrepancies(item)) {
                System.out.println(
                        "Warning: Stock levels or sales forecast indicate issues for " + item + ". Please review.");
                continue;
            }

            inventorySystem.updateStockPrice(item, newPrice);
            System.out.println("Price for " + item + " updated to: " + newPrice);
        }

        applyPriceChanges();
        generatePriceChangeReport();
    }

    private void applyPriceChanges() {
        System.out.println("Prices have been updated successfully on shelves and POS system.");
    }

    private void generatePriceChangeReport() {
        System.out.println("Generating report of price changes...");
        System.out.println("Price changes report sent to manager's email.");
    }

    public void updateTage() {
        System.out.println("Updating price tags on floor.");
    }

}
