package core;

import core.Tasks.Task;
import core.Tasks.TaskType;
import core.braden.InventorySystem;
import java.util.ArrayList;

public class VendorTask extends Task {
    public static VendorSystem vendorSystem = new VendorSystem();
    public static InventorySystem inv;

    public VendorTask(String name, TaskType type, ArrayList<String> taskSteps) {
        super(name, type, taskSteps);
        inv = InventorySystem.getInstance();
    }

    @Override
    public void executeTask(int taskCode) {
        switch (taskCode) {
            case 1:
                vendorSystem.logIn();
                break;
            case 2: 
                vendorSystem.displayPendingVendorDeliveries();
                break;
            case 3:
                vendorSystem.manageVendorDeliveries();

                break;
            case 4:
                vendorSystem.checkVendorShipment();
                break;
            case 5:
                vendorSystem.verifyDeliveredQuantities();
                break;
            case 6:
                InventorySystem.getInstance().reconcileDeliveries();
                break;
            case 7:
                vendorSystem.sendConfirmationToVendor();
                break;
            default:
                System.out.println("Invalid task code.");
                break;
        }
    }
}
