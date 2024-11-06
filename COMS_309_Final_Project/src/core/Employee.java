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
				"1. Walk through isles checking what products need stocking\r\n"
				+ "2. Go to backroom\r\n"
				+ "3. Find related stock\r\n"
				+ "4. Bring stock to isle\r\n"
				+ "5. Pull later expiration dates to front\r\n"
				+ "6. Put back stock behind any existing items\r\n"
				+ "7. Face up the product\r\n"
				+ "8. Throw away trash\r\n"
				+ "");
	}
	
	public void Clean() {
		System.out.println(
				"1. Go to an area to clean\r\n"
				+ "2. Determine if the area needs cleaning\r\n"
				+ "3. Throw away any trash in area\r\n"
				+ "4. Sanitize the area\r\n"
				+ "5. Wipe down area\r\n"
				+ "6. Go to next area\r\n"
				+ "");
	}
}
