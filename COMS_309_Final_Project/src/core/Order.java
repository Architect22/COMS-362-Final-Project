package core;

public class Order {
    private String orderId;
    private String customerName;
    private String orderStatus;
    private String items;

    public Order(String orderId, String customerName, String orderStatus, String items) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.orderStatus = orderStatus;
        this.items = items;
    }

    // Getters
    public String getOrderId() {
        return orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public String getItems() {
        return items;
    }

    // Setters (if needed)
    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Override
    public String toString() {
        return "Order ID: " + orderId + "\nCustomer Name: " + customerName + "\nOrder Status: " + orderStatus + "\nItems: " + items + "\n";
    }
}
