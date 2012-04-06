package com.smud.model.definition;

import com.smud.model.Zone;

public class MonsterDefinition {

	private static final int DEFAULT_ATTRIBUTE_VALUE = 11;
	
	private int id;
	private Zone zone;
	
	private int level;
	
	//Attributes
	private int strength = DEFAULT_ATTRIBUTE_VALUE;
	private int dexterity = DEFAULT_ATTRIBUTE_VALUE;
	private int constitution = DEFAULT_ATTRIBUTE_VALUE;
	private int intelligence = DEFAULT_ATTRIBUTE_VALUE;
	private int wisdom = DEFAULT_ATTRIBUTE_VALUE;
	private int charisma = DEFAULT_ATTRIBUTE_VALUE;
	
	private int maxHitPoints;
	
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
	
	public void setMaxHitPoints(int maxHitPoints) {
		this.maxHitPoints = maxHitPoints;
	}
	
	public int getMaxHitPoints() {
		return maxHitPoints;
	}
}
