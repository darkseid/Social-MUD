package com.smud.model;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;


public class Player {

	private int id;
	private String name;
	private Room inRoom;
	private Queue<String> messages;

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
		inRoom.addPlayer(this);
	}
	
	public void addMessage(String message) {
		synchronized (this) {
			if (messages == null) {
				messages = new LinkedBlockingQueue<String>();
			} 
		}
		messages.add(message);
	}
	
	public String getMessage() {
		if (messages == null) {
			return null;
		}
		
		return messages.poll();
	}
	
	@Override
	public boolean equals(Object obj) {
		boolean equals = false;
		if (obj instanceof Player) {
			Player other = (Player) obj;
			equals = new EqualsBuilder()
			.append(this.id, other.id)
			.append(this.name, other.name)
			.isEquals();
		}
		return equals;
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder()
		.append(id)
		.append(name)
		.toHashCode();
	}
}
