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


    public void displayPendingVendorDeliveries() {
        System.out.println("Displaying list of pending vendor deliveries...");
        
        try (BufferedReader reader = new BufferedReader(new FileReader("vendor_deliveries.csv"))) {
            String line;
            
            reader.readLine();  
            
            boolean hasPendingDeliveries = false; 
            
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
    
                if (parts.length == 7) {
                    String vendorName = parts[0].trim();
                    String deliveryDescription = parts[2].trim();
                    String status = parts[6].trim(); 
    
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

    public static class Delivery {
        private String deliveryID;
        private String description;
        private String product;
        private int quantity;
        private String deliveryTime;
        private String status;

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


    public void loadVendorsFromCSV(String fileName) {
        vendors.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            reader.readLine();
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
            in.nextLine();

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
            in.nextLine();
        }
    }

    public boolean checkShipmentUponArrival() {
        boolean isShipmentDamaged = simulateShipmentInspection();
        return !isShipmentDamaged;
    }

    private boolean simulateShipmentInspection() {
        return Math.random() < 0.1;
    }

    public void verifyDeliveredQuantities() {
        System.out.println("Verifying delivered quantities against the purchase order...");
    
        try (BufferedReader reader = new BufferedReader(new FileReader("vendor_deliveries.csv"))) {
            String line;
            reader.readLine(); 
            

            List<String> pendingShipments = new ArrayList<>();
            
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 7) {
                    String vendorName = parts[0].trim();
                    String deliveryID = parts[1].trim();
                    String product = parts[3].trim();
                    int deliveredQuantity = Integer.parseInt(parts[4].trim());
                    String status = parts[6].trim(); 
    
                    if ("pending".equalsIgnoreCase(status)) {
                        pendingShipments.add(deliveryID + " - " + vendorName + " - " + product + " - " + deliveredQuantity);
                    }
                }
            }
    
            if (pendingShipments.isEmpty()) {
                System.out.println("No pending shipments to verify.");
                return;
            }
    
            System.out.println("Select a shipment to verify:");
            for (int i = 0; i < pendingShipments.size(); i++) {
                System.out.println((i + 1) + ". " + pendingShipments.get(i));
            }
    
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter the number of the shipment to verify: ");
            int selectedIndex = scanner.nextInt() - 1;
    
            if (selectedIndex < 0 || selectedIndex >= pendingShipments.size()) {
                System.out.println("Invalid selection.");
                return;
            }
    
            String selectedShipment = pendingShipments.get(selectedIndex);
            String[] shipmentDetails = selectedShipment.split(" - ");
            String selectedVendor = shipmentDetails[1];
            String selectedProduct = shipmentDetails[2];
            int deliveredQuantity = Integer.parseInt(shipmentDetails[3]);
    
            System.out.println("Vendor: " + selectedVendor);
            System.out.println("Product: " + selectedProduct);
            System.out.println("Delivered Quantity: " + deliveredQuantity);
            
            System.out.print("Does the quantity match the expected amount? (y/n): ");
            scanner.nextLine(); 
            String response = scanner.nextLine();
    
            if ("y".equalsIgnoreCase(response)) {
                System.out.println("Delivery approved.");
            } else {
                System.out.println("Delivery not good. Discrepancy detected.");
            }
    
        } catch (IOException e) {
            System.err.println("Error reading the vendor deliveries file: " + e.getMessage());
        }
    }
    
    public void manageVendorDeliveries() {
        InventorySystem invSystem = InventorySystem.getInstance();

        Scanner in = new Scanner(System.in);
        System.out.println("Vendor Delivery Management:");
        System.out.println("1. Review All Deliveries");
        System.out.println("2. Reconcile Delivered Quantities");
        System.out.println("3. Approve or Reject Deliveries");
        System.out.println("4. Send Vendor Details");

        System.out.print("Select an option: ");
        int option = in.nextInt();
        in.nextLine();

        switch (option) {
            case 1:
                invSystem.reviewAllDeliveries();
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

    public void sendConfirmationToVendor() {
        System.out.println("Sending automated confirmation to the vendor...");
        System.out.println("Confirmation sent successfully to the vendor.");
    }

}
