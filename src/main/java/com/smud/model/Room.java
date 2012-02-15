package com.smud.model;

import java.util.ArrayList;
import java.util.List;


public class Room {

	private String title;
	
	private String description;
	
	// TODO change for a concurrent collection 
	private List<Player> players = new ArrayList<Player>();

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public List<Player> getPlayers() {
		return players;
	}
	
	public void addPlayer(Player player) {
		players.add(player);
	}
	
	public void removePlayer(Player player) {
		players.remove(player);
	}
	
	public void broadcast(String msg) {
		for (Player player : players) {
			player.addMessage(msg);
		}
	}
	
	
}
