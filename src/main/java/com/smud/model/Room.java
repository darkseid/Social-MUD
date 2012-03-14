package com.smud.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Room {

	private int id;
	
	private Map<Direction, Room> roomExits = new HashMap<Direction, Room>();
	
	// TODO change for a concurrent collection 
	private List<Player> players = new ArrayList<Player>();

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public Map<Direction, Room> getRoomExits() {
		return roomExits;
	}
	
	public void setRoomExits(Map<Direction, Room> roomExits) {
		this.roomExits = roomExits;
	}
	
	public Room getRoomExit(Direction direction){
		return roomExits.get(direction);
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
	
	public void addRoomExit(Direction direction, Room destinationRoom) {
		roomExits.put(direction, destinationRoom);
	}
	
	public void broadcast(String message) {
		for (Player player : players) {
			player.addMessage(message);
		}
	}

	public void sendToOtherPlayers(String message, Player player) {
		for (Player playerInRoom : players) {
			if (!playerInRoom.equals(player)){
				playerInRoom.addMessage(message);
			}
		}
	}
	
}
