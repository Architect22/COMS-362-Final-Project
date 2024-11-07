package core;

import java.util.*;

public class InventorySystem {
    private Map<String, Integer> stockLevels = new HashMap<>();
    private Map<String, Float>  stockPrices = new HashMap<>();
    private Map<String, Integer> reorderQuantities = new HashMap<>();
    private Map<String, Integer> salesForecast = new HashMap<>();
    private boolean systemStatus = true;

    public void updateStock(String product, int quantity) {
        stockLevels.put(product, stockLevels.getOrDefault(product, 0) + quantity);
    }

    public void updateReorderQuantity(String product, int quantity) {
        reorderQuantities.put(product, quantity);
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
                    + ", Reorder: " + reorderQuantities.getOrDefault(product, 0));
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
    }
    
    public float getPrice(String product) {
    	return stockPrices.getOrDefault(product, Float.NaN);
    }

    public Map<String, Float> getStockPrices() {
        return stockPrices;
    }
}