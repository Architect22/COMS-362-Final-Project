package core.CustomerServiceProcess;

import java.util.*;

public class CustomerSupportSystem {
    private Map<String, String> operatingHours;
    private Queue<CustomerInquiry> inquiryQueue = new LinkedList<>();
    private boolean isOperational = true;

    public CustomerSupportSystem() {
        operatingHours = new HashMap<>();
        // Initialize operating hours for each day
    }

    public boolean checkAvailability() {
        return isOperational;
    }

    public boolean withinOperatingHours() {
        // Logic to check if the current time is within operating hours
        return true;
    }

    public void handleOutsideOperatingHours() {
        System.out.println("Thank you for reaching us outside of operating hours. Please call back during our available times or leave a message.");
    }

    public void toggleSystemStatus(boolean status) {
        isOperational = status;
    }

    public void logInquiry(CustomerInquiry inquiry) {
        // Logs inquiries to support system or calls error handler in case of issues
        System.out.println("Logging inquiry from " + inquiry.getCustomerName());
    }

    public void escalateInquiry(CustomerInquiry inquiry, String reason) {
        inquiry.setEscalationNeeded(true);
        System.out.println("Escalating inquiry: " + reason);
    }
}
