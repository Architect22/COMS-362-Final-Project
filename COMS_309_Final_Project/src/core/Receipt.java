package core;

import core.braden.InventorySystem;

import java.util.HashMap;
import java.util.Map;

public class Receipt
{
	public static Receipt create(Map<String, Integer> items, InventorySystem inventory)
	{
		Receipt r = new Receipt(items, inventory);
		receipts.put(r.rid, r);
		return r;
	}
	
	public static Receipt lookup(int id)
	{
		return receipts.get(id);
	}
	
	private static Map<Integer, Receipt> receipts = new HashMap<>();
	
	private static int nextRid = 0;
	private int rid;
	
	private Map<String, Integer> items;
	private Map<String, Float> prices;
	
	private Receipt(Map<String, Integer> items, InventorySystem inventory)
	{
		this.rid = nextRid++;
		this.items = new HashMap<>(items);
		this.prices = new HashMap<>();
		for (String name : items.keySet())
			prices.put(name, inventory.getPrice(name));
	}
	
	public float getPriceFor(String item)
	{
		return prices.getOrDefault(item, Float.NaN);
	}
	
	public void remove(String item)
	{
		int newAmt = items.get(item) - 1;
		if (newAmt == 0)
		{
			items.remove(item);
			prices.remove(item);
		}
		else
			items.put(item, newAmt);
	}
	
	public void print()
	{
		synchronized (System.out)
		{
			float subtotal = 0f;
			for (Map.Entry<String, Integer> entries : items.entrySet()) {
	//			invManager.inventorySystem.updateStock(names.get(i), invManager.inventorySystem.getStockLevel(names.get(i)) - 1);
				float price = prices.get(entries.getKey());
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