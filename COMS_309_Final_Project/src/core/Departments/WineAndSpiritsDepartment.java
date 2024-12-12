package core.Departments;

import java.util.ArrayList;


import core.Tasks.WineAndSpiritsTask;
import core.Utility;
import core.Tasks.FloralTask;
import core.Tasks.Task;
import core.Tasks.TaskType;

public class WineAndSpiritsDepartment extends Department {
    private static WineAndSpiritsDepartment instance;

    private WineAndSpiritsDepartment(String name) {
        super(name);
    }

    public static WineAndSpiritsDepartment getInstance() {
        if (instance == null) {
            instance = new WineAndSpiritsDepartment("Wine And Spirits Department");
        }
        return instance;
    }


    @Override
    public void displayDepartmentTasks() {
        Utility.createHeader(width, "Wine And Spirits Department");
        ArrayList<String> tasks = new ArrayList<>();
        tasks.add("Check stock levels for wine and spirits");
        tasks.add("Identify low-stock items and place an order with suppliers");
        tasks.add("Verify that all wine and spirits products are stored in compliance with local laws and regulations");
        tasks.add("Inspect products for damage or tampering before restocking shelves");
        tasks.add("Restock shelves with wine and spirits, ensuring proper product placement and visibility");
        tasks.add("Ensure that products are displayed according to type, brand, and category for customer ease");
        tasks.add("Monitor inventory turnover to prevent overstock or understock of high-demand products");
        tasks.add("Track product expiration dates, if applicable, and remove expired items");
        tasks.add("Document any discrepancies, damaged goods, or issues with compliance");
        tasks.add("If stock levels are low, place an urgent order or adjust display for better product visibility");
        tasks.add("If products are not stored correctly, relocate them to meet compliance standards");
        tasks.add("If damaged or expired products are found, remove them from the shelf and arrange for returns or disposal");
        tasks.add("If inventory discrepancies are found during counts, investigate and reconcile stock with the system");

        Task task = new WineAndSpiritsTask("Wine And Spirits Tasks", TaskType.WINEANDSPIRITS, tasks);
    }

}
