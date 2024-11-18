package core.Departments;

import java.util.Scanner;

import core.Utility;

public class Department {
    String name;
    public static int width = 65;

    public Department(String name) {
        this.name = name;
        displayDepartmentDashboard();
    }

    public static void displayDepartmentDashboard() {
        Utility.createHeader(width, "Departments");
        System.out.println("| 1. Floral Department            |");
        System.out.println("| 2. Meat Department              |");
        System.out.println("|---------------------------------|");
        acceptInput();
    }

    public void displayDepartmentTasks() {
        Utility.createHeader(width, "DepartmentTasks");
        System.out.println("|---------------------------------|");
    }

    private static void acceptInput() {
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();

        if (input.equals("1") || input.toLowerCase().equals("floral department")) {

        } else if (input.equals("2") || input.toLowerCase().equals("meat department")) {

        }
        displayDepartmentDashboard();
    }
}
