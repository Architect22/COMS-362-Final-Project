package core;

import java.util.List;
import java.util.Map;

public class SelfCheckoutManager {
    private List<SelfCheckoutStation> stations;
    private boolean isMonitoring = true;

    public SelfCheckoutManager(List<SelfCheckoutStation> stations) {
        this.stations = stations;
    }

    public void monitorStations() {
        if (!isMonitoring) {
            System.out.println("Manager is on break; another co-worker is monitoring.");
            return;
        }
        for (SelfCheckoutStation station : stations) {
            if (station.hasIssue()) {
                assistCustomer(station);
            }
        }
    }

    public void assistCustomer(SelfCheckoutStation station) {
        System.out.println("Manager assisting at station: " + station.getId());
        if (station.getIssue().requiresIDVerification()) {
            verifyID(station.getCurrentCustomer());
        }
        station.resolveIssue();
    }

    public void verifyID(Customer customer) {
        if (customer.hasValidID()) {
            System.out.println("ID verified; purchase authorized.");
        } else {
            System.out.println("ID invalid or missing; sale declined.");
        }
    }

    public void takeBreak() {
        isMonitoring = false;
        System.out.println("Manager on break; monitoring transferred to another team member.");
    }

    public void endShiftCheck() {
        System.out.println("Performing routine station checks...");
        for (SelfCheckoutStation station : stations) {
            station.performEndOfDayCheck();
        }
    }
}
