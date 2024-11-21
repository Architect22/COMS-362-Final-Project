package core.Departments;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

import core.Utility;

public class HumanResourcesDepartment extends Department
{
	private static HumanResourcesDepartment instance;
	
	public HumanResourcesDepartment()
	{
		super("Human Resources Department");
	}
	
	public static HumanResourcesDepartment getInstance()
	{
		if (instance == null)
			instance = new HumanResourcesDepartment();
		return instance;
	}
	
	private Map<Integer, HiredEmployee> employees = new HashMap<>();
	
	@Override
	@SuppressWarnings("resource")
	public void displayDepartmentTasks()
	{
		Utility.createHeader(width, "Human Resources Department");
		System.out.println("| 1. Hire Employee                                             |");
		System.out.println("| 2. Handle Payroll                                            |");
		System.out.println("|--------------------------------------------------------------|");
		Scanner in = new Scanner(System.in);
		switch (Integer.parseInt(in.nextLine()))
		{
		case 1:
			hireEmployee(in);
			break;
		case 2:
			handlePayroll(in);
			break;
		}
	}
	
	private void hireEmployee(Scanner in)
	{
		while (true)
		{
			System.out.print("Enter employee name: ");
			String name = in.nextLine();
			if ("cancel".equals(name))
				return;
			
			System.out.print("Enter employee social security number: ");
			String ssn = in.nextLine();
			if (!Pattern.matches("^\\d\\d\\d-\\d\\d-\\d\\d\\d\\d$", ssn))
			{
				System.out.println("Invalid social security number: " + ssn);
				continue;
			}
			
			System.out.print("Enter employee hourly rate: ");
			float pay;
			try
			{
				pay = Float.parseFloat(in.nextLine());
				if (pay < 7.25)
				{
					System.out.println("Hourly rate is too low.");
					continue;
				}
			}
			catch (NumberFormatException e)
			{
				System.out.println("Invalid pay rate.");
				continue;
			}
			
			System.out.print("Enter employee tax withholding percentage: ");
			float withheld;
			try
			{
				withheld = Float.parseFloat(in.nextLine());
				if (withheld < 0f || withheld > 100f)
				{
					System.out.println("Tax withholding is outside allowed range.");
					continue;
				}
			}
			catch (NumberFormatException e)
			{
				System.out.println("Invalid withholding %.");
				continue;
			}
			
			HiredEmployee e = new HiredEmployee(name, ssn, pay, withheld / 100f);
			employees.put(e.getID(), e);
			System.out.printf("Created new employee with employee id %d.%n", e.getID());
			return;
		}
	}
	
	private void handlePayroll(Scanner in)
	{
		System.out.print("Enter employee number: ");
		HiredEmployee employee;
		try
		{
			employee = employees.get(Integer.parseInt(in.nextLine()));
			if (employee == null)
			{
				System.out.println("No employee exists with that ID.");
				return;
			}
		}
		catch (NumberFormatException e)
		{
			System.out.println("Invalid employee ID.");
			return;
		}
		
		System.out.print("Enter hours worked: ");
		float hoursWorked;
		try
		{
			hoursWorked = Float.parseFloat(in.nextLine());
			if (hoursWorked < 0)
			{
				System.out.println("Cannot pay negative hours.");
				return;
			}
		}
		catch (NumberFormatException e)
		{
			System.out.println("Invalid employee ID.");
			return;
		}
		
		float pay = employee.getPay() * hoursWorked;
		System.out.printf("%s hours worked: $%.2f%n", hoursWorked, pay);
		if (hoursWorked > 40)
		{
			float overtimePay = 0.5f * employee.getPay() * (hoursWorked - 40f);
			System.out.printf("%s hours overtime: +$%.2f%n", hoursWorked - 40f, overtimePay);
			pay += overtimePay;
			System.out.printf("Total gross pay: $%.2f%n", pay);
		}
		float tax = pay * employee.getTaxWithholding();
		System.out.printf("Income tax withheld: -$%.2f%nNet pay: $%.2f%n", tax, pay - tax);
	}
}