package com.smud.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.smud.model.character.Character;
import com.smud.model.character.Monster;
import com.smud.model.character.Player;
import com.smud.model.command.Response;
import com.smud.model.item.Item;

public class Room implements Resetable {

	private int id;
	
	private Zone zone;
	
	private Map<Direction, Room> roomExits = new HashMap<Direction, Room>();
	
	// TODO change for a concurrent collection 
	private List<Player> players = new ArrayList<Player>();
	
	// TODO change for a concurrent collection 
	private List<Monster> monsters = new ArrayList<Monster>();
	
	private List<ResetAction<Room>> resetActions = new ArrayList<ResetAction<Room>>();

	private List<Item> items = new ArrayList<Item>();

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
	
	public List<ResetAction<Room>> getResetActions() {
		return resetActions;
	}
	
	public void setResetActions(List<ResetAction<Room>> resetActions) {
		this.resetActions = resetActions;
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

	public List<Monster> getMonsters() {
		return monsters;
	}
	
	public void addMonster(Monster monster) {
		monsters.add(monster);
	}
	
	public void removeMonster(Monster monster) {
		monsters.remove(monster);
	}
	
	public void addRoomExit(Direction direction, Room destinationRoom) {
		roomExits.put(direction, destinationRoom);
	}
	
	public void broadcast(Response response) {
		for (Character character : players) {
			character.addResponse(response);
		}
	}

	public void sendToOtherCharacters(Response response, Character... characters) {
		sendToOtherCharacters(response, new HashSet<Character>(Arrays.asList(characters)));
	}
	
	public void sendToOtherCharacters(Response response, Set<Character> characters) {
		for (Character characterInRoom : players) {
			if (!characters.contains(characterInRoom)){
				characterInRoom.addResponse(response);
			}
		}
	}
	
	public Character getCharacter(String name, Character actor) {
		for (Player player : players) {
			if (player.isNameMatch(name)) {
				return player;
			}
		}
		for (Monster monster : monsters) {
			if (monster.isNameMatch(name)) {
				return monster;
			}
		}
		return null;
	}
	
	public void reset() {
		for (ResetAction<Room> resetAction : resetActions) {
			resetAction.execute(this);
		}
	}

	public List<Item> getItems() {
		return items;
	}
	
	public void addItem(Item item) {
		this.items.add(item);
	}

	public void removeItem(Item item) {
		this.items.remove(item);
	}
	
}
