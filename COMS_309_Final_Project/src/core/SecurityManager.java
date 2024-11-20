package core;

import java.util.Scanner;

public class SecurityManager {
    private Scanner in;

    public SecurityManager() {
        in = new Scanner(System.in);
    }

    public void monitorStore() {
        System.out.println("Monitoring the store...");
        System.out.println("Press 'q' to stop monitoring or any other key to continue.");
        String input = in.nextLine();
        if ("q".equalsIgnoreCase(input)) {
            System.out.println("Stopped monitoring the store.");
        } else {
            System.out.println("Continuing to monitor...");
        }
    }

    public void reportTheft() {
        System.out.print("Enter description of the suspected theft: ");
        String description = in.nextLine();
        System.out.println("Alerting authorities with the provided description...");
        System.out.println("Theft report logged with details: " + description);
    }

    public void respondToEmergency() {
        System.out.println("Emergency detected!");
        System.out.println("1. Call Emergency Services (911)");
        System.out.println("2. Trigger In-Store Alarm");
        System.out.println("3. Evacuate Store");
        System.out.print("Choose an action: ");
        String choice = in.nextLine();

        switch (choice) {
            case "1":
                System.out.println("Calling Emergency Services...");
                break;
            case "2":
                System.out.println("In-Store Alarm triggered!");
                break;
            case "3":
                System.out.println("Initiating store evacuation procedures...");
                break;
            default:
                System.out.println("Invalid option. No action taken.");
        }
    }

    public void reviewSurveillanceFootage() {
        System.out.println("Reviewing Surveillance Footage...");
        System.out.println("1. Select Camera (1-10)");
        System.out.println("2. Fast Forward");
        System.out.println("3. Pause");
        System.out.println("4. Rewind");
        System.out.println("5. Exit Review");
        boolean inLoop = true;

        while (inLoop) {
            System.out.print("Enter option: ");
            String choice = in.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Enter Camera Number (1-10): ");
                    int cameraNumber = Integer.parseInt(in.nextLine());
                    System.out.println("Displaying footage from Camera " + cameraNumber);
                    break;
                case "2":
                    System.out.println("Fast forwarding footage...");
                    break;
                case "3":
                    System.out.println("Footage paused.");
                    break;
                case "4":
                    System.out.println("Rewinding footage...");
                    break;
                case "5":
                    System.out.println("Exiting surveillance footage review.");
                    inLoop = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    public void patrolPremises() {
        System.out.println("Patrolling the premises...");
        System.out.println("1. Check Parking Lot");
        System.out.println("2. Inspect Back Room");
        System.out.println("3. Walk Store Aisles");
        System.out.println("4. Return to Security Office");
        boolean patrolling = true;

        while (patrolling) {
            System.out.print("Choose area to patrol: ");
            String area = in.nextLine();

            switch (area) {
                case "1":
                    System.out.println("Patrolling Parking Lot...");
                    break;
                case "2":
                    System.out.println("Inspecting Back Room...");
                    break;
                case "3":
                    System.out.println("Walking through Store Aisles...");
                    break;
                case "4":
                    System.out.println("Returning to Security Office.");
                    patrolling = false;
                    break;
                default:
                    System.out.println("Invalid option. Please select a valid area.");
            }
        }
    }
}