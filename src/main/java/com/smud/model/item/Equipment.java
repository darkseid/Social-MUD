package com.smud.model.item;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Equipment {

	private Map<ItemWearSlot, Item> items = new HashMap<ItemWearSlot, Item>();

	public Item getItem(ItemWearSlot itemWearSlot) {
		return items.get(itemWearSlot);
	}

	public boolean hasSlotAvailable(ItemWearPosition itemWearPosition) {
		List<ItemWearSlot> wearSlots = ItemWearSlot.findSlots(itemWearPosition);
		for (ItemWearSlot itemWearSlot : wearSlots) {
			if (getItem(itemWearSlot) == null) {
				return true;
			}
		}
		return false;
	}

	public void addItem(ItemWearPosition itemWearPosition, Item item) {
		List<ItemWearSlot> wearSlots = ItemWearSlot.findSlots(itemWearPosition);
		for (ItemWearSlot itemWearSlot : wearSlots) {
			if (getItem(itemWearSlot) == null) {
				items.put(itemWearSlot, item);
				return;
			}
		}
	}

	public int getApplyModifier(ItemApplies itemApplies) {
		int modifier = 0;
		for (ItemWearSlot itemWearSlot : ItemWearSlot.values()) {
			Item item = getItem(itemWearSlot);
			if (item != null) {
				int apply_value = item.getItemApplyValue(itemApplies);
				modifier += apply_value;
			}
		}
		return modifier;
	}
}
