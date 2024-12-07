package core;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FulfillmentSystem {
    private String CSV_FILE = "/Users/endiodobasic/iowa_state/c-362/COMS-362-Final-Project/COMS_309_Final_Project/src/core/pending_fulfillment_order.csv";
    private String validPassword = "fulfillmyneeds123";
    private String validUsername = "fulfillmentuser";
    public static FulfillmentSystem instance;
    private List<Order> allOrders;
    private String orderId;
    private Scanner in;

    public static FulfillmentSystem getInstance() {
        if (instance == null) {
            instance = new FulfillmentSystem();
        }
        return instance;
    }

    public FulfillmentSystem() {
        this.in = new Scanner(System.in);
        this.allOrders = new ArrayList<>();

    }

    public void logIntoSystem() {

        System.out.print("Enter username: ");
        String username = in.nextLine();

        System.out.print("Enter password: ");
        String password = in.nextLine();

        if (username.equals(validUsername) && password.equals(validPassword)) {
            System.out.println("Success: Logged into the system.");
        } else {
            System.out.println("Failed: Invalid username or password.");
        }
    }

    public void displayPendingOrders() {
        System.out.println("Displaying pending fulfillment orders...");
        try (BufferedReader reader = new BufferedReader(new FileReader(CSV_FILE))) {
            String line;
            boolean headerSkipped = false;
            while ((line = reader.readLine()) != null) {
                if (!headerSkipped) {
                    headerSkipped = true;
                    continue;
                }

                String[] fields = line.split(",");
                String orderId = fields[0].trim();
                String customerName = fields[1].trim();
                String orderStatus = fields[2].trim();
                String items = fields[3].trim();

                Order order = new Order(orderId, customerName, orderStatus, items);
                allOrders.add(order);

                if (orderStatus.equalsIgnoreCase("Pending")) {
                    System.out.println("Order ID: " + orderId);
                    System.out.println("Customer Name: " + customerName);
                    System.out.println("Items: " + items);
                    System.out.println("---------------------------");
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading the CSV file: " + e.getMessage());
        }
    }
    

    public void selectOrderToPrepare() {
        Scanner scanner = new Scanner(System.in);
    
        System.out.println("Displaying pending fulfillment orders...");
        List<Order> pendingOrders = new ArrayList<>();
    
        for (Order order : allOrders) {
            if (order.getOrderStatus().equalsIgnoreCase("Pending")) {
                pendingOrders.add(order);
                System.out.println("Order ID: " + order.getOrderId());
                System.out.println("Customer Name: " + order.getCustomerName());
                System.out.println("Items: " + order.getItems());
                System.out.println("---------------------------");
            }
        }
    
        if (pendingOrders.isEmpty()) {
            System.out.println("No pending orders available.");
            return;
        }
    
        System.out.print("Enter the Order ID of the order you want to prepare: ");
        String selectedOrderId = scanner.nextLine();
    
        Order selectedOrder = null;
        for (Order order : pendingOrders) {
            if (order.getOrderId().equals(selectedOrderId)) {
                selectedOrder = order;
                break;
            }
        }
    
        if (selectedOrder != null) {
            System.out.println("You have selected the following order to prepare:");
            System.out.println(selectedOrder);
    
            selectedOrder.setOrderStatus("In Progress");
            System.out.println("The order status has been updated to: In Progress");
        } else {
            System.out.println("Error: No order found with the given Order ID.");
        }
    }

    public void displayOrderDetails() {    
        System.out.print("Enter the order ID to view details: ");
        String orderIdInput = in.nextLine();
    
        boolean orderFound = false;
    
        for (Order order : allOrders) {
            if (order.getOrderId().equals(orderIdInput)) {
                System.out.println("Order ID: " + order.getOrderId());
                System.out.println("Customer Name: " + order.getCustomerName());
                System.out.println("Order Status: " + order.getOrderStatus());
                System.out.println("Items: " + order.getItems());
                System.out.println("---------------------------");
                orderFound = true;
                System.out.println("Order with ID " + orderIdInput + " should be ready soon...");
                retrieveItems(order.getOrderId(), order.getCustomerName(), order.getItems());
                break; 
            }
        }
    
        if (!orderFound) {
            System.out.println("Order with ID " + orderIdInput + " not found.");
        }
    }



    public void retrieveItems(String id, String name, String numItems) {
        this.orderId = id;
        System.out.println("Retrieving items from store...");
        System.out.println("Order " + id + " complete for " + name + "with " + numItems);
    }

    public void markOrderReadyForPickup() {
        for (Order order : allOrders) {
            if (order.getOrderId().equals(orderId)) {
                order.setOrderStatus("Ready for Pickup");
                System.out.println("Order ID " + orderId + " is now packed and Ready for Pickup.");

                updateCSVFile();

                return;
            }
        }

        System.out.println("Order ID " + orderId + " not found.");
    }

    public void sendNotificationToCustomer() {
        System.out.println("Notification Alert sent out to guest. Order is Ready For Pickup! ");
    }

    public void handleCustomerArrival() {
        System.out.println("Customer has arrived. The employee proceeds to the pickup zone to pack the order into the customer's vehicle.");
        System.out.println("Packing items...");
        System.out.println("The employee thanks the customer and wishes them a pleasant day as they leave.");
    }

    public void markOrderPickedUp() {

        List<String> lines = new ArrayList<>();
        boolean orderFound = false;
    
        try (BufferedReader reader = new BufferedReader(new FileReader(CSV_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading the CSV file: " + e.getMessage());
            return;
        }
    
        for (int i = 1; i < lines.size(); i++) { 
            String[] fields = lines.get(i).split(",");
            String currentOrderId = fields[0].trim();
    
            if (currentOrderId.equals(orderId)) {
                fields[2] = "Picked Up"; 
                lines.set(i, String.join(",", fields)); 
                orderFound = true;
                break;
            }
        }
    
        if (orderFound) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(CSV_FILE))) {
                for (String line : lines) {
                    writer.write(line);
                    writer.newLine();
                }
                System.out.println("Order " + orderId + " has been marked as 'Picked Up'.");
            } catch (IOException e) {
                System.err.println("Error writing to the CSV file: " + e.getMessage());
            }
        } else {
            System.out.println("Order with ID " + orderId + " not found.");
        }
    }
    



    ////////////////////////////
    private void updateCSVFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CSV_FILE))) {
            writer.write("OrderId,CustomerName,OrderStatus,Items\n");

            for (Order order : allOrders) {
                writer.write(order.getOrderId() + "," + order.getCustomerName() + "," 
                            + order.getOrderStatus() + "," + order.getItems() + "\n");
            }
            
        } catch (IOException e) {
            System.err.println("Error updating the CSV file: " + e.getMessage());
        }
    }

}
