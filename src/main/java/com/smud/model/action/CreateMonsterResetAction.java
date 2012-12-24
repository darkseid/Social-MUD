package com.smud.model.action;

import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;

import com.smud.model.ResetAction;
import com.smud.model.Room;
import com.smud.model.character.Monster;
import com.smud.model.definition.MonsterDefinition;
import com.smud.model.item.Inventory;

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
		monster.setLevel(monsterDefinition.getLevel());
		monster.setBaseStrength(monsterDefinition.getStrength());
		monster.setBaseDexterity(monsterDefinition.getDexterity());
		monster.setBaseConstitution(monsterDefinition.getConstitution());
		monster.setBaseIntelligence(monsterDefinition.getIntelligence());
		monster.setBaseWisdom(monsterDefinition.getWisdom());
		monster.setBaseCharisma(monsterDefinition.getCharisma());
		monster.setMaxHitPoints(monsterDefinition.getMaxHitPoints());
		monster.setMaxManaPoints(10);
		monster.setMaxMovementPoints(50);
		monster.setHitPoints(monster.getMaxHitPoints());
		monster.setManaPoints(monster.getMaxManaPoints());
		monster.setMovementPoints(monster.getMovementPoints());
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

