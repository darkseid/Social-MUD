package com.smud.model.item;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.smud.model.Keywords;
import com.smud.model.Zone;

public class Item {

	private long id;
	private int code;
	
	private Zone zone;
	
	private Keywords keywords;
	
	private ItemWearPosition itemWearPosition;
	private Map<ItemApplies, Integer> itemApplies = new HashMap<ItemApplies, Integer>();
	
	public int getCode() {
		return code;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setCode(int code) {
		this.code = code;
	}
	
	public Zone getZone() {
		return zone;
	}
	
	public void setZone(Zone zone) {
		this.zone = zone;
	}
	
	public Keywords getKeywords() {
		return keywords;
	}
	
	public void setKeywords(Keywords keywords) {
		this.keywords = keywords;
	}
	
	public void setItemWearPosition(ItemWearPosition itemWearPosition) {
		this.itemWearPosition = itemWearPosition;
	}
	
	public ItemWearPosition getItemWearPosition() {
		return itemWearPosition;
	}
	
	public Map<ItemApplies, Integer> getItemApplies() {
		return itemApplies;
	}
	
	public int getItemApplyValue(ItemApplies applies) {
		Integer applyValue = itemApplies.get(applies);
		return applyValue != null ? applyValue : 0;
	}
	
	public void setItemApplies(Map<ItemApplies, Integer> itemApplies) {
		this.itemApplies = itemApplies;
	}
	
	@Override
	public boolean equals(Object obj) {
		boolean equals = false;
		if (obj instanceof Item) {
			Item other = (Item) obj;
			equals = new EqualsBuilder()
			.append(this.getCode(), other.getCode())
			.append(this.getItemWearPosition(), other.getItemWearPosition())
			.isEquals();
		}
		return equals;
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder()
		.append(getCode())
		.append(getItemWearPosition())
		.toHashCode();
	}

	@Override
	public String toString() {
		return "Item [id=" + id + ", code=" + code + ", keywords=" + keywords
				+ "]";
	}

	public boolean isWieldable() {
		return ItemWearPosition.WIELD.equals(itemWearPosition);
	}
	
	public boolean isHoldable() {
		return ItemWearPosition.LIGHT.equals(itemWearPosition) ||
		ItemWearPosition.HOLD.equals(itemWearPosition);
	}
	
	public boolean isWearable() {
		return ItemWearPosition.WEARABLE_POSITIONS.contains(itemWearPosition);
	}
	
}
