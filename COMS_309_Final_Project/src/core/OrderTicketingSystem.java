package core;

import java.util.LinkedList;
import java.util.Queue;

public class OrderTicketingSystem {
    private Queue<OrderTicket> orderQueue;

    public OrderTicketingSystem() {
        this.orderQueue = new LinkedList<>();
    }

    public void submitTicket(OrderTicket ticket) {
        orderQueue.add(ticket);
        System.out.println("Order Ticket Submitted: " + ticket);
    }

    public OrderTicket getNextTicket() {
        if (!orderQueue.isEmpty()) {
            return orderQueue.poll();
        } else {
            System.out.println("No more orders in the queue.");
            return null;
        }
    }

    public void displayPendingOrders() {
        if (orderQueue.isEmpty()) {
            System.out.println("No pending orders in the queue.");
        } else {
            System.out.println("\n--- Pending Orders ---");
            for (OrderTicket ticket : orderQueue) {
                System.out.println(ticket);
            }
        }
    }

    public boolean hasOrders() {
        return !orderQueue.isEmpty();
    }
}
