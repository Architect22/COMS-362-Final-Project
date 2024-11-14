package core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import core.CustomerServiceProcess.CustomerInquiry;
import core.CustomerServiceProcess.CustomerSupportSystem;
import core.CustomerServiceProcess.SupportRepresentative;
import core.SelfCheckoutManagerProcess.SelfCheckoutManager;

public class Employee {
	private Scanner in;
	private InventoryManager invManager;
	private PriceManager priceManager;
	private SelfCheckoutManager schMngr;
	private SupportRepresentative csRep;
	private CustomerSupportSystem supportSystem;
	private CustomerInquiry inq;

	public Employee(InventoryManager invManager, PriceManager priceManager, SelfCheckoutManager schMngr,
			SupportRepresentative csRep, CustomerInquiry inq) {
		this.invManager = invManager;
		this.schMngr = schMngr;
		this.csRep = csRep;
		this.inq = inq;

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
		System.out.println("| 4. Return                              |");
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
			case "4":
			case "return":
				handleReturn();
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

	public void handleSelfCheckoutAssistance() {
		boolean inLoop = true;
		while (inLoop) {
			System.out.println("|----------------------------------------|");
			System.out.println("|      SELF-CHECKOUT ASSISTANCE         |");
			System.out.println("| 1. Monitor Stations                   |");
			System.out.println("| 2. Resolve Issue                      |");
			System.out.println("| 3. Authorize Age-Restricted Item      |");
			System.out.println("| 4. Check Scanner Functionality        |");
			System.out.println("| 5. Route Customer to Another Station  |");
			System.out.println("| 6. Call for Additional Support        |");
			System.out.println("| 7. Routine Station Check              |");
			System.out.println("| 8. Investigate Cash Discrepancy       |");
			System.out.println("| 0. Exit                               |");
			System.out.println("|----------------------------------------|");
			System.out.print("Enter task to complete: ");
			String assistanceTask = in.nextLine().toLowerCase();

			switch (assistanceTask) {
				case "1":
				case "monitor stations":
					schMngr.monitorStations();
					break;
				case "2":
				case "resolve issue":
					schMngr.resolveIssue();
					break;
				case "3":
				case "authorize age-restricted item":
					schMngr.authorizeAgeRestrictedItem();
					break;
				case "4":
				case "check scanner functionality":
					schMngr.checkScanner();
					break;
				case "5":
				case "route customer to another station":
					schMngr.routeCustomerToAnotherStation();
					break;
				case "6":
				case "call for additional support":
					schMngr.callForSupport();
					break;
				case "7":
				case "routine station check":
					schMngr.performRoutineCheck();
					break;
				case "8":
				case "investigate cash discrepancy":
					schMngr.investigateCashDiscrepancy();
					break;
				case "0":
				case "exit":
					inLoop = false;
					System.out.println("Exiting Self-Checkout Assistance...");
					break;
				default:
					System.out.println("Invalid option. Returning to assistance menu...");
			}
		}
	}

	public void handleCustomerSupport() {
		boolean inLoop = true;
		while (inLoop) {
			System.out.println("|----------------------------------------|");
			System.out.println("|     CUSTOMER SUPPORT AVAILABILITY     |");
			System.out.println("| 1. Greet Customer and Confirm Issue   |");
			System.out.println("| 2. Provide Product Information        |");
			System.out.println("| 3. Process Return Request             |");
			System.out.println("| 4. Check Order Status                 |");
			System.out.println("| 5. Escalate to Manager or Technician  |");
			System.out.println("| 6. Offer Alternative Solutions        |");
			System.out.println("| 7. Log Inquiry                         |");
			System.out.println("| 0. Exit                               |");
			System.out.println("|----------------------------------------|");
			System.out.print("Enter task to complete: ");
			String supportTask = in.nextLine().toLowerCase();

			switch (supportTask) {
				case "1":
				case "greet customer and confirm issue":
					csRep.greetCustomer(inq);
					break;
				case "2":
				case "provide product information":
					csRep.provideProductInformation();
					break;
				case "3":
				case "process return request":
					csRep.handleInquiry(inq);
					break;
				case "4":
				case "check order status":
					csRep.checkOrderStatus();
					break;
				case "5":
				case "escalate to manager or technician":
					csRep.escalateIssue(inq, supportTask);
					break;
				case "6":
				case "offer alternative solutions":
					csRep.offerAlternatives();
					break;
				case "7":
				case "log inquiry":
					csRep.logInquiry();
					break;
				case "0":
				case "exit":
					inLoop = false;
					System.out.println("Exiting Customer Support...");
					break;
				default:
					System.out.println("Invalid option. Returning to support menu...");
			}
		}
	}

	public void StockShelves() {
		ArrayList<String> steps = new ArrayList<>();
		steps.add("Walk through isles checking what products need stocking");
		steps.add("Go to backroom");
		steps.add("Find related stock");
		steps.add("If no stock found, report to manager");
		steps.add("Bring stock to isle");
		steps.add("Pull later expiration dates to front");
		steps.add("Put back stock behind any existing items");
		steps.add("If stock left over, return it to backroom");
		steps.add("Face up the product");
		steps.add("Throw away trash");
		steps.add("If trash is full, use compactor or take it out");
		Task task = new Task("Stocking", TaskType.STOCK, steps);
	}

	public void Clean() {
		ArrayList<String> steps = new ArrayList<>();
		steps.add("Go to an area to clean");
		steps.add("Go to area requested by manager or customer");
		steps.add("Determine if the area needs cleaning");
		steps.add("If the area is regularly used, clean it anyway");
		steps.add("Throw away any trash in area");
		steps.add("Take trash to compactor if your bag is full");
		steps.add("If compactor is full, run compactor");
		steps.add("Sanitize the area");
		steps.add("Wipe down area");
		steps.add("Once per day, after close, run floor cleaner machine");
		steps.add("Go to next area");

		Task task = new CleanTask("Cleaning", TaskType.CLEAN, steps);
	}

	public void handleSale() {
		System.out.println("Enter name for each item, or enter \"done\" to calculate total.");

		List<String> names = new ArrayList<>();
		List<Float> prices = new ArrayList<>();
		float subtotal = 0f;
		Map<String, Integer> items = new HashMap<>();
		o: for (int i = 1;; i++) {
			String name;
			float price;
			while (true) {
				System.out.printf("%d. ", i);
				name = in.nextLine();
				if ("done".equals(name))
					break o;
				if ("cancel".equals(name))
					return;
				price = invManager.inventorySystem.getPrice(name);

				if (Float.isNaN(price))
					System.out.printf("Error: no price for \"%s\".%n", name);
//				else if (invManager.inventorySystem.getStockLevel(name) <= invManager.inventorySystem.getStockPrices().get(name))
//					System.out.printf("Error: \"%s\" is out of stock.%n", name);
//				else

				subtotal += price;
				break;

			}


			names.add(name);
			prices.add(price);
			items.merge(name, 1, (a, b) -> a + b);
		}

		subtotal = 0.01f * Math.round(subtotal * 100f);
		float tax = 0.01f * Math.round(subtotal * 7f);
		while (true) {
			// payment and payment rejection are simulated
			System.out.printf("Subtotal: $%.2f%nTax: %.2f%nTotal: %.2f%nEnter account number: ", subtotal, tax,
					subtotal + tax);
			if ("cancel".equals(in.nextLine()))
				return;
			if (Math.random() < 0.9)
				break;
			System.out.println("Transaction declined (insufficient funds)");
		}
		for (Map.Entry<String, Integer> count : items.entrySet())
			invManager.inventorySystem.updateStock(count.getKey(),
					invManager.inventorySystem.getStockLevel(count.getKey()) - count.getValue());
		System.out.println("Thank you for shopping with us!");

		System.out.println();
		System.out.println("Receipt:");
		Receipt.create(items, invManager.inventorySystem).print();
	}

	public void handleReturn() {
		System.out.print("Enter item to return: ");
		String item = in.nextLine();
		System.out.print("Enter receipt ID: ");
		int rid = Integer.parseInt(in.nextLine());

		Receipt r = Receipt.lookup(rid);
		if (r == null) {
			System.out.println("Error: invalid receipt ID.");
			return;
		}
		float price = r.getPriceFor(item);
		if (Float.isNaN(price)) {
			System.out.println("Error: item is not on the given receipt.");
			return;
		}
		r.remove(item);
		System.out.printf("Refund applied: $%.2f%n", price * 1.07f);
	}
}
