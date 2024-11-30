package core;

import java.util.List;

public class Order {
    private String orderNumber;
    private String customerName;
    private List<String> items;
    private OrderStatus status;
    private String pickupTime;

    public Order(String orderNumber, String customerName, List<String> items, String pickupTime) {
        this.orderNumber = orderNumber;
        this.customerName = customerName;
        this.items = items;
        this.status = OrderStatus.PENDING;
        this.pickupTime = pickupTime;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public List<String> getItems() {
        return items;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public String getPickupTime() {
        return pickupTime;
    }
}
