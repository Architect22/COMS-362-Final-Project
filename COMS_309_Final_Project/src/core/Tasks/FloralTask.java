package core.Tasks;

import java.util.ArrayList;

import core.Departments.FloralDepartment;

public class FloralTask extends Task {
    public FloralTask(String name, TaskType type, ArrayList<String> taskSteps) {
        super(name, type, taskSteps);
        generateTasklistUI(taskSteps, name);
    }

    @Override
    public void executeTask(int taskCode) {
        if (taskCode == 1) {
            FloralDepartment.getInstance().handleCustomerOrder();
        }
    }

}
