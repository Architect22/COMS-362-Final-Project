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
}