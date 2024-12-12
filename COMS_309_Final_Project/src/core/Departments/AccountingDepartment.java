package core.Departments;

import java.util.Scanner;

import core.Receipt;
import core.Utility;

public class AccountingDepartment extends Department
{
	private static AccountingDepartment instance;
	
	private AccountingDepartment()
	{
		super("Accounting Department");
	}
	
	public static AccountingDepartment getInstance()
	{
		if (instance == null)
			instance = new AccountingDepartment();
		return instance;
	}
	
	@Override
	@SuppressWarnings("resource")
	public void displayDepartmentTasks()
	{
		Utility.createHeader(width, "Accounting Department");
		System.out.println("| 1. Shareholder Report (CFO only)                             |");
		System.out.println("| 2. Forecast Sales                                            |");
		System.out.println("|--------------------------------------------------------------|");
		Scanner in = new Scanner(System.in);
		switch (Integer.parseInt(in.nextLine()))
		{
			case 1:
			makeShareholderReport(in);
			break;
			
			case 2:
			forecastSales(in);
			break;
		}
	}
	
	private float accountBalance = 0f;
	
	private float sales = 0f;
	private float costOfGoods = -0f;
	private float salary = -0f;
	
	public void addSale(float amt)
	{
		sales += amt;
		accountBalance += amt;
	}
	
	public void addProductCost(float amt)
	{
		costOfGoods -= amt;
		accountBalance -= amt;
	}
	
	public void addSalary(float amt)
	{
		salary -= amt;
		accountBalance -= amt;
	}
	
	private void makeShareholderReport(Scanner in)
	{
		float grossProfit = sales + costOfGoods + salary;
		System.out.printf(
			"Sales:%+22.2f%n" +
			"Cost of Goods:%+14.2f%n" +
			"Salary and Wages:%+11.2f%n" +
			"----------------------------%n" +
			"Total Revenue:%+14.2f%n" +
			"Total Expenditures:%+9.2f%n" +
			"Gross Profit:%+15.2f%n" +
			"Tax:%+24.2f%n" +
			"Net Profit:%+17.2f%n",
			sales, costOfGoods, salary, sales, costOfGoods + salary, grossProfit, -0f, grossProfit);
		if (accountBalance < 0)
		{
			System.out.println("Corporate account is insolvent. Take loan? (y/n)");
			if ("y".equals(in.nextLine()))
				System.out.println("Loan taken.");
		}
		else
		{
			System.out.print("Enter balance to keep in the corporate account: ");
			float balanceKept = Float.parseFloat(in.nextLine());
			System.out.print("Enter number of shares: ");
			int numShares = Integer.parseInt(in.nextLine());
			System.out.printf("Dividend per Share:%9.2f%n", (grossProfit - balanceKept) / numShares);
		}
		
		sales = 0;
		costOfGoods = 0;
		salary = 0;
	}
	
	private static void forecastSales(Scanner in)
	{
		System.out.print("Enter item to make a sales forecast: ");
		String item = in.nextLine();
		System.out.print("Enter number of days to consider: ");
		long durationBack = (long)(1000.0 * 3600.0 * 24.0 * Double.parseDouble(in.nextLine()));
		long minTime = System.currentTimeMillis() - durationBack;
		
		int total = 0;
		for (Receipt r : Receipt.getAll())
			if (r.getTimestamp() > minTime)
				total += r.getCountOf(item);
		System.out.printf("Total units sold (during timeframe given): %d%n", total);
		System.out.print("Enter days to forecast: ");
		long durationForward = (long)(1000.0 * 3600.0 * 24.0 * Double.parseDouble(in.nextLine()));
		double rate = total * durationForward / (double)durationBack;
		System.out.printf("Forecast: %.2f\u00b1%.2f%n", rate, Math.sqrt(rate));
	}
}