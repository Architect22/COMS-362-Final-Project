package core.CustomerServiceProcess;

public class CustomerInquiry {
    private String customerName;
    private String issueDescription;
    private boolean isResolved = false;
    private boolean escalationNeeded = false;

    public CustomerInquiry(String customerName, String issueDescription) {
        this.customerName = customerName;
        this.issueDescription = issueDescription;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getIssueDescription() {
        return issueDescription;
    }

    public boolean isResolved() {
        return isResolved;
    }

    public void markResolved() {
        isResolved = true;
    }

    public boolean isEscalationNeeded() {
        return escalationNeeded;
    }

    public void setEscalationNeeded(boolean escalationNeeded) {
        this.escalationNeeded = escalationNeeded;
    }
}
