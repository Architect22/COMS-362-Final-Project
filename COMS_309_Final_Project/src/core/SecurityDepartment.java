package core;


import core.Departments.Department;


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
        Utility.createHeader(width, "Security Department");
        System.out.println("|--------------------------------------------------------------|");
        System.out.println("| 1. Display Security Options                                  |");
        System.out.println("|--------------------------------------------------------------|");
    }

    
}
