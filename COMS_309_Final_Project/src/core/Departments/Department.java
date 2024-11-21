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
        System.out.println("| 4. Pharmacy Department                                       |");
        System.out.println("| 5. Human Resources Department                                |");
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

        switch (input.toLowerCase())
        {
        	case "1": case "floral department":
            FloralDepartment floral = FloralDepartment.getInstance();
            floral.displayDepartmentTasks();
        	break;
        	
        	case "2": case "meat department":
            MeatDepartment meat = MeatDepartment.getInstance();
            meat.displayDepartmentTasks();
            break;

        	case "3": case "security department":
            SecurityDepartment security = SecurityDepartment.getInstance();
            security.displayDepartmentTasks();
            break;
            
        	case "4": case "pharmacy department":
            PharmacyDepartment pharmacy = PharmacyDepartment.getInstance();
            pharmacy.displayDepartmentTasks();
            break;
            
        	case "5": case "human resources department":
        	HumanResourcesDepartment hr = HumanResourcesDepartment.getInstance();
        	hr.displayDepartmentTasks();
        	break;
        }
    }
}
