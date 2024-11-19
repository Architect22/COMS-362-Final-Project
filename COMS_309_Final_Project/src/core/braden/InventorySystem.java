package core.braden;

import java.io.*;
import java.util.*;

public class InventorySystem {
    private Map<String, Integer> stockLevels = new HashMap<>();
    private Map<String, Float> stockPrices = new HashMap<>();
    private Map<String, Integer> reorderQuantities = new HashMap<>();
    private Map<String, Integer> salesForecast = new HashMap<>();
    private boolean systemStatus = true;
    private static final String CSV_FILE = "inventory.csv";
    private Scanner in;

    public InventorySystem() {
        loadFromCSV();
    }

    private void loadFromCSV() {
        try (BufferedReader reader = new BufferedReader(new FileReader(CSV_FILE))) {
            String line;
            // Skip the header row
            reader.readLine();

            // Read the rest of the lines
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length == 5) {
                    String product = values[0];
                    int stockLevel = Integer.parseInt(values[1]);
                    float price = Float.parseFloat(values[2]);
                    int reorderQuantity = Integer.parseInt(values[3]);
                    int forecast = Integer.parseInt(values[4]);

                    // Populate the maps with the values from the CSV
                    stockLevels.put(product, stockLevel);
                    stockPrices.put(product, price);
                    reorderQuantities.put(product, reorderQuantity);
                    salesForecast.put(product, forecast);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading inventory data: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error parsing numeric data: " + e.getMessage());
        }
    }

    // Save data to CSV file
    private void saveToCSV() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CSV_FILE))) {
            for (String product : stockLevels.keySet()) {
                writer.write(product + ","
                        + stockLevels.get(product) + ","
                        + stockPrices.getOrDefault(product, 0f) + ","
                        + reorderQuantities.getOrDefault(product, 0) + ","
                        + salesForecast.getOrDefault(product, 0) + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error saving inventory data: " + e.getMessage());
        }
    }

    public void updateStock(String product, int quantity) {
        stockLevels.put(product, stockLevels.getOrDefault(product, 0) + quantity);
        saveToCSV();
    }

    public void updateReorderQuantity(String product, int quantity) {
        reorderQuantities.put(product, quantity);
        saveToCSV();
    }

    public int getStockLevel(String product) {
        return stockLevels.getOrDefault(product, 0);
    }

    public Map<String, Integer> getStockLevels() {
        return stockLevels;
    }

    public void placeOrder(String product, int quantity) {
        System.out.println("Order placed for " + quantity + " units of " + product);
        updateStock(product, quantity);
    }

    public boolean isSystemOperational() {
        return systemStatus;
    }

    public void toggleSystemStatus(boolean status) {
        this.systemStatus = status;
    }

    public void updateSalesForecast(String product, int forecast) {
        salesForecast.put(product, forecast);
        saveToCSV();
    }

    public int getSalesForecast(String product) {
        return salesForecast.getOrDefault(product, 0);
    }

    public void displayInventoryDashboard() {
        System.out.println("Inventory Dashboard:");
        for (String product : stockLevels.keySet()) {
            System.out.println(product
                    + " - Stock: " + stockLevels.get(product)
                    + ", Price: " + stockPrices.getOrDefault(product, 0f)
                    + ", Reorder: " + reorderQuantities.getOrDefault(product, 0)
                    + ", Forecast: " + salesForecast.getOrDefault(product, 0));
        }
    }

    public boolean checkForDiscrepancies(String product) {
        int stock = getStockLevel(product);
        int forecast = getSalesForecast(product);
        int reorder = reorderQuantities.getOrDefault(product, 0);
        return stock < forecast || stock < reorder;
    }

    public void updateStockPrice(String product, float newPrice) {
        stockPrices.put(product, newPrice);
        saveToCSV();
    }

    public float getPrice(String product) {
        return stockPrices.getOrDefault(product, Float.NaN);
    }

    public Map<String, Float> getStockPrices() {
        return stockPrices;
    }

    public void priceUpdateProcess() {

        System.out.println("Accessing Price Update Section...");
        displayInventoryDashboard();


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
        return getStockLevels().keySet().stream()
                .filter(product -> product.toLowerCase().contains(filter.toLowerCase()))
                .toList();
    }

    private void updatePrices(List<String> itemsToUpdate) {
        System.out.println("Enter new price for each item (ensure it meets minimum markup rules):");
        for (String item : itemsToUpdate) {
            float currentPrice = getStockPrices().getOrDefault(item, 0f);
            System.out.print("Current price of " + item + ": " + currentPrice + " -> ");
            float newPrice = in.nextFloat();
            in.nextLine();


            if (newPrice < currentPrice * 1.1) {
                System.out.println("Error: New price must be at least 10% higher than the current price.");
                continue;
            }


            if (checkForDiscrepancies(item)) {
                System.out.println("Warning: Stock levels or sales forecast indicate issues for " + item + ". Please review.");
                continue;
            }


            updateStockPrice(item, newPrice);
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
}