package core.Tasks;

import core.Departments.ReceiptVerificationDepartment;
import java.util.ArrayList;

public class ReceiptVerificationTask extends Task {
    public ReceiptVerificationTask(String name, TaskType type, ArrayList<String> taskSteps) {
        super(name, type, taskSteps);
        generateTasklistUI(taskSteps, name);
    }

    @Override
    public void executeTask(int taskCode) {
        if (taskCode == 1) {
            ReceiptVerificationDepartment.getInstance().verifyReceiptAndItems();
        }
    }

}
