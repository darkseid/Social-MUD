package com.smud.model.character;

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

}
