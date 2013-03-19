package com.smud.model.character;

import java.text.MessageFormat;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.smud.model.command.Response;


public class Player extends Character {

	
	private String title;
	private PlayerClass playerClass;
	
	private Queue<Response> responses;

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}
	
	public PlayerClass getPlayerClass() {
		return playerClass;
	}
	
	public void setPlayerClass(PlayerClass characterClass) {
		this.playerClass = characterClass;
	}
	
	@Override
	public boolean isPlayer() {
		return true;
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
	public String getDescriptionToRoom() {
		//TODO use property key
		return MessageFormat.format("{0} {1} is here.", getName(), getTitle());
	}
	
	@Override
	protected void enterCurrentRoom() {
		getCurrentRoom().addPlayer(this);
	}

	@Override
	protected void exitCurrentRoom() {
		getCurrentRoom().removePlayer(this);
	}
	
	@Override
	public boolean equals(Object obj) {
		boolean equals = false;
		if (obj instanceof Player) {
			Player other = (Player) obj;
			equals = new EqualsBuilder()
			.append(this.getId(), other.getId())
			.append(this.getName(), other.getName())
			.append(this.title, other.title)
			.isEquals();
		}
		return equals;
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder()
		.append(getId())
		.append(getName())
		.append(getTitle())
		.toHashCode();
	}

	@Override
	public String toString() {
		return "Player [title=" + title + ", currentRoom ="
				+ getCurrentRoom().getId() + "]";
	}

}
