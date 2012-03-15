package com.smud.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.smud.model.command.Response;


public class Room {

	private int id;
	
	private Zone zone;
	
	private Map<Direction, Room> roomExits = new HashMap<Direction, Room>();
	
	// TODO change for a concurrent collection 
	private List<Player> players = new ArrayList<Player>();

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public Zone getZone() {
		return zone;
	}

	public void setZone(Zone zone) {
		this.zone = zone;
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
	
	public void broadcast(Response response) {
		for (Player player : players) {
			player.addResponse(response);
		}
	}

	public void sendToOtherPlayers(Response response, Player player) {
		for (Player playerInRoom : players) {
			if (!playerInRoom.equals(player)){
				playerInRoom.addResponse(response);
			}
		}
	}
	
}
