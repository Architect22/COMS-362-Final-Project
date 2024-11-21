package core.Departments;

import core.PharmacyDepartment;
import core.SecurityDepartment;
import core.Utility;
import java.util.Scanner;

public class Department {
    String name;
    public static int width = 65;

    public Department(String name) {
        this.name = name;
    }

    public static void displayDepartmentDashboard() {
        Utility.createHeader(width, "Departments");
        System.out.println("| 1. Floral Department                                         |");
        System.out.println("| 2. Meat Department                                           |");
        System.out.println("| 3. Security Department                                       |");
        System.out.println("| 4. Pharmacy Department                                           |");
        System.out.println("|--------------------------------------------------------------|");
        acceptInput();
    }

    public void displayDepartmentTasks() {
        Utility.createHeader(width, "DepartmentTasks");
        System.out.println("|--------------------------------------------------------------|");
    }

    private static void acceptInput() {
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();

        if (input.equals("1") || input.toLowerCase().equals("floral department")) {
            FloralDepartment floral = FloralDepartment.getInstance();
            floral.displayDepartmentTasks();

        } else if (input.equals("2") || input.toLowerCase().equals("meat department")) {
            MeatDepartment meat = MeatDepartment.getInstance();
            meat.displayDepartmentTasks();

        } else if (input.equals("3") || input.toLowerCase().equals("security department")) {
            SecurityDepartment security = SecurityDepartment.getInstance();
            security.displayDepartmentTasks();
            
        } else if (input.equals("4") || input.toLowerCase().equals("pharmacy department")) {
            PharmacyDepartment pharmacy = PharmacyDepartment.getInstance();
            pharmacy.displayDepartmentTasks();
            
        }
    }
}
