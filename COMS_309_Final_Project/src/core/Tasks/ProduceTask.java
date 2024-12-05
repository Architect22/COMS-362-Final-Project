package core.Tasks;

import java.util.ArrayList;

public class ProduceTask extends Task {
    public ProduceTask(String name, TaskType type, ArrayList<String> taskSteps) {
        super(name, type, taskSteps);
        generateTasklistUI(taskSteps, name);
    }

    @Override
    public void executeTask(int taskCode) {
        if (taskCode == 1) {
        }
    }
}
