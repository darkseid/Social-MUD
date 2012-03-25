package com.smud.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.smud.model.character.Character;
import com.smud.model.command.Response;

public class Room implements Resetable {

	private int id;
	
	private Zone zone;
	
	private Map<Direction, Room> roomExits = new HashMap<Direction, Room>();
	
	// TODO change for a concurrent collection 
	private List<Character> characters = new ArrayList<Character>();
	
	private List<ResetAction<Room>> resetActions = new ArrayList<ResetAction<Room>>();

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

	public List<Character> getCharacters() {
		return characters;
	}
	
	public void addCharacter(Character character) {
		characters.add(character);
	}
	
	public void removeCharacter(Character character) {
		characters.remove(character);
	}
	
	public void addRoomExit(Direction direction, Room destinationRoom) {
		roomExits.put(direction, destinationRoom);
	}
	
	public void broadcast(Response response) {
		for (Character character : characters) {
			character.addResponse(response);
		}
	}

	public void sendToOtherCharacters(Response response, Character character) {
		for (Character characterInRoom : characters) {
			if (!characterInRoom.equals(character)){
				characterInRoom.addResponse(response);
			}
		}
	}
	
	public void reset() {
		for (ResetAction<Room> resetAction : resetActions) {
			resetAction.execute(this);
		}
	}
	
}
