package core;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Employee {
	private Scanner in;
	private static InventoryManager invManager;
	private PriceManager priceManager;

	public Employee(InventoryManager invManager, PriceManager priceManager) {
		this.invManager = invManager;
		this.priceManager = priceManager;
		in = new Scanner(System.in);
		init();
	}

	public void init() {
		System.out.println("Access levels: Manager, Employee");
		System.out.print("Please enter your access level: ");
		String accessLevel = in.nextLine().toLowerCase();

		if (accessLevel.equals("manager")) {
			GenerateManagerTaskList();
		} else if (accessLevel.equals("employee")) {
			GenerateEmployeeTaskList();
		} else {
			System.out.println("Invalid access level. Please enter either 'Manager' or 'Employee'.");
		}
	}

	private void GenerateManagerTaskList() {
		System.out.println("|----------------------------------------|");
		System.out.println("|                  TASKS                 |");
		System.out.println("| 1. Inventory                           |");
		System.out.println("| 2. Update Price Tags                   |");
		System.out.println("|----------------------------------------|");
		System.out.print("Enter task number: ");
		String taskNumber = in.nextLine().toLowerCase();
		ChooseManagerTask(taskNumber);
	}

	private void GenerateEmployeeTaskList() {
		System.out.println("|----------------------------------------|");
		System.out.println("|                  TASKS                 |");
		System.out.println("| 1. Stocking                            |");
		System.out.println("| 2. Cleaning                            |");
		System.out.println("| 3. Sale                                |");
		System.out.println("|----------------------------------------|");
		System.out.print("Enter task number: ");
		String taskNumber = in.nextLine().toLowerCase();
		ChooseEmployeeTask(taskNumber);
	}


	private void ChooseManagerTask(String task) {
		switch (task) {
			case "1":
			case "inventory":
				handleInventory();
				break;
			case "2":
			case "update price tags":
				handlePriceUpdate();
				break;
			default:
				System.out.println("Invalid task number. Please try again.");
				break;
		}
		System.out.println();
	}


	private void ChooseEmployeeTask(String task) {
		switch (task) {
			case "1":
			case "stocking":
				StockShelves();
				break;
			case "2":
			case "cleaning":
				Clean();
				break;
			case "3":
			case "sale":
				handleSale();
				break;
			default:
				System.out.println("Invalid task number. Please try again.");
				break;
		}
		System.out.println();
	}

	public void handlePriceUpdate() {
		boolean inloop = true;
		while (inloop) {
			System.out.println("|----------------------------------------|");
			System.out.println("|          PRICE MANAGEMENT              |");
			System.out.println("| 1. Price Update Process               |");
			System.out.println("| 2. Walk Isles and Update Tags         |");
			System.out.println("| 0. Exit        |");
			System.out.println("|----------------------------------------|");
			System.out.print("Enter task to complete: ");
			String inventoryTask = in.nextLine().toLowerCase();

			switch (inventoryTask) {
				case "1":
				case "view inventory dashboard":
					priceManager.priceUpdateProcess();
					break;
				case "2":
				case "Walk Isles and Update Tags":
					invManager.adjustReorderQuantities();
					break;
				case "0":
				case "exit":
					inloop = false;
					break;
				default:
					inloop = false;
					System.out.println("Invalid option. Returning to task menu...");
			}
		}
	}

	public void handleInventory() {
		boolean inloop = true;
		while (inloop) {
			System.out.println("|----------------------------------------|");
			System.out.println("|          INVENTORY MANAGEMENT         |");
			System.out.println("| 1. View Inventory Dashboard           |");
			System.out.println("| 2. Adjust Reorder Quantities          |");
			System.out.println("| 3. Place an Order                     |");
			System.out.println("| 4. Check Stock Discrepancies          |");
			System.out.println("| 5. Manually Adjust Stock              |");
			System.out.println("| 6. Request Inventory Audit            |");
			System.out.println("| 7. Handle Order Failure               |");
			System.out.println("| 8. Reschedule Delivery                |");
			System.out.println("| 9. Handle Damaged Product             |");
			System.out.println("|----------------------------------------|");
			System.out.print("Enter task to complete: ");
			String inventoryTask = in.nextLine().toLowerCase();

			switch (inventoryTask) {
				case "1":
				case "view inventory dashboard":
					invManager.viewInventoryDashboard();
					break;
				case "2":
				case "adjust reorder quantities":
					invManager.adjustReorderQuantities();
					break;
				case "3":
				case "place an order":
					invManager.placeOrder();
					break;
				case "4":
				case "check stock discrepancies":
					invManager.checkStockDiscrepancies();
					break;
				case "5":
				case "manually adjust stock":
					invManager.manuallyAdjustStock();
					break;
				case "6":
				case "request inventory audit":
					invManager.requestAudit();
					break;
				case "7":
				case "handle order failure":
					invManager.handleOrderFailure();
					break;
				case "8":
				case "reschedule delivery":
					invManager.rescheduleDelivery();
					break;
				case "9":
				case "handle damaged product ":
					invManager.handleDamagedProduct();
					break;
				case "0":
				case "exit":
					inloop = false;
					break;
				default:
					inloop = false;
					System.out.println("Invalid option. Returning to task menu...");
			}
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
