package com.smud.model.item;

import java.util.ArrayList;
import java.util.List;

public enum ItemWearSlot {

	LIGHT(ItemWearPosition.LIGHT),
	HEAD(ItemWearPosition.HEAD),
	EAR_RIGHT(ItemWearPosition.EAR),
	EAR_LEFT(ItemWearPosition.EAR),
	FACE(ItemWearPosition.FACE),
	NOSE(ItemWearPosition.NOSE),
	NECK_1(ItemWearPosition.NECK),
	NECK_2(ItemWearPosition.NECK),
	BODY(ItemWearPosition.BODY),
	ARMS(ItemWearPosition.ARMS),
	HANDS(ItemWearPosition.HANDS),
	WRIST_RIGHT(ItemWearPosition.WRIST),
	WRIST_LEFT(ItemWearPosition.WRIST),
	FINGER_RIGHT(ItemWearPosition.FINGER),
	FINGER_LEFT(ItemWearPosition.FINGER),
	WAIST(ItemWearPosition.WAIST),
	LEGS(ItemWearPosition.LEGS),
	FEET(ItemWearPosition.FEET),
	ABOUT(ItemWearPosition.ABOUT),
	SHIELD(ItemWearPosition.SHIELD),
	WIELD(ItemWearPosition.WIELD),
	HOLD(ItemWearPosition.HOLD),
	WINGS(ItemWearPosition.WINGS),
	INSIGNE(ItemWearPosition.INSIGNE);
	
	private ItemWearPosition itemWearPosition;
	
	private ItemWearSlot(ItemWearPosition itemWearPosition) {
		this.itemWearPosition = itemWearPosition;
	}
	
	public ItemWearPosition getItemWearPosition() {
		return itemWearPosition;
	}

	public static List<ItemWearSlot> findSlots(ItemWearPosition itemWearPosition) {
		List<ItemWearSlot> slots = new ArrayList<ItemWearSlot>();
		for (ItemWearSlot itemWearSlot : ItemWearSlot.values()) {
			if (itemWearSlot.getItemWearPosition().equals(itemWearPosition)) {
				slots.add(itemWearSlot);
			}
 		}
		return slots;
	}
}
