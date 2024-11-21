package core.Departments;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Random;
import core.Utility;
import core.Tasks.ReceiptVerificationTask;
import core.Tasks.Task;
import core.Tasks.TaskType;

public class ReceiptVerificationDepartment extends Department {
    private static ReceiptVerificationDepartment instance;

    private ReceiptVerificationDepartment(String name) {
        super(name);
    }

    public static ReceiptVerificationDepartment getInstance() {
        if (instance == null) {
            instance = new ReceiptVerificationDepartment("Receipt Verification Department");
        }
        return instance;
    }

    @Override
    public void displayDepartmentTasks() {
        Utility.createHeader(width, "Receipt Verification Department");
        ArrayList<String> tasks = new ArrayList<>();
        tasks.add("Verify Customer Receipt");
        tasks.add("Resolve Item Discrepancies");
        Task task = new ReceiptVerificationTask("Receipt Verification Tasks", TaskType.RECEIPT_VERIFICATION, tasks);
    }

    public void verifyReceiptAndItems() {
        Scanner scanner = new Scanner(System.in);
        HashMap<String, Integer> items = new HashMap<>();
        Random random = new Random();

        System.out.println("Starting receipt verification...");

        // Step 1: Simulate receipt details
        System.out.println("Retrieving receipt details...");
        items.put("Apple", random.nextInt(5) + 1);
        items.put("Milk", random.nextInt(2) + 1);
        items.put("Bread", random.nextInt(3) + 1);
        Utility.displayLoadingAnimation(2, 500, "Loading receipt...");

        System.out.println("Receipt details retrieved: " + items);

        // Step 2: Verify items
        boolean discrepancy = random.nextBoolean();
        if (!discrepancy) {
            System.out.println("Items match the receipt. Verification complete!");
        } else {
            System.out.println("Discrepancy found! Initiating resolution process...");
            resolveDiscrepancy(scanner, items);
        }
    }

    private void resolveDiscrepancy(Scanner scanner, HashMap<String, Integer> items) {
        System.out.println("Items in question: " + items);

        System.out.print("Would you like to contact a supervisor for further review? (y/n): ");
        String response = scanner.nextLine().toLowerCase();

        if (response.equals("y")) {
            Utility.displayLoadingAnimation(2, 500, "Contacting supervisor...");
            System.out.println("Supervisor has been contacted and is resolving the issue.");
        } else {
            System.out.println("Customer directed to service desk for resolution.");
        }
    }
}
