package core;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Task {
    private static final int width = 65;
    public String name;
    public boolean isComplete;
    public TaskType type;
    public Task taskObject;
    private ArrayList<String> taskSteps;

    public Task(String name, TaskType type, ArrayList<String> taskSteps) {
        this.name = name;
        this.type = type;
        this.isComplete = false;
        this.taskSteps = taskSteps;
        this.taskObject = this;
        generateTasklistUI(taskSteps, name);
    }

    public void generateTasklistUI(ArrayList<String> steps, String taskGroupTitle) {
        taskSteps = steps;
        Utility.createHeader(width, taskGroupTitle);
        for (int i = 0; i < steps.size(); ++i) {
            String line = ("|" + (i + 1) + ". " + steps.get(i));
            line += Utility.addPadding(width - line.length() - (2)); // the number is to account for the number label at
            line += "|";
            System.out.println(line);
        }
        System.out.println("|--------------------------------------------------------------|");
        userInput();
    }

    private void userInput() {
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
                        executeTask(index);
                        break;
                }

            } else {
                System.out.println("That is not a valid step!");
            }

        }
    }

    public void executeTask(int taskCode) {
        System.out.println("Task Completed!");
    }
}