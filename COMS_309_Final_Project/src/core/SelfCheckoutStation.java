package core;

public class SelfCheckoutStation {
    private int id;
    private boolean isOperational = true;
    private boolean hasIssue = false;
    private Issue currentIssue;
    private Customer currentCustomer;

    public SelfCheckoutStation(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public boolean hasIssue() {
        return hasIssue;
    }

    public Issue getIssue() {
        return currentIssue;
    }

    public Customer getCurrentCustomer() {
        return currentCustomer;
    }

    public void triggerIssue(Issue issue, Customer customer) {
        this.currentIssue = issue;
        this.currentCustomer = customer;
        this.hasIssue = true;
    }

    public void resolveIssue() {
        System.out.println("Issue resolved at station " + id);
        this.hasIssue = false;
        this.currentIssue = null;
        this.currentCustomer = null;
    }

    public void performEndOfDayCheck() {
        System.out.println("Checking station " + id + " for end-of-day maintenance.");
        // Code for diagnostics
    }
}
