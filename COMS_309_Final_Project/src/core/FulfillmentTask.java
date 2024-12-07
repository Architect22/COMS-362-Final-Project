package core;

import core.Tasks.Task;
import core.Tasks.TaskType;
import java.util.ArrayList;

public class FulfillmentTask extends Task{
    
    public FulfillmentTask(String name, TaskType type, ArrayList<String> taskSteps) {
        super(name, type, taskSteps);
    }
    
    @Override
    public void executeTask(int taskCode) {
        switch (taskCode) {
            case 1 -> FulfillmentSystem.getInstance().logIntoSystem();
            case 2 -> FulfillmentSystem.getInstance().displayPendingOrders();
            case 3 -> FulfillmentSystem.getInstance().selectOrderToPrepare();
            case 4 -> FulfillmentSystem.getInstance().displayOrderDetails();
            case 5 -> FulfillmentSystem.getInstance().markOrderReadyForPickup();
            case 6 -> FulfillmentSystem.getInstance().sendNotificationToCustomer();
            case 7 -> FulfillmentSystem.getInstance().handleCustomerArrival();
            case 8 -> FulfillmentSystem.getInstance().markOrderPickedUp();
            default -> System.out.print("Try Again!");
        }
    }

}
