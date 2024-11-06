package core;

import java.util.Scanner;

public class Employee {
	private Scanner in;
	public Employee() {
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
			System.out.println("| 1. Inventory                           |");
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
		else {
			System.out.print("Nothing to do for this task.");
		}
		System.out.println();
		System.out.println();
	}
	
	public void StockShelves() {
		System.out.println(
				"1. Walk through isles checking what products need stocking\n"
				+ "2. Go to backroom\n"
				+ "3. Find related stock\n"
				+ "   3a. If no stock found, report to manager\n"
				+ "4. Bring stock to isle\n"
				+ "5. Pull later expiration dates to front\n"
				+ "6. Put back stock behind any existing items\n"
				+ "   6a. If stock left over, return it to backroom\n"
				+ "7. Face up the product\n"
				+ "8. Throw away trash\n"
				+ "   8a. If trash is full, use compactor or take it out");
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
}
