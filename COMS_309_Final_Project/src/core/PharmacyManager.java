package core;

import core.braden.InventorySystem;
import java.util.Scanner;

public class PharmacyManager {
    private Scanner in;
    private InventorySystem inv;
    private String prescriptionName = "";
    private int numProduct = 0;


    public PharmacyManager() {
        in = new Scanner(System.in);
        inv = InventorySystem.getInstance();
    }

    public void greetCustomer() {
        System.out.println("Pharmacy staff greets the customer. 'Welcome to the pharmacy! How can we assist you today?'");
    }

    public void collectPrescriptionDetails() {
        System.out.print("Please provide your prescription you want: ");
        String prescriptionDetails = in.nextLine();

        while (!inv.getStockLevels().containsKey(prescriptionDetails)) {
            System.out.println("The entered prescription \"" + prescriptionDetails + "\" is not available in the inventory.");
            System.out.print("Please provide a valid prescription name (-1 to exit): ");
            prescriptionDetails = in.nextLine();
            if (prescriptionDetails.equals("-1")) {
                return;
            }
        }

        prescriptionName = prescriptionDetails;
        System.out.println("Prescription details received: " + prescriptionDetails + ", how many do you need?");
        numProduct = in.nextInt();
    }


    public void retrieveMedication() {
        System.out.println("Retrieving medication from the pharmacy system...");
    }

    public void processPayment() {
        System.out.println("Processing payment...");
        System.out.println("Enter payment method (e.g., Credit Card, Cash, -1 to cancel): ");

        if (in.hasNextLine()) {
            in.nextLine();
        }

        String paymentMethod = in.nextLine();

        if (paymentMethod.equals("") || paymentMethod.equals("-1")) {
            System.out.println("Payment declined. Please provide an alternate payment method.");
        } else {
            System.out.println("Payment successfully processed using: " + paymentMethod);
            inv.placeOrder(prescriptionName, numProduct * -1);
            System.out.println("Processing payment with: " + paymentMethod);
            System.out.println("Order placed!");
        }
    }

    public void handMedicationToCustomer() {
        System.out.println("Handing medication to the customer...");
        System.out.println("If requested, providing additional counseling on medication usage.");
    }

    public void handleCustomerDeparture() {
        System.out.println("Customer leaves the drive-thru. 'Thank you for visiting! Have a great day!'");
        System.out.println("If customer forgot to ask a question, they may park and go inside.");
    }
    
}
