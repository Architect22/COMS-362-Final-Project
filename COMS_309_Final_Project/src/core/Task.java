package core;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Task {
    private static final int width = 65;
    static ArrayList<String> taskSteps;

    public static void generateTasklistUI(ArrayList<String> steps, String taskGroupTitle) {
        taskSteps = steps;
        int padding = (width - taskGroupTitle.length()) / 2;
        System.out.println("|--------------------------------------------------------------|");
        int endPadding;
        if (taskGroupTitle.length() % 2 == 0) {
            endPadding = padding - 2;
        } else {
            endPadding = padding - 3;
        }

        System.out.println("|" + addPadding(padding) + taskGroupTitle.toUpperCase() + addPadding(endPadding) + "|");
        for (int i = 0; i < steps.size(); ++i) {
            String line = ("|" + (i + 1) + ". " + steps.get(i));
            line += addPadding(width - line.length() - (2)); // the number is to account for the number label at the
            line += "|";
            System.out.println(line);
        }
        System.out.println("|--------------------------------------------------------------|");
        userInput();
    }

    private static String addPadding(int amount) {
        String text = "";
        for (int i = 0; i < amount; ++i) {
            text += " ";
        }
        return text;
    }

    private static void userInput() {
        boolean loop = true;
        Scanner in = new Scanner(System.in); // don't close otherwise loop scanner breaks in storefront class!
        while (loop) {

            System.out.println("Please enter the number of the step you would like to complete (enter -1 to quit): ");
            int index = in.nextInt();
            if (index <= taskSteps.size()) {
                switch (index) {
                    case -1:
                        loop = false;
                        break;
                    default:
                        System.out.println("Step Completed!");
                        break;
                }
            } else {
                System.out.println("That is not a valid step!");
            }

        }

    }
}
