package core.Departments;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import core.Utility;
import core.Tasks.FloralTask;
import core.Tasks.Task;
import core.Tasks.TaskType;

public class FloralDepartment extends Department {
    private static FloralDepartment instance;

    private FloralDepartment(String name) {
        super(name);
    }

    public static FloralDepartment getInstance() {
        if (instance == null) {
            instance = new FloralDepartment("Floral Department");
        }
        return instance;
    }

    @Override
    public void displayDepartmentTasks() {
        Utility.createHeader(width, "Floral Department");
        ArrayList<String> tasks = new ArrayList<>();
        tasks.add("Handle Customer Order");
        Task task = new FloralTask("Floral Tasks", TaskType.FLORAL, tasks);
    }

    public void handleCustomerOrder() {
        Scanner inString = new Scanner(System.in);
        Scanner inInt = new Scanner(System.in);
        HashMap<String, Integer> display = new HashMap<>();
        boolean selecting = true;
        while (selecting) {
            System.out.print("Enter the flower for display: ");
            String flower = inString.nextLine();

            System.out.print("Enter the number of this flower in display: ");
            int amount = inInt.nextInt();

            System.out.print("Is that all: (y/n)");
            String quit = inString.nextLine();

            if (quit.equals("y")) {
                selecting = false;
            }
            display.put(flower, amount);
        }
        Utility.displayLoadingAnimation(3, 500, "Creating your floral display...");
        System.out.println("Your order of " + display.entrySet().toString() + " was successfully created!");
    }
}
