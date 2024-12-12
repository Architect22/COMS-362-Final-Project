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

    public void promptUserForDepartmentTask() {
        Scanner scanner = new Scanner(System.in);
        boolean continueTask = true;

        while (continueTask) {
            System.out.println("\n--- Restaurant Department Main Menu ---");
            System.out.println("1. Front of House (Server)");
            System.out.println("2. Kitchen (Chef)");            
            System.out.println("3. Exit");
            System.out.print("Choose an option (1, 2 or 3): ");

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
                case 3:
                    continueTask = false;
                default:
                    System.out.println("Invalid choice. Please select 1 or 2.");
            }
        }
    }

    public void serverWorkFlow() {
        Scanner scanner = new Scanner(System.in);
        seatCustomer();
        checkCustomerReadiness();

        boolean continueAddingOrders = true;
        while (continueAddingOrders) {
            OrderTicket ticket = takeCustomerOrder();
            enterOrderIntoSys(ticket);
            continueAddingOrders = askToAddMoreOrders(scanner);
        }

        waitForKitchen();
        serveFood();
        checkCustomerSatisfaction();
        giveBill();
    }

    private void seatCustomer() {
        System.out.println("\n--- Server Workflow ---");
        System.out.println("Greeting customers and escorting them to their table.");
        System.out.println("Providing menus and explaining today's specials.");
    }

    private void checkCustomerReadiness() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Is the customer ready to order? (y/n): ");
        String readyToOrder = scanner.nextLine().toLowerCase();
        while (readyToOrder.equals("n")) {
            System.out.println("Customer is not ready to order. Server is waiting...");
            System.out.print("Is the customer ready to order? (y/n): ");
            readyToOrder = scanner.nextLine().toLowerCase();
        }
    }

    private OrderTicket takeCustomerOrder() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the dish name to be ordered: ");
        String dishName = scanner.nextLine();

        System.out.print("Any special requests or dietary restrictions? (Enter text or press Enter to skip): ");
        String specialRequest = scanner.nextLine();

        return new OrderTicket(dishName, specialRequest);
    }

    private void enterOrderIntoSys(OrderTicket ticket) {
        ticketingSystem.submitTicket(ticket);
        System.out.println("Order for " + ticket.getDishName() + " has been submitted to the kitchen.");
    }

    private boolean askToAddMoreOrders(Scanner scanner) {
        System.out.print("Would you like to add another order for this party? (y/n): ");
        String response = scanner.nextLine().toLowerCase();
        return response.equals("y");
    }

    private void waitForKitchen() {
        Utility.displayLoadingAnimationKeepTerminal(2, 500, "Waiting for kitchen to complete the order...");
    }

    private void serveFood() {
        System.out.println("Order is ready for pickup. Delivering to the customer.");
    }

    private void checkCustomerSatisfaction() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Is the customer satisfied with the order? (y/n): ");
        String satisfaction = scanner.nextLine().toLowerCase();
        if (satisfaction.equals("n")) {
            System.out.println("Customer reports an issue with the dish. Apologizing and contacting the kitchen for resolution.");
        } else {
            System.out.println("Customer is satisfied with the dish.");
        }
    }

    private void giveBill() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Providing the bill to the customer.");
        System.out.print("Did the payment go through successfully? (y/n): ");
        String paymentCheck = scanner.nextLine().toLowerCase();
        if (paymentCheck.equals("n")) {
            System.out.println("Payment issue occurred. Addressing the issue discreetly with the customer.");
        } else {
            System.out.println("Payment processed successfully. Thanking the customer for dining.");
        }
    }
    
    public void chefWorkFlow() {
        System.out.println("\n--- Chef Workflow ---");

        while (ticketingSystem.hasOrders()) {
            OrderTicket ticket = ticketingSystem.getNextTicket();
            if (ticket != null) {
                System.out.println("Preparing order: " + ticket);

                boolean ingredientsAvailable = checkIngredients(ticket);
                if (!ingredientsAvailable) {
                    notifyManagerForIngredients(ticket);
                }

                boolean equipmentOperational = checkKitchenEquip();
                if (!equipmentOperational) {
                    handleEquipmentFailure();
                }

                boolean qualityCheckPassed = cookFoodAndSendOut(ticket);
                if (!qualityCheckPassed) {
                    reSubmitTicket(ticket);
                } else {
                    System.out.println("Dish prepared successfully: " + ticket.getDishName());
                }
            }
        }

        System.out.println("All orders have been prepared. No more tickets in the queue.");
    }

    private boolean checkIngredients(OrderTicket ticket) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Are all ingredients available for " + ticket.getDishName() + "? (y/n): ");
        String response = scanner.nextLine().toLowerCase();
        return response.equals("y");
    }

    private void notifyManagerForIngredients(OrderTicket ticket) {
        System.out.println("Notifying manager about missing ingredients for " + ticket.getDishName() + ".");
    }

    private boolean checkKitchenEquip() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Is all kitchen equipment operational? (y/n): ");
        String response = scanner.nextLine().toLowerCase();
        return response.equals("y");
    }

    private void handleEquipmentFailure() {
        System.out.println("Adapting to equipment failure...");
    }

    private boolean cookFoodAndSendOut(OrderTicket ticket) {
        Utility.displayLoadingAnimationKeepTerminal(3, 300, "Preparing " + ticket.getDishName() + "...");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Did the quality check pass for " + ticket.getDishName() + "? (y/n): ");
        String response = scanner.nextLine().toLowerCase();
        return response.equals("y");
    }

    private void reSubmitTicket(OrderTicket ticket) {
        System.out.println("Re-preparing the dish: " + ticket.getDishName());
        ticketingSystem.submitTicket(ticket);
    }

    
}
