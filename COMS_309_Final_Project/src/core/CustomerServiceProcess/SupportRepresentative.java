package core.CustomerServiceProcess;

import java.util.Scanner;


public class SupportRepresentative {
    private CustomerSupportSystem supportSystem;

    public SupportRepresentative(CustomerSupportSystem supportSystem) {
        this.supportSystem = supportSystem;
    }

    public void greetCustomer(CustomerInquiry inquiry) {
        System.out.println("Hello " + inquiry.getCustomerName() + ", how can I assist you today?");
    }

    public void handleInquiry(CustomerInquiry inquiry) {
        if (!supportSystem.withinOperatingHours()) {
            supportSystem.handleOutsideOperatingHours();
            return;
        }
        greetCustomer(inquiry);
        
        String issue = inquiry.getIssueDescription();
        System.out.println("Customer issue: " + issue);

        if (issue.isEmpty()) {
            requestAdditionalInfo(inquiry);
        } else {
            resolveIssue(inquiry);
        }
    }

    private void resolveIssue(CustomerInquiry inquiry) {
        // Logic to resolve common issues
        System.out.println("Resolving issue: " + inquiry.getIssueDescription());
        inquiry.markResolved();
        supportSystem.logInquiry(inquiry);
    }

    public void escalateIssue(CustomerInquiry inquiry, String reason) {
        supportSystem.escalateInquiry(inquiry, reason);
    }

    private void requestAdditionalInfo(CustomerInquiry inquiry) {
        System.out.println("Could you provide more details about your issue?");
        // Assume more details are collected from customer input
    }

    public void logInquiry() {
        System.out.println("Logging your product");
    }

    public void offerAlternatives() {
        System.out.println("The product is out of stock; would you like to try similar products?");
    }

    public void provideProductInformation() {
        System.out.println("Providing product information...");
    }

    public void checkOrderStatus() {
        System.out.println("Order Status: Providing status of your order...");
    }


}
