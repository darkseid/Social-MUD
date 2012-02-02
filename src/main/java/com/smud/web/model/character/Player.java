package com.smud.web.model.character;

import com.smud.web.model.world.Room;

public class Player {

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
	}
	
}
