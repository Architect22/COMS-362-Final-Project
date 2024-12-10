package core.Departments;
import core.Tasks.RestaurantTask;
import core.Tasks.Task;
import core.Tasks.TaskType;
import core.Utility;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class RestaurantDepartment extends Department {
    private static RestaurantDepartment instance;

    private RestaurantDepartment(String name) {
        super(name);
    }

    public static RestaurantDepartment getInstance() {
        if (instance == null) {
            instance = new RestaurantDepartment("Restaurant Department");
        }
        return instance;
    }

    @Override
    public void displayDepartmentTasks() {
        Utility.createHeader(width, "Restaurant Department");
        ArrayList<String> tasks = new ArrayList<>();
        tasks.add("Prepare Customer Orders (Chef)");
        tasks.add("Serve Customers (Server)");
        Task task = new RestaurantTask("Restaurant Department Tasks", TaskType.RESTAURANT, tasks);
    }

    /**
     * This method allows the user to choose between Chef (Back of House) 
     * and Server (Front of House) workflows.
     */
    public void promptUserForDepartmentTask() {
        Scanner scanner = new Scanner(System.in);
        boolean continueTask = true;

        while (continueTask) {
            System.out.println("\n--- Restaurant Department Main Menu ---");
            System.out.println("1. Front of House (Server)");
            System.out.println("2. Kitchen (Chef)");            
            System.out.println("3. Exit");
            System.out.print("Choose an option (1 or 2): ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 3.");
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.println("\n--- Front of House (Server) ---");
                    serverWorkFlow();
                    break;
                case 2:
                    System.out.println("\n--- Kitchen (Chef) ---");
                    chefWorkFlow();
                    break;
                default:
                    System.out.println("Invalid choice. Please select 1 or 2.");
            }
        }
    }

    public void chefWorkFlow() {
        Scanner scanner = new Scanner(System.in);
        HashMap<String, Integer> order = new HashMap<>();

        System.out.println("\n--- Chef Workflow ---");

        // Step 1: Receive the order ticket
        System.out.println("Receiving order ticket from server...");
        System.out.print("Enter the name of the dish to prepare: ");
        String dish = scanner.nextLine();

        // Step 2: Check ingredient availability
        System.out.print("Are all ingredients available? (y/n): ");
        String ingredientCheck = scanner.nextLine().toLowerCase();
        if (ingredientCheck.equals("n")) {
            System.out.println("Ingredient unavailable! Notifying manager and attempting to substitute or modify order.");
        }

        // Step 3: Check equipment status
        System.out.print("Is all kitchen equipment operational? (y/n): ");
        String equipmentCheck = scanner.nextLine().toLowerCase();
        if (equipmentCheck.equals("n")) {
            System.out.println("Kitchen equipment malfunction! Adjusting preparation methods.");
        }

        // Simulate preparation time
        Utility.displayLoadingAnimationKeepTerminal(3, 500, "Preparing the dish...");
        System.out.println("Dish prepared successfully: " + dish);

        // Step 4: Verify the quality
        System.out.print("Did the quality check pass? (y/n): ");
        String qualityCheck = scanner.nextLine().toLowerCase();
        if (qualityCheck.equals("y")) {
            System.out.println("Quality check passed. Placing order in pickup area.");
        } else {
            System.out.println("Quality check failed. Re-preparing the dish.");
        }

        // Step 5: Mark the ticket as complete
        System.out.println("Order ticket for " + dish + " marked as complete.");
    }

    public void serverWorkFlow() {
        Scanner scanner = new Scanner(System.in);
        boolean customerReadyToOrder = true;
        boolean paymentSuccess = true;

        System.out.println("\n--- Server Workflow ---");

        // Step 1: Greet the customer
        System.out.println("Greeting customers and escorting them to their table.");

        // Step 2: Provide menu and take order
        System.out.println("Providing menus and explaining today's specials.");
        System.out.print("Is the customer ready to order? (y/n): ");
        String readyToOrder = scanner.nextLine().toLowerCase();
        while (readyToOrder.equals("n")) {
            System.out.println("Customer is not ready to order. Server is waiting...");
            System.out.print("Is the customer ready to order? (y/n): ");
            readyToOrder = scanner.nextLine().toLowerCase();
            customerReadyToOrder = false;
        }
        customerReadyToOrder = true;

        // Step 3: Take the customer's order
        if (customerReadyToOrder) {
            System.out.print("Enter the dish name to be ordered: ");
            String order = scanner.nextLine();
            System.out.print("Any special requests or dietary restrictions? (y/n): ");
            String specialRequest = scanner.nextLine().toLowerCase();
            if (specialRequest.equals("y")) {
                System.out.println("Server notes the special request on the order.");
            }
            System.out.println("Order for " + order + " has been submitted to the kitchen.");
        }

        // Step 4: Pick up and serve the order
        Utility.displayLoadingAnimationKeepTerminal(2, 500, "Waiting for kitchen to complete the order...");
        System.out.println("Order is ready for pickup. Delivering to the customer.");

        // Step 5: Check in with the customer
        System.out.print("Is the customer satisfied with the order? (y/n): ");
        String customerSatisfaction = scanner.nextLine().toLowerCase();
        if (customerSatisfaction.equals("n")) {
            System.out.println("Customer reports an issue with the dish. Apologizing and contacting the kitchen for resolution.");
        } else {
            System.out.println("Customer is satisfied with the dish.");
        }

        // Step 6: Payment and checkout
        System.out.println("Providing the bill to the customer.");
        System.out.print("Did the payment go through successfully? (y/n): ");
        String paymentCheck = scanner.nextLine().toLowerCase();
        if (paymentCheck.equals("n")) {
            System.out.println("Payment issue occurred. Addressing the issue discreetly with the customer.");
            paymentSuccess = false;
        }

        if (paymentSuccess) {
            System.out.println("Payment processed successfully. Thanking the customer for dining.");
        }
    }
}
