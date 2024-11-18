package core;

import java.util.Scanner;

public class Department {
    String name;
    private static int width = 65;

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

    private static void acceptInput() {
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();

        if (input.equals("1") || input.toLowerCase().equals("floral department")) {

        } else if (input.equals("2") || input.toLowerCase().equals("meat department")) {

        }
    }
}
