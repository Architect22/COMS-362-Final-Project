package core;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Employee {
	private Scanner in;
	private InventoryManager invManager
	public Employee(InventoryManager invManager) {
		in = new Scanner(System.in);
		init();
	}
	public void init() {
		System.out.println("Access levels: Manager, Employee");
		System.out.print("Please enter your access level: ");
		String accessLevel = in.nextLine().toLowerCase();
		GenerateTaskList(accessLevel);
	}
	
	private void GenerateTaskList(String accessLevel) {
		System.out.println("|----------------------------------------|");
		System.out.println("|                  TASKS                 |");
		if(accessLevel.equals("manager")) {
			System.out.println("| 1. inventory                           |");
			System.out.println("|----------------------------------------|");
			System.out.print("Enter task to complete: ");
			String task = in.nextLine().toLowerCase();
			ChooseTask(task);
			System.out.println();
			System.out.println();
		}
		else if(accessLevel.equals("employee")) {
			System.out.println("| 1. Stocking                            |");
			System.out.println("| 2. Cleaning                            |");
			System.out.println("| 3. Sale                                |");
			System.out.println("|----------------------------------------|");
			System.out.print("Enter task to complete: ");
			String task = in.nextLine().toLowerCase();
			ChooseTask(task);
		}
	}
	
	private void ChooseTask(String task) {
		if(task.equals("stocking")) {
			StockShelves();
		}
		else if(task.equals("cleaning")) {
			Clean();
		}
		else if (task.equals("sale")) {
			handleSale();
		}
		else if (task.equals("inventory")) {
			handleInventory();
		}
		else {
			System.out.print("Nothing to do for this task.");
		}
		System.out.println();
		System.out.println();
	}

public static void handleInventory() {
        System.out.println("|----------------------------------------|");
        System.out.println("|          INVENTORY MANAGEMENT         |");
        System.out.println("| 1. View Inventory Dashboard           |");
        System.out.println("| 2. Adjust Reorder Quantities          |");
        System.out.println("| 3. Place an Order                     |");
        System.out.println("| 4. Check Stock Discrepancies          |");
        System.out.println("| 5. Manually Adjust Stock              |");
        System.out.println("| 6. Request Inventory Audit            |");
        System.out.println("|----------------------------------------|");
        System.out.print("Enter task to complete: ");
        String inventoryTask = in.nextLine().toLowerCase();

        switch (inventoryTask) {
            case "1":
            case "view inventory dashboard":
                viewInventoryDashboard();
                break;
            case "2":
            case "adjust reorder quantities":
                adjustReorderQuantities();
                break;
            case "3":
            case "place an order":
                placeOrder();
                break;
            case "4":
            case "check stock discrepancies":
                checkStockDiscrepancies();
                break;
            case "5":
            case "manually adjust stock":
                manuallyAdjustStock();
                break;
            case "6":
            case "request inventory audit":
                requestInventoryAudit();
                break;
            default:
                System.out.println("Invalid option. Returning to task menu...");
        }
    }
	
	public void StockShelves() {
	
	}
	
	public void Clean() {
		System.out.println(
				"1. Go to an area to clean\n"
				+ "   1a. Go to area requested by manager or customer\n"
				+ "2. Determine if the area needs cleaning\n"
				+ "   2a. If the area is regularly used, clean it anyway\n"
				+ "3. Throw away any trash in area\n"
				+ "   3a. Take trash to compactor if your bag is full\n"
				+ "   3b. If compactor is full, run compactor\n"
				+ "4. Sanitize the area\n"
				+ "5. Wipe down area\n"
				+ "   5a. Once per day, after close, run floor cleaner machine\n"
				+ "6. Go to next area\n");
	}
	
	public void handleSale() {
		System.out.println("Enter name and price for each item, or enter \"done\" to calculate total.");
		
		List<String> names = new ArrayList<>();
		List<Float> prices = new ArrayList<>();
		float subtotal = 0f;
		for (int i = 1; ; i++) {
			System.out.printf("%d Name:  ", i);
			String name = in.nextLine();
			if ("done".equals(name))
				break;
			if ("cancel".equals(name))
				return;
			System.out.printf("%d Price: $", i);
			float price = Float.parseFloat(in.nextLine());
			subtotal += price;
			
			names.add(name);
			prices.add(price);
		}
		
		subtotal = 0.01f * Math.round(subtotal * 100f);
		float tax = 0.01f * Math.round(subtotal * 7f);
		while (true) {
			// payment and payment rejection are simulated
			System.out.printf("Subtotal: $%.2f%nTax: %.2f%nTotal: %.2f%nEnter account number: ", subtotal, tax, subtotal + tax);
			if ("cancel".equals(in.nextLine()))
				return;
			if (Math.random() < 0.9)
				break;
			System.out.println("Transaction declined (insufficient funds)");
		}
		System.out.println("Thank you for shopping with us!");
		System.out.println("TODO: update inventory");
		
		System.out.println();
		System.out.println("Receipt:");
		for (int i = 0; i < names.size(); i++) {
			System.out.printf("%-19s %10s%n", names.get(i), String.format("$%.2f", prices.get(i)));
		}
		System.out.printf(
			"------------------------------%n" +
			"Subtotal            %10s%n" +
			"Tax                 %10s%n" +
			"Total               %10s%n" +
			"Receipt ID: %d%n",
			String.format("$%.2f", subtotal), String.format("$%.2f", tax), String.format("$%.2f", subtotal + tax), (int)(Math.random() * 1000000000));
	}

}
