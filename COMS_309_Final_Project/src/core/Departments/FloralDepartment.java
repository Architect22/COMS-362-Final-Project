package core.Departments;

import core.Utility;

public class FloralDepartment extends Department {
    public FloralDepartment(String name) {
        super(name);
    }

    @Override
    public void displayDepartmentTasks() {
        Utility.createHeader(width, "Floral Department");
        System.out.println("| 1. Handle Customer              |");
        System.out.println("|---------------------------------|");
    }

    private void handleCustomer() {

    }
}
