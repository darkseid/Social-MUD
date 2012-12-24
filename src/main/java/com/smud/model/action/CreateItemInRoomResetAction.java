package com.smud.model.action;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;

import com.smud.model.Keywords;
import com.smud.model.ResetAction;
import com.smud.model.Room;
import com.smud.model.definition.ItemDefinition;
import com.smud.model.item.Item;

public class CreateItemInRoomResetAction implements ResetAction<Room> {

	@Resource(name="textProperties")
	private Properties textProperties;
	
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
	
	public void setTextProperties(Properties textProperties) {
		this.textProperties = textProperties;
	}
	
	// TODO use an ItemFactory
	private Item createItem() {
		Item item = new Item();
		item.setCode(itemDefinition.getId());
		item.setZone(itemDefinition.getZone());
		item.setKeywords(createKeywords());
		item.setItemWearPosition(itemDefinition.getItemWearPosition());
		item.setItemApplies(itemDefinition.getItemApplies());
		return item;
	}
	
	//TODO single instance of Keywords for all items of the same kind
	private Keywords createKeywords() {
		Keywords keywords = new Keywords();
		keywords.setWords(Arrays.asList(textProperties.getProperty("item." + itemDefinition.getId() + ".keywords").split(Keywords.KEYWORD_SEPARATOR)));
		return keywords;
	}

	private int countNumberOfItems(List<Item> itemsInRoom, int id) {
		int count = 0;
		for (Item item : itemsInRoom) {
			if (item.getCode() == id){
				count++;
			}
		}
		return count;
	}

}
