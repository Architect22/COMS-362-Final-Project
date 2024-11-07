package core;

import java.util.ArrayList;
import java.util.List;

public class AlertSystem {
    private List<Issue> activeAlerts = new ArrayList<>();

    public void triggerAlert(Issue issue) {
        activeAlerts.add(issue);
        System.out.println("Alert triggered: " + issue.getDescription());
    }

    public List<Issue> getActiveAlerts() {
        return activeAlerts;
    }

    public void resolveAlert(Issue issue) {
        activeAlerts.remove(issue);
        System.out.println("Alert resolved: " + issue.getDescription());
    }
}
