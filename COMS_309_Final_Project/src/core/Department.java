package core;

public class Department {
    String name;
    private static int width = 65;

    public Department(String name) {
        this.name = name;
        displayDepartmentDashboard();
    }

    public void displayDepartmentDashboard() {
        Utility.createHeader(width, name);
    }
}
