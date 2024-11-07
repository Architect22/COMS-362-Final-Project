package core;

import java.util.Scanner;

public class StoreFront {

	public static void main(String[] args) {
		InventorySystem inventorySystem = new InventorySystem();
        InventoryManager invManager = new InventoryManager(inventorySystem);
		PriceManager priceManager = new PriceManager(inventorySystem);

		Scanner in = new Scanner(System.in);
		while(true) {
			System.out.println("|----------------------------------------|");
			System.out.println("|            Grocery Store               |");
			System.out.println("|                                        |");
			System.out.println("|________________________________________|");
			
			System.out.println("Login: (Admin)(Employee)(Customer)(Exit)");
			System.out.print("Please enter your login type: ");
			String loginType = in.nextLine().toLowerCase();
			
			if(loginType.equals("employee")) {
				Employee employee = new Employee(invManager, priceManager);
			}
			if(loginType.equals("exit")) {
				break;
			}
		}
		in.close();
	}

}