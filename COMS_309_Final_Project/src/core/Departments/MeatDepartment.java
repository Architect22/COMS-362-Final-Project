package core.Departments;

import java.util.HashMap;
import java.util.Scanner;

import core.Utility;

public class MeatDepartment extends Department {
    private static MeatDepartment instance;

    private MeatDepartment(String name) {
        super(name);
    }

    public static MeatDepartment getInstance() {
        if (instance == null) {
            instance = new MeatDepartment("Meat Department");
        }
        return instance;
    }

    @Override
    public void displayDepartmentTasks() {
        Utility.createHeader(width, "Meat Department");
        System.out.println("| 1. Handle Customer                                           |");
        System.out.println("|--------------------------------------------------------------|");
        handleCustomerOrder();
    }

    private void handleCustomerOrder() {
        Scanner inString = new Scanner(System.in);
        Scanner inFloat = new Scanner(System.in);
        HashMap<String, Float> order = new HashMap<>();
        boolean selecting = true;
        while (selecting) {
            System.out.print("Enter the meat for you want: ");
            String meat = inString.nextLine();

            System.out.print("Enter the amount (in lbs) for this meat: ");
            float amount = inFloat.nextFloat();

            System.out.print("Is that all: (y/n)");
            String quit = inString.nextLine();

            if (quit.equals("y")) {
                selecting = false;
            }
            order.put(meat, amount);
        }
        Utility.displayLoadingAnimation(1, 500, "Getting your order ready...");
        Utility.displayLoadingAnimation(1, 500, "Wrapping meat...");
        Utility.displayLoadingAnimation(1, 500, "Tying up package...");
        System.out.println("Your order of " + order.entrySet().toString() + " was successfully created!");
    }
}