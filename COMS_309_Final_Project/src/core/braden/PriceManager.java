package core.braden;

import core.Tasks.Task;
import core.Tasks.TaskType;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PriceManager extends Task {

    public PriceManager(String name, TaskType type, ArrayList<String> taskSteps) {
        super(name, type, taskSteps);
        generateTasklistUI(taskSteps, name);
    }

    @Override
    public void executeTask(int taskCode) {
        if (taskCode == 1) {
            InventorySystem.getInstance().priceUpdateProcess();
        } else {
            System.out.print("Task Complete!");
        }
    }

}
