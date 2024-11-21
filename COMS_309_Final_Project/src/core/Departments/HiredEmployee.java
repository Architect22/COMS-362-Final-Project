package core.Departments;

public class HiredEmployee
{
	private static int nextId = 0;
	
	private int id;
	private String name;
	private String ssn;
	private float pay;
	private float withheld;
	
	public HiredEmployee(String name, String ssn, float pay, float withheld)
	{
		this.id = nextId++;
		this.name = name;
		this.ssn = ssn;
		this.pay = pay;
		this.withheld = withheld;
	}
	
	public int getID()
	{
		return id;
	}

	public String getName()
	{
		return name;
	}
	
	public String getSSN()
	{
		return ssn;
	}
	
	public float getPay()
	{
		return pay;
	}
	
	public float getTaxWithholding()
	{
		return withheld;
	}
}
