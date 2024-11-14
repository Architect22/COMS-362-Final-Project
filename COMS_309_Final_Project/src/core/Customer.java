package core;

import java.util.Scanner;

public class Customer {
    private Scanner in;

    public Customer() {
        in = new Scanner(System.in);
        startCustomerSession();
    }

    private void startCustomerSession() {
        System.out.println("Welcome to the Grocery Store!");

        boolean isShopping = true;
        while (isShopping) {
            System.out.println("\nPlease select an option:");
            System.out.println("1. Online Order");
            System.out.println("2. Self-Checkout");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            String choice = in.nextLine();

            switch (choice) {
                case "1":
                    // Start Online Order
                    new OnlineOrderApp();
                    break;
                case "2":
                    // Start Self-Checkout
                    new SelfCheckout();
                    break;
                case "3":
                    isShopping = false;
                    System.out.println("Thank you for visiting our store!");
                    break;
                default:
                    System.out.println("Invalid choice. Please select 1, 2, or 3.");
            }
        }
    }

    public static void main(String[] args) {
        new Customer();
    }
}
