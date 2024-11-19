package core.Departments;

import core.Utility;

public class MeatDepartment extends Department {
    public static MeatDepartment instance;

    private MeatDepartment(String name) {
        super(name);
    }

    public static MeatDepartment getInstance() {
        if (instance == null) {
            instance = new MeatDepartment("Meat Department");
        }
        return instance;
    }

    @Override
    public void displayDepartmentTasks() {
        Utility.createHeader(width, "Meat Department");
        System.out.println("| 1. Handle Customer                                           |");
        System.out.println("|--------------------------------------------------------------|");
    }

    private void handleCustomer() {

    }
}