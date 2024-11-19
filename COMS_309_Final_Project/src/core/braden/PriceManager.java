package core.braden;

import core.Task;
import core.TaskType;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PriceManager extends Task {
    private InventorySystem inventorySystem = new InventorySystem();

    public PriceManager(String name, TaskType type, ArrayList<String> taskSteps) {
        super(name, type, taskSteps);
        generateTasklistUI(taskSteps, name);
    }

    @Override
    public void executeTask(int taskCode) {
        if(taskCode == 1){
            inventorySystem.priceUpdateProcess();
        }else {
            System.out.print("Task Complete!");
        }
    }

}
