package core.SelfCheckoutManagerProcess;


import java.util.ArrayList;
import java.util.List;

import core.SelfCheckout;

public class SelfCheckoutManager {
    private List<SelfCheckout> stations;
    private boolean monitoringSystemOperational;
    private boolean onBreak;

    public SelfCheckoutManager(int stationCount, boolean monitoringSystemOperational) {
        this.stations = new ArrayList<>();
        for (int i = 0; i < stationCount; i++) {
            stations.add(new SelfCheckout());
        }
        this.monitoringSystemOperational = monitoringSystemOperational;
        this.onBreak = false;
    }

    public void monitorStations() {
        if (!monitoringSystemOperational) {
            System.out.println("Monitoring system malfunction. Switching to manual monitoring.");
            manuallyMonitorStations();
            return;
        }

        System.out.println("Monitoring self-checkout stations...");
        for (SelfCheckout station : stations) {
            if (station.hasIssue()) {
                System.out.println("Alert: Issue detected at a self-checkout station.");
                assistCustomer(station);
            }
        }
    }

    private void manuallyMonitorStations() {
        System.out.println("Visually checking each self-checkout station for issues...");
        for (SelfCheckout station : stations) {
            if (station.hasIssue()) {
                System.out.println("Manual check: Issue detected at a self-checkout station.");
                assistCustomer(station);
            }
        }
    }

    private void assistCustomer(SelfCheckout station) {
        System.out.println("Approaching self-checkout station to assist customer.");
        if (station.hasAgeRestrictedItem()) {
            System.out.println("Customer has an age-restricted item. Verifying ID...");
            if (verifyCustomerID()) {
                station.authorizeRestrictedItem();
                System.out.println("Purchase authorized for age-restricted item.");
            } else {
                System.out.println("ID verification failed. Declining sale due to store policy.");
            }
        } else if (station.hasScannerIssue()) {
            handleScannerIssue(station);
        } else {
            System.out.println("Assisting customer with general issue.");
            station.resolveIssue();
        }
        System.out.println("Issue resolved. Returning to monitoring area.");
    }

    private boolean verifyCustomerID() {
        System.out.println("Enter ID verification result (1 for valid, 0 for invalid): ");
        int idVerification = 1; 
        return idVerification == 1;
    }

    private void handleScannerIssue(SelfCheckout station) {
        System.out.println("Scanner issue detected. Attempting to assist customer.");
        if (!station.rescanItem()) {
            System.out.println("Rescan failed. Manually entering barcode...");
            if (!station.enterBarcodeManually()) {
                System.out.println("Manual entry unsuccessful. Redirecting customer to another station.");
                redirectCustomerToAnotherStation(station);
            }
        }
    }

    private void redirectCustomerToAnotherStation(SelfCheckout station) {
        System.out.println("Locating an available self-checkout station...");
        for (SelfCheckout s : stations) {
            if (s.isAvailable()) {
                System.out.println("Directing customer to another self-checkout station.");
                return;
            }
        }
        System.out.println("No self-checkout stations available. Directing customer to staffed checkout lane.");
    }

    public void endOfDayRoutine() {
        System.out.println("Performing end-of-day routine checks on all stations...");
        for (SelfCheckout station : stations) {
            station.performRoutineCheck();
        }
        System.out.println("All self-checkout stations checked. End-of-day routine complete.");
    }

    public void takeBreak() {
        System.out.println("Self-checkout manager is taking a break. Assigning another coworker to monitor stations.");
        this.onBreak = true;
    }

    public void returnFromBreak() {
        System.out.println("Self-checkout manager has returned from break.");
        this.onBreak = false;
    }

    public void handleMultipleIssues() {
        System.out.println("Multiple issues detected at different stations. Prioritizing assistance...");
        assistCustomer(stations.get(0)); 
    }

    public void resolveIssue() {
        System.out.println("Resolving customer issue at self-checkout station");
    }

    public void authorizeAgeRestrictedItem() {
        System.out.println("Authorizing purchase of age-restricted item");
    }

    public void checkScanner() {
        System.out.println("Checking scanner functionality at self-checkout station");
    }

    public void routeCustomerToAnotherStation() {
        System.out.println("Routing customer to an alternative self-checkout station");
    }

    public void callForSupport() {
        System.out.println("Requesting additional support for self-checkout area");
    }

    public void performRoutineCheck() {
        System.out.println("Performing end-of-day routine checks on all self-checkout stations");
    }

    public void investigateCashDiscrepancy() {
        System.out.println("Investigating cash discrepancy at a self-checkout station");
    }
}
