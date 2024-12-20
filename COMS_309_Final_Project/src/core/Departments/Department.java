package core.Departments;

import core.FulfillmentDepartment;
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

        System.out.println("| 1.  Floral Department                                         |");
        System.out.println("| 2.  Meat Department                                           |");
        System.out.println("| 3.  Security Department                                       |");
        System.out.println("| 4.  Pharmacy Department                                       |");
        System.out.println("| 5.  Human Resources Department                                |");
        System.out.println("| 6.  Online Order Department                                   |");
        System.out.println("| 7.  Receipt Verification Department                           |");
        System.out.println("| 8.  Produce Department                                        |");
        System.out.println("| 9.  Bakery Department                                         |");
        System.out.println("| 10. Accounting Department                                     |");
        System.out.println("| 11. Restaurant Department                                     |");
        System.out.println("| 12. Wine and Spirits Department                               |");
        System.out.println("| 13. Frozen and Dairy Department                               |");
        System.out.println("| 14. Fulfillment Department                                    |");
        System.out.println("|---------------------------------------------------------------|");
        acceptInput();
    }

    public void displayDepartmentTasks() {
        Utility.createHeader(width, "DepartmentTasks");
        System.out.println("|--------------------------------------------------------------|");
    }

    private static void acceptInput() {
        @SuppressWarnings("resource")
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();

        switch (input.toLowerCase()) {
            case "1":
            case "floral department":
                FloralDepartment.getInstance().displayDepartmentTasks();
                break;

            case "2":
            case "meat department":
                MeatDepartment.getInstance().displayDepartmentTasks();
                break;

            case "3":
            case "security department":
                SecurityDepartment.getInstance().displayDepartmentTasks();
                break;

            case "4":
            case "pharmacy department":
                PharmacyDepartment.getInstance().displayDepartmentTasks();
                break;

            case "5":
            case "human resources department":
                HumanResourcesDepartment.getInstance().displayDepartmentTasks();
                break;

            case "6":
            case "online order department":
                OnlineOrderDepartment.getInstance().displayDepartmentTasks();
                break;

            case "7":
            case "receipt verification department":
                ReceiptVerificationDepartment.getInstance().displayDepartmentTasks();
                break;
            case "8":
            case "produce department":
                ProduceDepartment.getInstance().displayDepartmentTasks();
                break;
            case "9":
            case "bakery department":
                BakeryDepartment.getInstance().displayDepartmentTasks();
                break;
            case "10":
            case "accounting department":
            	AccountingDepartment.getInstance().displayDepartmentTasks();
                break;
            case "11":
            case "restaurant department":
                RestaurantDepartment.getInstance().promptUserForDepartmentTask();
                break;
            case "12":
            case "wine and spirits department":
                WineAndSpiritsDepartment.getInstance().displayDepartmentTasks();
                break;
            case "13":
            case "frozen and dairy department":
                FrozenDepartment.getInstance().displayDepartmentTasks();
                break;
            case "14":
            case "fulfillment department":
                FulfillmentDepartment.getInstance().displayDepartmentTasks();
                break;
        }
    }
}
