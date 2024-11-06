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
		if(accessLevel.equals("manager")) {
			System.out.println("Tasks: Inventory");
		}
		else if(accessLevel.equals("employee")) {
			System.out.println("Tasks: Stocking, Cleaning");
		}
	}
	
	public void StockShelves() {
		
	}
	
	public void Clean() {
		
	}
}
