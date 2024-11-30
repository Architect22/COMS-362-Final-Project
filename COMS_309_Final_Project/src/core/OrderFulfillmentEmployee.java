package core;

public class OrderFulfillmentEmployee {
    private String name;

    public OrderFulfillmentEmployee(String name) {
        this.name = name;
    }

    public void logIn() {
        System.out.println(name + " has logged into the system.");
    }

    public void handleOrder(String orderNumber, OrderFulfillmentSystem system) {
        system.prepareOrder(orderNumber);
    }

    public void markOrderAsPickedUp(String orderNumber, OrderFulfillmentSystem system) {
        system.markOrderAsPickedUp(orderNumber);
    }
}
