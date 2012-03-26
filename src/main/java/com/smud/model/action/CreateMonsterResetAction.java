package com.smud.model.action;

import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;

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
		monster.setName(textProperties.getProperty("monster." + monsterDefinition.getId() + ".name"));
		monster.setRoomDescription(textProperties.getProperty("monster." + monsterDefinition.getId() + ".room.description"));
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

