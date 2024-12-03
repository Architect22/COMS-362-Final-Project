package core.Departments;

import java.util.ArrayList;

import core.Utility;
import core.Tasks.FloralTask;
import core.Tasks.Task;
import core.Tasks.TaskType;

public class BakeryDepartment extends Department {
    public static BakeryDepartment instance;

    private BakeryDepartment(String name) {
        super(name);
    }

    public static BakeryDepartment getInstance() {
        if (instance == null) {
            instance = new BakeryDepartment("Bakery Department");
        }
        return instance;
    }

    @Override
    public void displayDepartmentTasks() {
        Utility.createHeader(width, "Bakery Department");
        ArrayList<String> tasks = new ArrayList<>();
        tasks.add("Bake Daily Menu");
        // Task task = new BakeryTask("Bakery Tasks", TaskType.BAKERY, tasks);
    }

    public void BakeItem() {

    }
}
