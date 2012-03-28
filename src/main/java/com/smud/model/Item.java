package com.smud.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.smud.model.character.Monster;
import com.smud.model.character.Player;

public class Item {

	private int id;
	
	private Zone zone;
	
	private Keywords keywords;
	
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
	
	public Keywords getKeywords() {
		return keywords;
	}
	
	public void setKeywords(Keywords keywords) {
		this.keywords = keywords;
	}
	
	@Override
	public boolean equals(Object obj) {
		boolean equals = false;
		if (obj instanceof Player) {
			Monster other = (Monster) obj;
			equals = new EqualsBuilder()
			.append(this.getId(), other.getId())
			.isEquals();
		}
		return equals;
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder()
		.append(getId())
		.toHashCode();
	}
}
