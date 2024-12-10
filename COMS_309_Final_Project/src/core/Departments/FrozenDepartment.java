package core.Departments;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

import core.Tasks.FrozenAndDairyTask;
import core.Utility;
import core.Tasks.FloralTask;
import core.Tasks.Task;
import core.Tasks.TaskType;

public class FrozenDepartment extends Department {
    private static FrozenDepartment instance;

    private FrozenDepartment(String name) {
        super(name);
    }

    public static FrozenDepartment getInstance() {
        if (instance == null) {
            instance = new FrozenDepartment("Frozen Department");
        }
        return instance;
    }



    @Override
    public void displayDepartmentTasks() {
        Utility.createHeader(width, "Frozen Department");
        ArrayList<String> tasks = new ArrayList<>();
        tasks.add("Check the dairy cooler temperature");
        tasks.add("Inspect dairy products for damage");
        tasks.add("Ensure proper organization and storage of dairy items");
        tasks.add("Check the freezer temperature");
        tasks.add("Inspect frozen items for damage or freezer burn");
        tasks.add("Remove damaged or expired items from both sections");
        tasks.add("Contact suppliers for returns or replacements");
        tasks.add("Adjust storage conditions if necessary");
        tasks.add("Document the inspection results");
        tasks.add("Discuss findings with the purchasing team");
        tasks.add("If the Cooler temperature is incorrect, adjust the settings");
        tasks.add("If the Freezer temperature is incorrect, adjust the settings");
        tasks.add("If damaged products are found, contact suppliers for returns and remove from inventory");
        tasks.add("If the cooler/freezer is malfunctioning, call maintenance");
        Task task = new FrozenAndDairyTask("Frozen And Dairy Task", TaskType.FROZENANDDAIRY, tasks);
    }

}
