package core;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class OrderFulFillmentSystem {
    private List<Order> pendingOrders;

    public static OrderFulFillmentSystem instance;

    public static OrderFulFillmentSystem getInstance() {
        if (instance == null) {
            instance = new OrderFulFillmentSystem();
        }
        return instance;
    }

    public OrderFulFillmentSystem() {
        this.pendingOrders = new ArrayList<>();
    }

    public void displayPendingOrders() {
        if (pendingOrders.isEmpty()) {
            System.out.println("No pending curbside pickup orders.");
            return;
        }

        pendingOrders.sort(Comparator.comparing(Order::getPickupTime));
        System.out.println("Pending Orders (Sorted by Pickup Time):");
        for (Order order : pendingOrders) {
            System.out.println("Order: " + order.getOrderNumber() + " | Customer: " + order.getCustomerName() + " | Time: " + order.getPickupTime());
        }
    }

    public void prepareOrder(String orderNumber) {
        Order order = findOrder(orderNumber);
        if (order == null) {
            System.out.println("Order not found.");
            return;
        }
        System.out.println("Preparing Order: " + order.getOrderNumber());
        System.out.println("Customer: " + order.getCustomerName());
        System.out.println("Items: " + order.getItems());
        order.setStatus(OrderStatus.READY_FOR_PICKUP);
        notifyCustomer(order);
    }

    private Order findOrder(String orderNumber) {
        for (Order order : pendingOrders) {
            if (order.getOrderNumber().equals(orderNumber)) {
                return order;
            }
        }
        return null;
    }

    private void notifyCustomer(Order order) {
        System.out.println("Notification sent to " + order.getCustomerName() + " that their order is ready for pickup.");
    }

    public void markOrderAsPickedUp(String orderNumber) {
        Order order = findOrder(orderNumber);
        if (order != null && order.getStatus() == OrderStatus.READY_FOR_PICKUP) {
            System.out.println("Order " + order.getOrderNumber() + " has been picked up by the customer.");
            order.setStatus(OrderStatus.FULFILLED);
        } else {
            System.out.println("Order not ready for pickup or not found.");
        }
    }

    public void addOrder(Order order) {
        pendingOrders.add(order);
    }

    ///////////////////////////////////////////////////////////////////////////

    public void logIntoTerminalSystem() {
        System.out.println("Logging into terminal system...");
    }

    public void selectOrderToPrepare() {
        System.out.println("Selecting an order to prepare...");
    }

    public void displayOrderDetails() {
        System.out.println("Displaying order details...");
    }

    public void markItemsPicked() {
        System.out.println("Marking items as picked...");
    }

    public void markOrderReadyForPickup() {
        System.out.println("Marking order status as Ready for Pickup...");
    }

    public void sendNotificationToCustomer() {
        System.out.println("Sending notification to customer...");
    }

    public void handleCustomerArrival() {
        System.out.println("Handling customer arrival...");
    }

    public void confirmOrderNumber() {
        System.out.println("Confirming order number...");
    }

    public void markOrderPickedUp() {
        System.out.println("Marking order as Picked Up...");
    }

    public void recordOrderFulfillment() {
        System.out.println("Recording order completion and logging it as fulfilled...");
    }

    public void notifyNoPendingOrders() {
        System.out.println("Notifying employee of no pending orders...");
    }

    public void handleItemNotFound() {
        System.out.println("Handling item not found scenario...");
    }

    public void handleDamagedItem() {
        System.out.println("Handling damaged item scenario...");
    }

    public void handleSystemError() {
        System.out.println("Handling system error, switching to offline procedures...");
    }

    public void verifyCustomerIdentity() {
        System.out.println("Verifying customer identity through other means...");
    }

    public void handleOrderDeclined() {
        System.out.println("Recording declined order and updating status...");
    }


}
