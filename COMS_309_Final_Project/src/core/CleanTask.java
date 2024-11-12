package core;

import java.util.ArrayList;

public class CleanTask extends Task {
    public CleanTask(String name, TaskType type, ArrayList<String> taskSteps) {
        super(name, type, taskSteps);
        generateTasklistUI(taskSteps, name);
    }

    @Override
    public void executeTask(String taskCode) {
        if (taskCode.equals("test")) {
            test();
        }
    }

    private void test() {
        System.out.println("your clean task test method was called");
    }
}
