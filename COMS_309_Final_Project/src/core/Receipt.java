package core;

import java.util.HashMap;
import java.util.Map;

public class Receipt
{
	public static Receipt create(Map<String, Integer> items)
	{
		Receipt r = new Receipt(items);
		receipts.put(r.rid, r);
		return r;
	}
	
	private static Map<Integer, Receipt> receipts = new HashMap<>();
	
	private static int nextRid = 0;
	private int rid;
	
	private Map<String, Integer> items;
	
	private Receipt(Map<String, Integer> items)
	{
		this.rid = nextRid++;
		this.items = new HashMap<>(items);
	}
	
	public void print(InventorySystem inv)
	{
		synchronized (System.out)
		{
			float subtotal = 0f;
			for (Map.Entry<String, Integer> entries : items.entrySet()) {
	//			invManager.inventorySystem.updateStock(names.get(i), invManager.inventorySystem.getStockLevel(names.get(i)) - 1);
				float price = inv.getPrice(entries.getKey());
				subtotal += price * entries.getValue();
				
				System.out.printf("%-19s %10s", entries.getKey(), String.format("$%.2f", price));
				if (entries.getValue() > 1)
					System.out.printf(" x%d", entries.getValue());
				System.out.println();
			}
			
			subtotal = 0.01f * Math.round(subtotal * 100f);
			float tax = 0.01f * Math.round(subtotal * 7f);
			System.out.printf(
				"------------------------------%n" +
				"Subtotal            %10s%n" +
				"Tax                 %10s%n" +
				"Total               %10s%n" +
				"Receipt ID: %d%n",
				String.format("$%.2f", subtotal), String.format("$%.2f", tax), String.format("$%.2f", subtotal + tax), rid);
		}
	}
}