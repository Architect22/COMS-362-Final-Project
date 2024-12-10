package core.Departments;

import java.util.ArrayList;
import java.util.HashMap;

import core.Utility;
import core.Tasks.ProduceTask;
import core.Tasks.Task;
import core.Tasks.TaskType;

public class ProduceDepartment extends Department {
    public static ProduceDepartment instance;

    public static HashMap<String, Boolean> produceItems = new HashMap();

    private ProduceDepartment(String name) {
        super(name);
        produceItems.put("Apple", false);
        produceItems.put("Bannana", false);
        produceItems.put("Pear", false);
        produceItems.put("Orange", false);
        produceItems.put("Carrot", false);
        produceItems.put("Pepper", false);
        produceItems.put("Onion", false);
        produceItems.put("Brocolli", false);
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
