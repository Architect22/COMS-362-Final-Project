package core.Departments;
import core.OrderTicket;
import core.OrderTicketingSystem;
import core.Tasks.RestaurantTask;
import core.Tasks.Task;
import core.Tasks.TaskType;
import core.Utility;
import java.util.ArrayList;
import java.util.Scanner;

public class RestaurantDepartment extends Department {
    private static RestaurantDepartment instance;
    private OrderTicketingSystem ticketingSystem;

    private RestaurantDepartment(String name) {
        super(name);
        this.ticketingSystem = new OrderTicketingSystem();
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
        System.out.println("\n--- Chef Workflow ---");
        while(ticketingSystem.hasOrders()){
            OrderTicket ticket = ticketingSystem.getNextTicket();
            if (ticket != null) {
                System.out.println("Preparing order: " + ticket);

                Scanner scanner = new Scanner(System.in);

                // Check if all ingredients are available
                System.out.print("Are all ingredients available for " + ticket.getDishName() + "? (y/n): ");
                String ingredientsAvailable = scanner.nextLine().toLowerCase();
                if (ingredientsAvailable.equals("n")) {
                    System.out.println("Notifying manager about missing ingredients.");
                }

                // Check if equipment is functional
                System.out.print("Is all kitchen equipment operational? (y/n): ");
                String equipmentCheck = scanner.nextLine().toLowerCase();
                if (equipmentCheck.equals("n")) {
                    System.out.println("Adapting to equipment failure...");
                }

                Utility.displayLoadingAnimationKeepTerminal(3, 300, "Preparing the dish...");
                System.out.println("Dish prepared successfully: " + ticket.getDishName());

                // Perform quality check
                System.out.print("Did the quality check pass? (y/n): ");
                String qualityCheck = scanner.nextLine().toLowerCase();
                if (qualityCheck.equals("n")) {
                    System.out.println("Re-preparing the dish...");
                } else {
                    System.out.println("Dish prepared successfully: " + ticket.getDishName());
                }

                System.out.println("All queued orders prepared.");
            }
        }


    }

    public void serverWorkFlow() {
        Scanner scanner = new Scanner(System.in);
        boolean continueAddingOrders = true;

        System.out.println("\n--- Server Workflow ---");

        System.out.println("Greeting customers and escorting them to their table.");
        System.out.println("Providing menus and explaining today's specials.");

        System.out.print("Is the customer ready to order? (y/n): ");
        String readyToOrder = scanner.nextLine().toLowerCase();
        while (readyToOrder.equals("n")) {
            System.out.println("Customer is not ready to order. Server is waiting...");
            System.out.print("Is the customer ready to order? (y/n): ");
            readyToOrder = scanner.nextLine().toLowerCase();
        }

        while (continueAddingOrders) {
            System.out.print("Enter the dish name to be ordered: ");
            String dishName = scanner.nextLine();

            System.out.print("Any special requests or dietary restrictions? (Enter text or press Enter to skip): ");
            String specialRequest = scanner.nextLine();

            OrderTicket ticket = new OrderTicket(dishName, specialRequest);
            ticketingSystem.submitTicket(ticket);

            System.out.println("Order for " + ticket.getDishName() + " has been submitted to the kitchen.");
            System.out.print("Would you like to add another order for this party? (y/n): ");
            String addMoreOrders = scanner.nextLine().toLowerCase();
            if (!addMoreOrders.equals("y")) {
                continueAddingOrders = false;
            }
        }

        System.out.println("Order has been submitted to the kitchen.");
        Utility.displayLoadingAnimationKeepTerminal(2, 500, "Waiting for kitchen to complete the order...");
        System.out.println("Order is ready for pickup. Delivering to the customer.");

        System.out.print("Is the customer satisfied with the order? (y/n): ");
        String customerSatisfaction = scanner.nextLine().toLowerCase();
        if (customerSatisfaction.equals("n")) {
            System.out.println("Customer reports an issue with the dish. Apologizing and contacting the kitchen for resolution.");
        } else {
            System.out.println("Customer is satisfied with the dish.");
        }

        System.out.println("Providing the bill to the customer.");
        System.out.print("Did the payment go through successfully? (y/n): ");
        String paymentCheck = scanner.nextLine().toLowerCase();
        if (paymentCheck.equals("n")) {
            System.out.println("Payment issue occurred. Addressing the issue discreetly with the customer.");
        }
        else {
            System.out.println("Payment processed successfully. Thanking the customer for dining.");
        }
    }
}
