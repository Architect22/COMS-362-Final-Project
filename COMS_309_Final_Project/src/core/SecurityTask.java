package core;

import core.Tasks.Task;
import core.Tasks.TaskType;
import java.util.ArrayList;

public class SecurityTask extends Task {
    public static SecurityDepartment secDep;
    public static SecurityManager securityPersonnel = new SecurityManager(); 


    public SecurityTask(String name, TaskType type, ArrayList<String> taskSteps) {
        super(name, type, taskSteps);
    }

    @Override
    public void executeTask(int taskCode) {
        if (taskCode == 1) {
            securityPersonnel.monitorStore();
        } else if (taskCode == 2) {
            securityPersonnel.reportTheft();
        } else if (taskCode == 3) {
            securityPersonnel.respondToEmergency();
        } else if (taskCode == 4) {
            securityPersonnel.reviewSurveillanceFootage();
        } else if (taskCode == 5) {
            securityPersonnel.patrolPremises();
        }
    }


    
}
