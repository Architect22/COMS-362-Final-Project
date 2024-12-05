package core.Departments;

import java.util.ArrayList;

import core.Utility;
import core.Tasks.ProduceTask;
import core.Tasks.Task;
import core.Tasks.TaskType;

public class ProduceDepartment extends Department {
    public static ProduceDepartment instance;

    private ProduceDepartment(String name) {
        super(name);
    }

    public static ProduceDepartment getInstance() {
        if (instance == null) {
            instance = new ProduceDepartment("Produce Department");
        }
        return instance;
    }

    @Override
    public void displayDepartmentTasks() {
        Utility.createHeader(width, "Produce Department");
        ArrayList<String> tasks = new ArrayList<>();
        tasks.add("Label Produce");
        Task task = new ProduceTask("Produce Tasks", TaskType.PRODUCE, tasks);
    }
}
