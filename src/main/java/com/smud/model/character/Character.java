package com.smud.model.character;

import com.smud.model.Room;
import com.smud.model.command.Response;
import com.smud.model.item.Equipment;
import com.smud.model.item.Inventory;
import com.smud.model.item.ItemApplies;

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
	private Equipment equipment = new Equipment();
	
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
		return getBaseStrength() + equipment.getApplyModifier(ItemApplies.APPLY_STR);
	}
	
	public int getBaseStrength() {
		return strength;
	}

	public void setBaseStrength(int strength) {
		this.strength = strength;
	}

	public int getDexterity() {
		return getBaseDexterity() + equipment.getApplyModifier(ItemApplies.APPLY_DEX);
	}
	
	public int getBaseDexterity() {
		return dexterity;
	}

	public void setBaseDexterity(int dexterity) {
		this.dexterity = dexterity;
	}
	
	public int getConstitution() {
		return getBaseConstitution() + equipment.getApplyModifier(ItemApplies.APPLY_CON);
	}

	public int getBaseConstitution() {
		return constitution;
	}

	public void setBaseConstitution(int constitution) {
		this.constitution = constitution;
	}
	
	public int getIntelligence() {
		return getBaseIntelligence() + equipment.getApplyModifier(ItemApplies.APPLY_INT);
	}

	public int getBaseIntelligence() {
		return intelligence;
	}

	public void setBaseIntelligence(int intelligence) {
		this.intelligence = intelligence;
	}
	
	public int getWisdom() {
		return getBaseWisdom() + equipment.getApplyModifier(ItemApplies.APPLY_WIS);
	}

	public int getBaseWisdom() {
		return wisdom;
	}

	public void setBaseWisdom(int wisdom) {
		this.wisdom = wisdom;
	}
	
	public int getCharisma() {
		return getBaseCharisma() + equipment.getApplyModifier(ItemApplies.APPLY_CHA);
	}

	public int getBaseCharisma() {
		return charisma;
	}

	public void setBaseCharisma(int charisma) {
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
	
	public void decrementHitPoints(int amount) {
		this.hitPoints -= amount;
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
	
	public void setEquipment(Equipment equipment) {
		this.equipment = equipment;
	}
	
	public Equipment getEquipment() {
		return equipment;
	}
	
	public boolean isNameMatch(String name) {
		return this.name.toUpperCase().startsWith(name.toUpperCase());
	}
	
	public abstract boolean isPlayer();
	
	public abstract void addResponse(Response response);

	public abstract String getDescriptionToRoom();
	
	protected abstract void enterCurrentRoom();
	
	protected abstract void exitCurrentRoom();

}
