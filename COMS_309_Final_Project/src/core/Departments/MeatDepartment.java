package core.Departments;

import java.util.HashMap;
import java.util.Random;
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

    @SuppressWarnings("resource")
    private void handleCustomerOrder() {
        Scanner inString = new Scanner(System.in);
        Scanner inFloat = new Scanner(System.in);
        HashMap<String, Float> order = new HashMap<>();
        boolean selecting = true;
        float amount = 0;
        while (selecting) {
            System.out.print("Enter the meat for you want: ");
            String meat = inString.nextLine();

            if (!hasStock()) {
                System.out.println("We don't have that in stock. Would you like to select a replacement?: (y/n)");
                String response = inString.nextLine().toLowerCase();
                if (response.equals("y")) {
                    continue;
                }
            } else {
                System.out.print("Enter the amount (in lbs) for this meat: ");
                amount = inFloat.nextFloat();
            }

            System.out.print("Is that all: (y/n)");
            String quit = inString.nextLine();

            if (quit.equals("y")) {
                selecting = false;
            }
            order.put(meat, amount);
        }
        Utility.displayLoadingAnimation(1, 500, "Getting your order ready...");
        if (scaleBroken()) {
            System.out.println(
                    "The scale has broken! Calling maintenance. Your order will be charged at the default price");
        } else {
            Utility.displayLoadingAnimation(1, 500, "Wrapping meat...");
            Utility.displayLoadingAnimation(1, 500, "Tying up package...");
        }
        System.out.println("Your order of " + order.entrySet().toString() + " was successfully created!");
    }

    private boolean scaleBroken() {
        Random rand = new Random();

        int check = rand.nextInt(1);
        if (check == 0) {
            return false;
        } else {
            return true;
        }
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