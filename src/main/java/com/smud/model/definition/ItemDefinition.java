package com.smud.model.definition;

import com.smud.model.Zone;
import com.smud.model.item.ItemWearPosition;

public class ItemDefinition {

	private int id;
	private Zone zone;
	
	private ItemWearPosition itemWearPosition;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Zone getZone() {
		return zone;
	}

	public void setZone(Zone zone) {
		this.zone = zone;
	}
	
	public ItemWearPosition getItemWearPosition() {
		return itemWearPosition;
	}
	
	public void setItemWearPosition(ItemWearPosition itemWearPosition) {
		this.itemWearPosition = itemWearPosition;
	}
	
}
