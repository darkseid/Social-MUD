package com.smud.model;

import java.util.ArrayList;
import java.util.List;

public class Zone {

	private int id;
	
	private List<Room> rooms = new ArrayList<Room>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public List<Room> getRooms() {
		return rooms;
	}

	public void setRooms(List<Room> rooms) {
		this.rooms = rooms;
	}
	
}
