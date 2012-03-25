package com.smud.model.character;

import com.smud.model.Room;
import com.smud.model.command.Response;

public abstract class Character {

	private int id;
	private String name;
	private Room inRoom;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	

	public Room getInRoom() {
		return inRoom;
	}

	public void setInRoom(Room inRoom) {
		this.inRoom = inRoom;
		inRoom.addCharacter(this);
	}

	public abstract void addResponse(Response response);

	public abstract String getDescriptionToRoom();
}
