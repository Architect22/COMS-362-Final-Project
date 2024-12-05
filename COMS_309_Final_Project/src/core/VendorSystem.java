package core;

import core.braden.InventorySystem;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class VendorSystem {
    private Scanner in = new Scanner(System.in);
    private InventorySystem inventorySystem = InventorySystem.getInstance();
    private boolean loggedIn = false;
    private List<String> vendors = new ArrayList<>();


    public boolean logIn() {
        System.out.println("Username: ");
        String user = in.nextLine().trim();
        System.out.println("Password: ");
        String pass = in.nextLine().trim();

        if (authenticateUser(user, pass)) {
            System.out.println("Login successful.");
            this.loggedIn = true;
            return true;
        } else {
            System.out.println("Login failed.");
            this.loggedIn = false;
            return false;
        }
    }

    private boolean authenticateUser(String username, String password) {
        
        if (!username.equalsIgnoreCase("vendorlog") || !password.equals("vendor123")) {
            return false;
        }
        return true;
    }

    ///////////////////////////////////////////////////////////////////////

    public void displayPendingVendorDeliveries() {
        System.out.println("Displaying list of pending vendor deliveries...");
        
        try (BufferedReader reader = new BufferedReader(new FileReader("vendor_deliveries.csv"))) {
            String line;
            
            // Read and skip the header line
            reader.readLine();  
            
            boolean hasPendingDeliveries = false; 
            
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
    
                // Check if the row has the correct number of columns
                if (parts.length == 7) {
                    String vendorName = parts[0].trim();
                    String deliveryDescription = parts[2].trim();
                    String status = parts[6].trim();  // Status column
    
                    // Display only pending deliveries
                    if (status.equalsIgnoreCase("Pending")) {
                        hasPendingDeliveries = true;
                        System.out.println("Vendor: " + vendorName + " | Delivery: " + deliveryDescription);
                    }
                }
            }
            
            if (!hasPendingDeliveries) {
                System.out.println("No pending vendor deliveries available.");
            }
    
        } catch (IOException e) {
            System.err.println("Error reading the CSV file: " + e.getMessage());
        }
    }
    

    ///////////////////////////////////////////////////////////////////////////////

    // Main function to test the system


    public static class Delivery {
        private String deliveryID;
        private String description;
        private String product;
        private int quantity;
        private String deliveryTime;
        private String status;

        // Constructor matching your usage
        public Delivery(String deliveryID, String description, String product, int quantity, String deliveryTime, String status) {
            this.deliveryID = deliveryID;
            this.description = description;
            this.product = product;
            this.quantity = quantity;
            this.deliveryTime = deliveryTime;
            this.status = status;
        }

        public String getProduct() {
            return product;
        }

        public int getQuantity() {
            return quantity;
        }

        public String getDeliveryTime() {
            return deliveryTime;
        }

    }

    ////////////////////////////////////////////////////////////////////////

    public void loadVendorsFromCSV(String fileName) {
        vendors.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            reader.readLine(); // Skip header row
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 7) {
                    String vendorName = parts[0].trim();
                    if (!vendors.contains(vendorName)) {
                        vendors.add(vendorName);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading the CSV file: " + e.getMessage());
        }
    }

    public void displayVendors() {
        System.out.println("Please select a vendor to inspect their shipment:");
        for (int i = 0; i < vendors.size(); i++) {
            System.out.println((i + 1) + ". " + vendors.get(i));
        }
    }

    public void checkVendorShipment() {
        loadVendorsFromCSV("vendor_deliveries.csv");
        if (vendors.isEmpty()) {
            System.out.println("No vendors found in the system.");
            return;
        }

        displayVendors();
        try {
            int choice = in.nextInt();
            in.nextLine(); // Clear buffer

            if (choice >= 1 && choice <= vendors.size()) {
                String selectedVendor = vendors.get(choice - 1);
                System.out.println("Inspecting shipment for " + selectedVendor + "...");
                if (checkShipmentUponArrival()) {
                    System.out.println("Shipment for " + selectedVendor + " is in good condition.");
                } else {
                    System.out.println("Shipment for " + selectedVendor + " is damaged. Contacting vendor...");
                }
            } else {
                System.out.println("Invalid selection. Please try again.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number.");
            in.nextLine(); // Clear buffer
        }
    }

    public boolean checkShipmentUponArrival() {
        boolean isShipmentDamaged = simulateShipmentInspection();
        return !isShipmentDamaged;
    }

    private boolean simulateShipmentInspection() {
        return Math.random() < 0.1; // 10% chance shipment is damaged
    }

    ///////////////////////////////////////////////////////////////////////

    public void verifyDeliveredQuantities() {
        System.out.println("Verifying delivered quantities against the purchase order...");
    
        try (BufferedReader reader = new BufferedReader(new FileReader("vendor_deliveries.csv"))) {
            String line;
            reader.readLine(); // Skip header line
            
            // List to hold pending shipments
            List<String> pendingShipments = new ArrayList<>();
            
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 7) {
                    String vendorName = parts[0].trim();
                    String deliveryID = parts[1].trim();
                    String product = parts[3].trim();
                    int deliveredQuantity = Integer.parseInt(parts[4].trim());
                    String status = parts[6].trim();  // Assuming the 7th column has the delivery status
    
                    // Only include pending deliveries
                    if ("pending".equalsIgnoreCase(status)) {
                        pendingShipments.add(deliveryID + " - " + vendorName + " - " + product + " - " + deliveredQuantity);
                    }
                }
            }
    
            // Display the available pending shipments
            if (pendingShipments.isEmpty()) {
                System.out.println("No pending shipments to verify.");
                return;
            }
    
            System.out.println("Select a shipment to verify:");
            for (int i = 0; i < pendingShipments.size(); i++) {
                System.out.println((i + 1) + ". " + pendingShipments.get(i));
            }
    
            // Get user input for shipment selection
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter the number of the shipment to verify: ");
            int selectedIndex = scanner.nextInt() - 1;
    
            // Validate user selection
            if (selectedIndex < 0 || selectedIndex >= pendingShipments.size()) {
                System.out.println("Invalid selection.");
                return;
            }
    
            // Get the selected shipment details
            String selectedShipment = pendingShipments.get(selectedIndex);
            String[] shipmentDetails = selectedShipment.split(" - ");
            String selectedVendor = shipmentDetails[1];
            String selectedProduct = shipmentDetails[2];
            int deliveredQuantity = Integer.parseInt(shipmentDetails[3]);
    
            // Display the vendor and delivered quantity to the user
            System.out.println("Vendor: " + selectedVendor);
            System.out.println("Product: " + selectedProduct);
            System.out.println("Delivered Quantity: " + deliveredQuantity);
            
            // Ask the user if the quantities match
            System.out.print("Does the quantity match the expected amount? (y/n): ");
            scanner.nextLine();  // Consume the newline character left by nextInt
            String response = scanner.nextLine();
    
            // Check if the user approved or rejected the delivery
            if ("y".equalsIgnoreCase(response)) {
                System.out.println("Delivery approved.");
            } else {
                System.out.println("Delivery not good. Discrepancy detected.");
            }
    
        } catch (IOException e) {
            System.err.println("Error reading the vendor deliveries file: " + e.getMessage());
        }
    }
    
    ///////////////////////////////////////////////////////////////////////

    public void manageVendorDeliveries() {
        InventorySystem invSystem = InventorySystem.getInstance();

        Scanner in = new Scanner(System.in);
        System.out.println("Vendor Delivery Management:");
        System.out.println("1. Review Pending Deliveries");
        System.out.println("2. Reconcile Delivered Quantities");
        System.out.println("3. Approve or Reject Deliveries");
        System.out.println("4. Send Vendor Details");

        System.out.print("Select an option: ");
        int option = in.nextInt();
        in.nextLine(); // consume newline

        switch (option) {
            case 1:
                invSystem.reviewPendingDeliveries();
                break;
            case 2:
                invSystem.reconcileDeliveries();
                break;
            case 3:
                invSystem.approveOrRejectDeliveries();
                break;
            case 4:
                invSystem.sendVendorDetails();
                break;
            default:
                System.out.println("Invalid option. Returning to main menu.");
                break;
        }
    }

    ///////////////////////////////////////////////////////////////////////

    public void sendConfirmationToVendor() {
        System.out.println("Sending automated confirmation to the vendor...");
        // Simulate sending confirmation (e.g., email, system update)
        System.out.println("Confirmation sent successfully to the vendor.");
    }

    ///////////////////////////////////////////////////////////////////////


    public ArrayList<String> getPendingDeliveries() {

        ArrayList<String> deliveries = new ArrayList<>();

        deliveries.add("PepsiCo - Weekly Beverage Replenishment");
        deliveries.add("Tyson Foods - Poultry and Meat Delivery");
        deliveries.add("Nestlé - Packaged Foods and Beverages Delivery");
        deliveries.add("FreshPoint - Produce Shipment");
        deliveries.add("Bimbo Bakeries - Bread and Baked Goods Delivery");

        return deliveries;
    }

    public boolean selectVendorAndReviewDetails(String vendorName) {
        // Retrieve details for the selected vendor
        // Placeholder for actual logic to retrieve vendor delivery details
        System.out.println("Reviewing delivery details for: " + vendorName);
        
        // Assume the delivery has been reviewed successfully
        return true;
    }

    public boolean verifyQuantities(String vendorName, ArrayList<String> deliveredItems) {
        // Retrieve expected quantities from the purchase order
        ArrayList<String> expectedItems = getExpectedItems(vendorName);
        
        // Check if quantities match
        for (String item : deliveredItems) {
            if (!expectedItems.contains(item)) {
                System.out.println("Discrepancy found in quantities, contacting vendor.");
                return false;  // Discrepancy found
            }
        }
        
        System.out.println("Quantities match.");
        return true;  // Quantities match
    }
    
    private ArrayList<String> getExpectedItems(String vendorName) {
        // Dummy expected items for a specific vendor
        ArrayList<String> items = new ArrayList<>();
        items.add("Item 1");
        items.add("Item 2");
        return items;
    }

    public boolean approveDelivery(String deliveryID) {
        // Assuming quantities were already verified
        System.out.println("Delivery " + deliveryID + " approved.");
        return true;  // Return true if delivery is approved
    }

    public void updateInventory(String vendorName, ArrayList<String> deliveredItems) {
        // Logic to update inventory levels
        System.out.println("Updating inventory with delivered items from " + vendorName);
        // Add the items to inventory (dummy logic)
        for (String item : deliveredItems) {
            System.out.println("Added " + item + " to inventory.");
        }
    }
    
    public void markOrderAsReconciled(String deliveryID) {
        // Mark order as reconciled in the system
        System.out.println("Order " + deliveryID + " marked as reconciled.");
    }

    public void sendConfirmationToVendor(String vendorName) {
        // Send automated confirmation of delivery
        System.out.println("Sending confirmation to " + vendorName);
    }

    public void handleLateDelivery(String deliveryID) {
        // Notify vendor and update delivery status
        System.out.println("Delivery " + deliveryID + " is late. Notifying vendor.");
        updateDeliveryStatus(deliveryID, "Late");
    }
    
    private void updateDeliveryStatus(String deliveryID, String status) {
        // Update the status of the delivery in the system
        System.out.println("Updated delivery " + deliveryID + " status to " + status);
    }

    public void handleDiscrepancy(String deliveryID) {
        // Contact vendor support for reconciliation
        System.out.println("Quantities do not match for delivery " + deliveryID + ". Contacting vendor support.");
        // Assuming you contact support here
    }

    public void handleDamagedOrExpiredItems(String deliveryID) {
        // Document the issue and notify the vendor
        System.out.println("Damaged or expired items identified in delivery " + deliveryID + ". Notifying vendor.");
        // Assuming you notify the vendor here
    }

    ///////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////


    public void viewPendingDeliveries() {
        System.out.println("Fetching pending deliveries...");
        // Logic for viewing pending deliveries
    }

    public void registerNewVendor() {
        System.out.print("Enter vendor name: ");
        String vendorName = in.nextLine();
        System.out.println("Vendor " + vendorName + " registered successfully!");
        // Logic for registering a new vendor
    }

    public void restockInventory() {
        System.out.print("Enter product name: ");
        String product = in.nextLine();
        System.out.print("Enter quantity to restock: ");
        int quantity = in.nextInt();
        in.nextLine();  // Consume newline

        inventorySystem.updateStock(product, quantity);
        System.out.println("Restocked " + quantity + " units of " + product + ".");
    }

    public void generateVendorReport() {
        System.out.println("Generating vendor report...");
        // Logic for generating vendor reports
    }
}
