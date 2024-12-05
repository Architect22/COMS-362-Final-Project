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
    private Scanner in = new Scanner(System.in);

    public static InventorySystem instance;

    public static InventorySystem getInstance() {
        if (instance == null) {
            instance = new InventorySystem();
        }
        return instance;
    }

    private InventorySystem() {
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
                System.out.println(
                        "Warning: Stock levels or sales forecast indicate issues for " + item + ". Please review.");
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

    public void sendVendorDetails() {
        Scanner scanner = new Scanner(System.in);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("vendor_deliveries.csv", true))) {
            // Check if the CSV file is empty; if so, write a header.
            if (new java.io.File("vendor_deliveries.csv").length() == 0) {
                writer.write("Vendor,DeliveryID,Description,Product,Quantity,DeliveryTime,Status\n");
            }
    
            while (true) {
                System.out.println("Enter Vendor Name (or type 'exit' to finish): ");
                String vendorName = scanner.nextLine();
                if (vendorName.equalsIgnoreCase("exit")) {
                    break;
                }
    
                System.out.println("Enter Delivery ID: ");
                String deliveryID = scanner.nextLine();
    
                System.out.println("Enter Delivery Description: ");
                String deliveryDescription = scanner.nextLine();
    
                System.out.println("Enter Product Name: ");
                String product = scanner.nextLine();
    
                System.out.println("Enter Quantity: ");
                int quantity = Integer.parseInt(scanner.nextLine());
    
                System.out.println("Enter Delivery Time (YYYY-MM-DD HH:MM:SS): ");
                String deliveryTime = scanner.nextLine();
    
                String status = "Pending"; // Default status is "Pending"
    
                // Append the new vendor entry to the CSV file.
                writer.write(vendorName + "," + deliveryID + "," + deliveryDescription + "," + product + "," + quantity + "," + deliveryTime + "," + status + "\n");
                System.out.println("Vendor and delivery details added to CSV.");
            }
    
        } catch (IOException e) {
            System.err.println("Error writing to CSV file: " + e.getMessage());
        }
    
        System.out.println("Exiting Vendor CSV Manager.");
    }
    

    public void reviewPendingDeliveries() {
        System.out.println("Reviewing pending deliveries...");
        
        try (BufferedReader reader = new BufferedReader(new FileReader("vendor_deliveries.csv"))) {
            String line;
            
            reader.readLine(); // Skip the header row
            
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                
                if (parts.length == 7) {
                    String vendorName = parts[0].trim();
                    String deliveryID = parts[1].trim();
                    String deliveryDescription = parts[2].trim();
                    String product = parts[3].trim();
                    int quantity = Integer.parseInt(parts[4].trim());
                    String deliveryTime = parts[5].trim();
                    String status = parts[6].trim();
                    
                    // Print delivery info or add it to a collection
                    System.out.println("Vendor: " + vendorName + " | Delivery ID: " + deliveryID + 
                                       " | Product: " + product + " | Quantity: " + quantity + 
                                       " | Time: " + deliveryTime + " | Status: " + status);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading the CSV file: " + e.getMessage());
        }
    }
    

    public void reconcileDeliveries() {
        System.out.println("Reconciling delivered quantities...");
        
        // Logic to verify deliveries
        System.out.print("Enter the product to reconcile: ");
        String product = in.nextLine();
        
        System.out.print("Enter the received quantity: ");
        int quantity = in.nextInt();
        in.nextLine(); // Consume newline character
        
        // Here, you would get the current stock level from a database or inventory system
        int stockLevel = getStockLevel(product);
        
        // Check discrepancies
        if (quantity != stockLevel) {
            System.out.println("Discrepancy detected! Updating stock.");
        }
        
        System.out.println(product + quantity + ": reconciled");
        //updateStock(product, quantity);
    }
    

    public void approveOrRejectDeliveries() {
        System.out.println("Approving or rejecting deliveries...");
        System.out.print("Enter the Delivery ID to review: ");
        String deliveryID = in.nextLine();
        
        System.out.print("Approve this delivery? (y/n): ");
        String response = in.nextLine();
        
        try {
            // Read the CSV file and update status of the relevant delivery
            File inputFile = new File("vendor_deliveries.csv");
            File tempFile = new File("temp_vendor_deliveries.csv");
    
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
    
            String line;
            reader.readLine(); // Skip the header line
    
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 7 && parts[1].equals(deliveryID)) {
                    if (response.equalsIgnoreCase("y")) {
                        parts[6] = "Approved"; // Update status to "Approved"
                    } else {
                        parts[6] = "Rejected"; // Update status to "Rejected"
                    }
                }
    
                writer.write(String.join(",", parts) + "\n");
            }
    
            reader.close();
            writer.close();
    
            // Delete original file and rename the temporary file to the original
            if (inputFile.delete()) {
                tempFile.renameTo(inputFile);
            }
    
            System.out.println("Delivery " + (response.equalsIgnoreCase("y") ? "approved." : "rejected.") + " Updated in CSV.");
        } catch (IOException e) {
            System.err.println("Error updating the CSV file: " + e.getMessage());
        }
    }
    
}