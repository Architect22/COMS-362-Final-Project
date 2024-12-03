package core.Departments;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
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
        int amount = 0;
        while (selecting) {
            System.out.print("Enter the flower for display: ");
            String flower = inString.nextLine();

            if (!hasStock()) {
                System.out.println("We don't have that in stock. Would you like to select a replacement?: (y/n)");
                String response = inString.nextLine().toLowerCase();
                if (response.equals("y")) {
                    continue;
                } else {
                    flower = "";
                }
            } else {
                System.out.print("Enter the number of this flower in display: ");
                amount = inInt.nextInt();
            }

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

    private boolean hasStock() {
        Random rand = new Random();
        int check = rand.nextInt(2);
        if (check == 0) {
            return true;
        } else {
            return false;
        }
    }
}
