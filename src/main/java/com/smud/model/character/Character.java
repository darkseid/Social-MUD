package com.smud.model.character;

import com.smud.model.Inventory;
import com.smud.model.Room;
import com.smud.model.command.Response;

public abstract class Character {

	private long id;
	private String name;
	
	private int level;
	
	//Attributes
	private int strength;
	private int dexterity;
	private int constitution;
	private int intelligence;
	private int wisdom;
	private int charisma;
	
	//Points
	private int maxHitPoints;
	private int maxManaPoints;
	private int maxMovementPoints;
	private int hitPoints;
	private int manaPoints;
	private int movementPoints;
	
	private Room currentRoom;
	private Inventory inventory = new Inventory();
	
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
	
	public int getLevel() {
		return level;
	}
	
	public void setLevel(int level) {
		this.level = level;
	}
	
	public int getStrength() {
		return strength;
	}

	public void setStrength(int strength) {
		this.strength = strength;
	}

	public int getDexterity() {
		return dexterity;
	}

	public void setDexterity(int dexterity) {
		this.dexterity = dexterity;
	}

	public int getConstitution() {
		return constitution;
	}

	public void setConstitution(int constitution) {
		this.constitution = constitution;
	}

	public int getIntelligence() {
		return intelligence;
	}

	public void setIntelligence(int intelligence) {
		this.intelligence = intelligence;
	}

	public int getWisdom() {
		return wisdom;
	}

	public void setWisdom(int wisdom) {
		this.wisdom = wisdom;
	}

	public int getCharisma() {
		return charisma;
	}

	public void setCharisma(int charisma) {
		this.charisma = charisma;
	}
	
	public int getMaxHitPoints() {
		return maxHitPoints;
	}

	public void setMaxHitPoints(int maxHitPoints) {
		this.maxHitPoints = maxHitPoints;
	}

	public int getMaxManaPoints() {
		return maxManaPoints;
	}

	public void setMaxManaPoints(int maxManaPoints) {
		this.maxManaPoints = maxManaPoints;
	}

	public int getMaxMovementPoints() {
		return maxMovementPoints;
	}

	public void setMaxMovementPoints(int maxMovementPoints) {
		this.maxMovementPoints = maxMovementPoints;
	}

	public int getHitPoints() {
		return hitPoints;
	}

	public void setHitPoints(int hitPoints) {
		this.hitPoints = hitPoints;
	}

	public int getManaPoints() {
		return manaPoints;
	}

	public void setManaPoints(int manaPoints) {
		this.manaPoints = manaPoints;
	}

	public int getMovementPoints() {
		return movementPoints;
	}

	public void setMovementPoints(int movementPoints) {
		this.movementPoints = movementPoints;
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
	
	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}
	
	public Inventory getInventory() {
		return inventory;
	}
	
	public abstract void addResponse(Response response);

	public abstract String getDescriptionToRoom();
	
	protected abstract void enterCurrentRoom();
	
	protected abstract void exitCurrentRoom();
}
