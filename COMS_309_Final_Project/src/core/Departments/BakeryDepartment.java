package core.Departments;

import java.util.ArrayList;
import java.util.Random;

import core.Utility;
import core.Tasks.BakeryTask;
import core.Tasks.Task;
import core.Tasks.TaskType;

public class BakeryDepartment extends Department {
    public static BakeryDepartment instance;
    public ArrayList<String> fullMenu = new ArrayList<>();
    private ArrayList<String> dailyMenu = new ArrayList<>();

    private BakeryDepartment(String name) {
        super(name);
        fullMenu.add("Muffin");
        fullMenu.add("Cake");
        fullMenu.add("Donut");
        fullMenu.add("Brownie");
        fullMenu.add("Bread");
        fullMenu.add("Colache");
        fullMenu.add("Cinnamon Roll");
        fullMenu.add("Cookies");

    }

    public static BakeryDepartment getInstance() {
        if (instance == null) {
            instance = new BakeryDepartment("Bakery Department");
        }
        return instance;
    }

    @Override
    public void displayDepartmentTasks() {
        Utility.createHeader(width, "Bakery Department");
        ArrayList<String> tasks = new ArrayList<>();
        tasks.add("Bake Daily Menu");
        Task task = new BakeryTask("Bakery Tasks", TaskType.BAKERY, tasks);
    }

    public void BakeItem() {
        randomizeMenu();
        for (String item : dailyMenu) {
            Utility.displayLoadingAnimation(1, 500, "Baking " + item);
        }
        System.out.println("All items baked!");
        System.out.println("The daily menu is: ");
        for (String item : dailyMenu) {
            System.out.println(item);
        }
    }

    private void randomizeMenu() {
        dailyMenu.clear();
        Random rand = new Random();
        int amount = rand.nextInt(fullMenu.size());
        for (int i = 0; i < amount; ++i) {
            dailyMenu.add(fullMenu.get(i));
        }
    }
}
