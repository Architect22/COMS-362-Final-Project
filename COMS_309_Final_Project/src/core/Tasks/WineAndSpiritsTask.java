package core.Tasks;

import core.Departments.FloralDepartment;
import core.braden.InventorySystem;

import java.util.ArrayList;
import java.util.Scanner;

public class WineAndSpiritsTask extends Task {
    public WineAndSpiritsTask(String name, TaskType type, ArrayList<String> taskSteps) {
        super(name, type, taskSteps);
        generateTasklistUI(taskSteps, name);
    }

    @Override
    public void executeTask(int taskCode) {
        if (taskCode == 1) {
            InventorySystem.getInstance().displayInventoryDashboard();
        } else if (taskCode == 2 | taskCode == 10){
            System.out.print("Enter the product to order: ");
            String product = new Scanner(System.in).nextLine();
            System.out.print("Enter the quantity to order: ");
            int quantity = Integer.parseInt(new Scanner(System.in).nextLine());
            InventorySystem.getInstance().placeOrder(product, quantity);
        } else if ( taskCode == 12 | taskCode == 13) {
            System.out.print("Enter product to manually adjust stock: ");
            String product = new Scanner(System.in).nextLine();
            System.out.print("Enter the adjustment amount");
            int adjustment = Integer.parseInt(new Scanner(System.in).nextLine());
            InventorySystem.getInstance().updateStock(product, adjustment);
            System.out.println("Manually adjusted stock for " + product + " by " + adjustment);
        }else {
            System.out.print("Task Complete!\n");
        }
    }

}
