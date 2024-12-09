package core.braden;

import core.Tasks.Task;
import core.Tasks.TaskType;

import java.util.ArrayList;
import java.util.Scanner;

public class ManageBackroomTask extends Task {

    public ManageBackroomTask(String name, TaskType type, ArrayList<String> taskSteps) {
        super(name, type, taskSteps);
        generateTasklistUI(taskSteps, name);
    }

    @Override
    @SuppressWarnings("resource")
    public void executeTask(int taskCode) {
        if (taskCode == 1) {
            InventorySystem.getInstance().displayInventoryDashboard();
        } else if (taskCode == 8) {
            InventorySystem.getInstance().priceUpdateProcess();
        } else if (taskCode == 9) {
            System.out.print("Enter product to liquidate: ");
            String product = new Scanner(System.in).nextLine();
            System.out.print("Enter the adjustment amount");
            int adjustment = Integer.parseInt(new Scanner(System.in).nextLine());
            InventorySystem.getInstance().updateStock(product, adjustment);
            System.out.println("Manually adjusted stock for " + product + " by " + adjustment);
        } else {
            System.out.print("Task Complete!");
        }
    }
}
