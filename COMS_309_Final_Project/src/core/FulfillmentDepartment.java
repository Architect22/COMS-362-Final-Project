package core;

import core.Departments.Department;
import core.Tasks.Task;
import core.Tasks.TaskType;
import java.util.ArrayList;

public class FulfillmentDepartment extends Department {
    private static FulfillmentDepartment instance;

    public FulfillmentDepartment(String name) {
        super(name);
    }

    public static FulfillmentDepartment getInstance() {
        if (instance == null) {
            instance = new FulfillmentDepartment("Fulfillment Department");
        }
        return instance;
    }

    @Override
    public void displayDepartmentTasks() {
        Utility.createHeader(width, "Fulfillment Department");
        ArrayList<String> tasks = new ArrayList<>();
        tasks.add("Log into Terminal System");
        tasks.add("Display Pending Online Orders");
        tasks.add("Select an Order to Fulfill");
        tasks.add("Display Order Details (Customer Name, Order Number, Item Locations)");
        tasks.add("Retrieve Items from Store Shelves and Mark as Ready For Pickup");
        tasks.add("Notify Customer Order is Ready For Pickup");
        tasks.add("Customer Arrives, Employee Packs Loads Order");
        tasks.add("Order Marked as Picked Up and Updated in System");
        Task task = new FulfillmentTask("Fulfillment Tasks", TaskType.FULFILLMENT, tasks);
    }

    
}
