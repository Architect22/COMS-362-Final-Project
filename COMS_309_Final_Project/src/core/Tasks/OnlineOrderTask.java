package core.Tasks;

import java.util.ArrayList;

import core.Departments.OnlineOrderDepartment;

public class OnlineOrderTask extends Task {
    public OnlineOrderTask(String name, TaskType type, ArrayList<String> taskSteps) {
        super(name, type, taskSteps);
        generateTasklistUI(taskSteps, name);
    }

    @Override
    public void executeTask(int taskCode) {
        if (taskCode == 1) {
            OnlineOrderDepartment.getInstance().prepareAndDeliverOrder();
        }
    }

}
