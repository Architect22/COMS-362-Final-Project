package core;

import core.Departments.Department;
import core.Tasks.Task;
import core.Tasks.TaskType;
import java.util.ArrayList;

public class PharmacyDepartment extends Department{
    public static PharmacyDepartment instance;

    private PharmacyDepartment(String name) {
        super(name);
    }

    public static PharmacyDepartment getInstance() {
        if (instance == null) {
            instance = new PharmacyDepartment("Pharmacy Department");
        }
        return instance;
    }

    @Override
    public void displayDepartmentTasks() {
        ArrayList<String> steps = new ArrayList<>();
        Utility.createHeader(width, "Pharmacy Department");
        System.out.println("|--------------------------------------------------------------|");
        steps.add("Greet the Customer");
        steps.add("Collect Prescription Details");
        steps.add("Retrieve Medication");
        steps.add("Process Payment");
        steps.add("Hand Medication to the Customer");
        steps.add("Handle Customer Departure");
        Task task = new PharmacyTask("Pharmacy Task", TaskType.PHARMACY, steps);
    }

}
