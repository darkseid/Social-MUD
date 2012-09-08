package com.smud.model.item;

import java.util.ArrayList;
import java.util.List;

public class Inventory {

	private List<Item> items = new ArrayList<Item>();
	
	public void addItem(Item item) {
		items.add(item);
	}
	
	public void removeItem(Item item) {
		items.remove(item);
	}
	
	public List<Item> getItems() {
		return items;
	}
	
	public Item findItem(String keyword) {
		Item itemFound = null;
		for (Item item : items) {
			if (item.getKeywords().match(keyword)) {
				itemFound = item;
				break;
			}
		}
		return itemFound;
	}
}
