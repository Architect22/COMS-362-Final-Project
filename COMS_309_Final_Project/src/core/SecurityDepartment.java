package core;


import core.Departments.Department;
import core.Tasks.Task;
import core.Tasks.TaskType;
import java.util.ArrayList;


public class SecurityDepartment extends Department {
    public static SecurityDepartment instance;
    public static SecurityManager securityPersonnel;
    public static SecurityTask securityTasks;

    
    public SecurityDepartment(String name) {
        super(name);
    }

    public static SecurityDepartment getInstance() {
        if (instance == null) {
            instance = new SecurityDepartment("Security Department");
        }
        return instance;
    }

    @Override
    public void displayDepartmentTasks() {
        ArrayList<String> steps = new ArrayList<>();
        Utility.createHeader(width, "Security Department");
        System.out.println("|--------------------------------------------------------------|");
		steps.add("Monitor Live Camera Feeds ");
		steps.add("Review Alerts or Suspicious Events");
		steps.add("Patrol the Store");
		steps.add("Check Alarm and Sensor Status");
		steps.add("Respond to Active Incident");
		steps.add("Document Incident Details");
		steps.add("Communicate with Other Personnel");
		Task task = new SecurityTask("Security Personnel", TaskType.SECURITY, steps);
    }

    
}
