package core.Tasks;

import core.Departments.RestaurantDepartment;
import java.util.ArrayList;

public class RestaurantTask extends Task {
    public RestaurantTask(String name, TaskType type, ArrayList<String> taskSteps) {
        super(name, type, taskSteps);
        generateTasklistUI(taskSteps, name);
    }

    @Override
    public void executeTask(int taskCode) {
        if (taskCode == 1) {
            RestaurantDepartment.getInstance().promptUserForDepartmentTask();
        }
    }

}
