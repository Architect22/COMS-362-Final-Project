package core;

import core.Tasks.Task;
import core.Tasks.TaskType;
import java.util.ArrayList;

public class SecurityTask extends Task {
    public static SecurityDepartment secDep;
    public static SecurityManager securityPersonnel; 


    public SecurityTask(String name, TaskType type, ArrayList<String> taskSteps) {
        super(name, type, taskSteps);
    }

    @Override
    public void executeTask(int taskCode) {
        if (taskCode == 1) {
            this.securityPersonnel.monitorStore();
        } else if (taskCode == 2) {
            this.securityPersonnel.reportTheft();
        } else if (taskCode == 3) {
            this.securityPersonnel.respondToEmergency();
        } else if (taskCode == 4) {
            this.securityPersonnel.reviewSurveillanceFootage();
        } else if (taskCode == 5) {
            this.securityPersonnel.patrolPremises();
        }
    }


    
}
