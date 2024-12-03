package core.Tasks;

import java.util.ArrayList;

import core.Departments.BakeryDepartment;

public class BakeryTask extends Task {
    public BakeryTask(String name, TaskType type, ArrayList<String> taskSteps) {
        super(name, type, taskSteps);
        generateTasklistUI(taskSteps, name);
    }

    @Override
    public void executeTask(int taskCode) {
        if (taskCode == 1) {
            BakeryDepartment.getInstance().BakeItem();
        }
    }
}
