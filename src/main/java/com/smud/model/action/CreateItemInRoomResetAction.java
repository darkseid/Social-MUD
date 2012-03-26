package com.smud.model.action;

import java.util.List;

import com.smud.model.Item;
import com.smud.model.ResetAction;
import com.smud.model.Room;
import com.smud.model.definition.ItemDefinition;

public class CreateItemInRoomResetAction implements ResetAction<Room> {

	private ItemDefinition itemDefinition;
	
	private int maxQuantity;
	
	@Override
	public void execute(Room target) {
		List<Item> itemsInRoom = target.getItems();
		int quantityOfItemsOfSameType = countNumberOfItems(itemsInRoom, itemDefinition.getId());
		if (quantityOfItemsOfSameType < maxQuantity) {
			Item item = createItem();
			target.addItem(item);
		}
	}
	
	public void setItemDefinition(ItemDefinition itemDefinition) {
		this.itemDefinition = itemDefinition;
	}
	
	public void setMaxQuantity(int maxQuantity) {
		this.maxQuantity = maxQuantity;
	}
	
	private Item createItem() {
		Item item = new Item();
		item.setId(itemDefinition.getId());
		item.setZone(itemDefinition.getZone());
		return item;
	}
	
	private int countNumberOfItems(List<Item> itemsInRoom, int id) {
		int count = 0;
		for (Item item : itemsInRoom) {
			if (item.getId() == id){
				count++;
			}
		}
		return count;
	}

}
