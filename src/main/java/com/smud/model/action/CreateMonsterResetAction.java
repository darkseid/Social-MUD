package com.smud.model.action;

import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;

import com.smud.model.Inventory;
import com.smud.model.ResetAction;
import com.smud.model.Room;
import com.smud.model.character.Monster;
import com.smud.model.definition.MonsterDefinition;

public class CreateMonsterResetAction implements ResetAction<Room> {
	
	@Resource(name="textProperties")
	private Properties textProperties;

	private MonsterDefinition monsterDefinition;
	
	private int maxQuantity;
	
	@Override
	public void execute(Room target) {
		List<Monster> monstersInRoom = target.getMonsters();
		int quantityOfMonstersOfSameType = countNumberOfMonsters(monstersInRoom, monsterDefinition.getId());
		if (quantityOfMonstersOfSameType < maxQuantity) {
			Monster monster = createMonster();
			monster.enters(target);
		}
	}
	
	public void setMonsterDefinition(MonsterDefinition monsterDefinition) {
		this.monsterDefinition = monsterDefinition;
	}
	
	public void setMaxQuantity(int maxQuantity) {
		this.maxQuantity = maxQuantity;
	}
	
	public void setTextProperties(Properties textProperties) {
		this.textProperties = textProperties;
	}
	
	private Monster createMonster() {
		Monster monster = new Monster();
		monster.setId(monsterDefinition.getId());
		monster.setStrength(monsterDefinition.getStrength());
		monster.setDexterity(monsterDefinition.getDexterity());
		monster.setConstitution(monsterDefinition.getConstitution());
		monster.setIntelligence(monsterDefinition.getIntelligence());
		monster.setWisdom(monsterDefinition.getWisdom());
		monster.setCharisma(monsterDefinition.getCharisma());
		monster.setName(textProperties.getProperty("monster." + monsterDefinition.getId() + ".name"));
		monster.setRoomDescription(textProperties.getProperty("monster." + monsterDefinition.getId() + ".room.description"));
		monster.setInventory(new Inventory());
		return monster;
	}
	
	private int countNumberOfMonsters(List<Monster> monstersInRoom, int id) {
		int count = 0;
		for (Monster monster : monstersInRoom) {
			if (monster.getId() == id){
				count++;
			}
		}
		return count;
	}
}

