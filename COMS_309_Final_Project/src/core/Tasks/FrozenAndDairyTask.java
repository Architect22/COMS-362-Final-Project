package core.Tasks;

import core.Departments.FloralDepartment;
import core.braden.InventorySystem;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class FrozenAndDairyTask extends Task {
    public FrozenAndDairyTask(String name, TaskType type, ArrayList<String> taskSteps) {
        super(name, type, taskSteps);
        generateTasklistUI(taskSteps, name);
    }
    Random random = new Random();
    int CoolerTemp= 35 + random.nextInt(11);
    int FreezerTemp = 15 + random.nextInt(11);


    @Override
    public void executeTask(int taskCode) {
        if (taskCode == 1) {
            System.out.println("Cooler Temp:"+CoolerTemp);
        }  if (taskCode == 4) {
            System.out.println("Freezer Temp:"+FreezerTemp);
        } else if (taskCode == 11 ) {
            System.out.print("Enter temp to manually adjust Cooler: ");
            int adjustment = Integer.parseInt(new Scanner(System.in).nextLine());
            CoolerTemp = adjustment;
            System.out.println("Cooler Temp:"+CoolerTemp);
        } else if (taskCode == 12) {
            System.out.print("Enter temp to manually adjust Freezer: ");
            int adjustment = Integer.parseInt(new Scanner(System.in).nextLine());
            FreezerTemp = adjustment;
            System.out.println("Freezer Temp:"+FreezerTemp);
        } else if (taskCode == 13 | taskCode == 14) {
            InventorySystem.getInstance().displayInventoryDashboard();
            System.out.print("Enter product to manually adjust stock: ");
            String product = new Scanner(System.in).nextLine();
            System.out.print("Enter the adjustment amount");
            int adjustment = Integer.parseInt(new Scanner(System.in).nextLine());
            InventorySystem.getInstance().updateStock(product, adjustment);
            System.out.println("Manually adjusted stock for " + product + " by " + adjustment);
        }else {
            System.out.println("Task Complete!");
        }
    }

}
