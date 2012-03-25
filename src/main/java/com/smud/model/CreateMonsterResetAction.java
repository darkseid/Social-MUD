package com.smud.model;

import java.util.Properties;

import javax.annotation.Resource;

import com.smud.model.character.Monster;
import com.smud.model.definition.MonsterDefinition;

public class CreateMonsterResetAction implements ResetAction<Room> {

	private MonsterDefinition monsterDefinition;
	
	@Resource(name="monsterProperties")
	private Properties monstersProperties;
	
	@Override
	public void execute(Room target) {
		Monster monster = createMonster();
		target.addCharacter(monster);
	}
	
	public MonsterDefinition getMonsterDefinition() {
		return monsterDefinition;
	}
	
	public void setMonsterDefinition(MonsterDefinition monsterDefinition) {
		this.monsterDefinition = monsterDefinition;
	}

	private Monster createMonster() {
		Monster monster = new Monster();
		monster.setId(monsterDefinition.getId());
		monster.setName(monstersProperties.getProperty("monster." + monsterDefinition.getId() + ".name"));
		monster.setRoomDescription(monstersProperties.getProperty("monster." + monsterDefinition.getId() + ".room.description"));
		return monster;
	}

}
