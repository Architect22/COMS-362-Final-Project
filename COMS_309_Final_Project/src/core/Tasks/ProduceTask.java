package core.Tasks;

import java.util.ArrayList;

import core.Utility;
import core.Departments.ProduceDepartment;

public class ProduceTask extends Task {
    public ProduceTask(String name, TaskType type, ArrayList<String> taskSteps) {
        super(name, type, taskSteps);
        generateTasklistUI(taskSteps, name);
    }

    @Override
    public void executeTask(int taskCode) {
        if (taskCode == 1) {
            labelProduce();
        }
    }

    private void labelProduce() {
        for (String item : ProduceDepartment.getInstance().produceItems.keySet()) {
            // if the item hasn't been labeled yet
            if (!ProduceDepartment.getInstance().produceItems.get(item)) {
                ProduceDepartment.getInstance().produceItems.put(item, true);
                Utility.displayLoadingAnimation(1, 200, "Labeling " + item + "s");
            }
        }
        System.out.println("All items labeled!");
    }
}
