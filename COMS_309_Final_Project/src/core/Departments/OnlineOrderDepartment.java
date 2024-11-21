package core.Departments;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import core.Utility;
import core.Tasks.OnlineOrderTask;
import core.Tasks.Task;
import core.Tasks.TaskType;

public class OnlineOrderDepartment extends Department {
    private static OnlineOrderDepartment instance;

    private OnlineOrderDepartment(String name) {
        super(name);
    }

    public static OnlineOrderDepartment getInstance() {
        if (instance == null) {
            instance = new OnlineOrderDepartment("Online Order Department");
        }
        return instance;
    }

    @Override
    public void displayDepartmentTasks() {
        Utility.createHeader(width, "Online Order Department");
        ArrayList<String> tasks = new ArrayList<>();
        tasks.add("Prepare Online Order");
        tasks.add("Deliver Order to Curbside");
        Task task = new OnlineOrderTask("Online Order Tasks", TaskType.ONLINE_ORDER, tasks);
    }

    public void prepareAndDeliverOrder() {
        Scanner scanner = new Scanner(System.in);
        HashMap<String, Integer> order = new HashMap<>();
        boolean addingItems = true;

        System.out.println("Preparing an online order...");

        // Step 1: Add items to the order
        while (addingItems) {
            System.out.print("Enter the item name: ");
            String item = scanner.nextLine();

            System.out.print("Enter the quantity: ");
            int quantity = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            order.put(item, quantity);

            System.out.print("Add more items? (y/n): ");
            String response = scanner.nextLine().toLowerCase();
            if (response.equals("n")) {
                addingItems = false;
            }
        }

        // Simulate preparation time
        Utility.displayLoadingAnimation(3, 500, "Preparing the order...");
        System.out.println("Order has been prepared: " + order.entrySet().toString());

        // Step 2: Deliver to curbside
        System.out.print("Is the customer ready for curbside pickup? (y/n): ");
        String ready = scanner.nextLine().toLowerCase();
        if (ready.equals("y")) {
            Utility.displayLoadingAnimation(2, 500, "Delivering order to curbside...");
            System.out.println("Order successfully delivered to curbside pickup!");
        } else {
            System.out.println("Waiting for customer to arrive...");
        }
    }
}
