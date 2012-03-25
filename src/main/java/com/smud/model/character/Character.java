package com.smud.model.character;

import com.smud.model.Room;
import com.smud.model.command.Response;

public abstract class Character {

	private long id;
	private String name;
	private Room currentRoom;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	

	public Room getCurrentRoom() {
		return currentRoom;
	}

	public void enters(Room inRoom) {
		if ( currentRoom != null ) {
			exitCurrentRoom();
		}
		this.currentRoom = inRoom;
		enterCurrentRoom();
	}


	public abstract void addResponse(Response response);

	public abstract String getDescriptionToRoom();
	
	protected abstract void enterCurrentRoom();
	
	protected abstract void exitCurrentRoom();
}
