package core;

import core.Tasks.Task;
import core.Tasks.TaskType;
import java.util.ArrayList;

public class PharmacyTask extends Task {
    public static PharmacyDepartment pharmacyDep;
    public static PharmacyManager pharmacyManager = new PharmacyManager();

    public PharmacyTask(String name, TaskType type, ArrayList<String> taskSteps) {
        super(name, type, taskSteps);
    }

    @Override
    public void executeTask(int taskCode) {
        switch (taskCode) {
            case 1:
                pharmacyManager.greetCustomer();
                break;
            case 2:
                pharmacyManager.collectPrescriptionDetails();
                break;
            case 3:
                pharmacyManager.retrieveMedication();
                break;
            case 4:
                pharmacyManager.processPayment();
                break;
            case 5:
                pharmacyManager.handMedicationToCustomer();
                break;
            case 6:
                pharmacyManager.handleCustomerDeparture();
                break;
            default:
                System.out.println("Invalid task code.");
        }
    }
}
