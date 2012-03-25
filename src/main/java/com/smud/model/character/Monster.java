package com.smud.model.character;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.smud.model.command.Response;

public class Monster extends Character {

	private String roomDescription;
	
	public String getRoomDescription() {
		return roomDescription;
	}
	
	public void setRoomDescription(String roomDescription) {
		this.roomDescription = roomDescription;
	}
	
	@Override
	public void addResponse(Response response) {
		//TODO keep record of response only if monster is being controlled by an immortal
	}

	@Override
	public String getDescriptionToRoom() {
		return roomDescription;
	}
	
	@Override
	protected void enterCurrentRoom() {
		getCurrentRoom().addMonster(this);
	}

	@Override
	protected void exitCurrentRoom() {
		getCurrentRoom().removeMonster(this);
	}
	
	@Override
	public boolean equals(Object obj) {
		boolean equals = false;
		if (obj instanceof Player) {
			Monster other = (Monster) obj;
			equals = new EqualsBuilder()
			.append(this.getId(), other.getId())
			.append(this.getName(), other.getName())
			.append(this.getRoomDescription(), other.getRoomDescription())
			.isEquals();
		}
		return equals;
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder()
		.append(getId())
		.append(getName())
		.append(getRoomDescription())
		.toHashCode();
	}

}
