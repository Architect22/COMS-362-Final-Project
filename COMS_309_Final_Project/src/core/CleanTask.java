package core;

import java.util.ArrayList;

public class CleanTask extends Task {
    public CleanTask(String name, TaskType type, ArrayList<String> taskSteps) {
        super(name, type, taskSteps);
        generateTasklistUI(taskSteps, name);
    }

    @Override
    public void executeTask(int taskCode) {
        if (taskCode == 1) {
            test();
        }
    }

    private void test() {
        System.out.println("your clean task test method was called");
    }
}
