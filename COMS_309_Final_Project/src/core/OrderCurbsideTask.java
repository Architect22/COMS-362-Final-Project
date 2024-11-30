package core;

import core.Tasks.Task;
import core.Tasks.TaskType;
import java.util.ArrayList;

public class OrderCurbsideTask extends Task{
    private OrderFulFillmentSystem orderSystem;
    
    public OrderCurbsideTask(String name, TaskType type, ArrayList<String> taskSteps) {
        super(name, type, taskSteps);
        orderSystem = OrderFulFillmentSystem.getInstance();
    }

    @Override
    public void executeTask(int taskCode) {
        if (taskCode == 1) {
            orderSystem.logIntoTerminalSystem();

        } else if (taskCode == 2) {
            orderSystem.displayPendingOrders();

        } else if (taskCode == 3) {
            orderSystem.selectOrderToPrepare();

        } else if (taskCode == 4) {
            orderSystem.displayOrderDetails();

        } else if (taskCode == 5) {
            orderSystem.markItemsPicked();

        } else if (taskCode == 6) {
            orderSystem.markOrderReadyForPickup();

        } else if (taskCode == 7) {
            orderSystem.sendNotificationToCustomer();

        } else if (taskCode == 8) {
            orderSystem.handleCustomerArrival();

        } else if (taskCode == 9) {
            orderSystem.confirmOrderNumber();

        }  else if (taskCode == 10) {
            orderSystem.markOrderPickedUp();

        }  else if (taskCode == 11) {
            orderSystem.recordOrderFulfillment();

        } else {
            System.out.print("Try Again!");
        }
    }
    
}
