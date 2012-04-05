package com.smud.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Item {

	private long id;
	private int code;
	
	private Zone zone;
	
	private Keywords keywords;
	
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
	
	@Override
	public boolean equals(Object obj) {
		boolean equals = false;
		if (obj instanceof Item) {
			Item other = (Item) obj;
			equals = new EqualsBuilder()
			.append(this.getCode(), other.getCode())
			.isEquals();
		}
		return equals;
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder()
		.append(getCode())
		.toHashCode();
	}

	@Override
	public String toString() {
		return "Item [id=" + id + ", code=" + code + ", keywords=" + keywords
				+ "]";
	}
	
}
