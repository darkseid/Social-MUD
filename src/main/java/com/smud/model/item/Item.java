package com.smud.model.item;

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
	
}
