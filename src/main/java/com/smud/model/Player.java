package com.smud.model;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.smud.model.command.Response;


public class Player {

	private int id;
	private String name;
	private String title;
	private Room inRoom;
	private Queue<Response> responses;

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
	
	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public Room getInRoom() {
		return inRoom;
	}

	public void setInRoom(Room inRoom) {
		this.inRoom = inRoom;
		inRoom.addPlayer(this);
	}
	
	public void addResponse(Response response) {
		synchronized (this) {
			if (responses == null) {
				responses = new LinkedBlockingQueue<Response>();
			} 
		}
		responses.add(response);
	}
	
	public Response getResponse() {
		if (responses == null) {
			return null;
		}
		
		return responses.poll();
	}
	
	@Override
	public boolean equals(Object obj) {
		boolean equals = false;
		if (obj instanceof Player) {
			Player other = (Player) obj;
			equals = new EqualsBuilder()
			.append(this.id, other.id)
			.append(this.name, other.name)
			.append(this.title, other.title)
			.isEquals();
		}
		return equals;
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder()
		.append(id)
		.append(name)
		.append(title)
		.toHashCode();
	}
}
