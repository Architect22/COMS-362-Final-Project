package core.CustomerServiceProcess;

import java.util.*;

public class CustomerSupportSystem {
    private Map<String, String> operatingHours;
    private Queue<CustomerInquiry> inquiryQueue = new LinkedList<>();
    private boolean isOperational = true;

    public CustomerSupportSystem() {
        operatingHours = new HashMap<>();
    }

    public boolean checkAvailability() {
        return isOperational;
    }

    public boolean withinOperatingHours() {
        return true;
    }

    public void handleOutsideOperatingHours() {
        System.out.println("Thank you for reaching us outside of operating hours. Please call back during our available times or leave a message.");
    }

    public void toggleSystemStatus(boolean status) {
        isOperational = status;
    }

    public void logInquiry(CustomerInquiry inquiry) {
        System.out.println("Logging inquiry from " + inquiry.getCustomerName());
    }

    public void escalateInquiry(CustomerInquiry inquiry, String reason) {
        inquiry.setEscalationNeeded(true);
        System.out.println("Escalating inquiry: " + reason);
    }
}
