package com.smud.model.character;

import java.util.List;

public class ThiefAttributePriorityStrategy implements AttributePriorityStrategy {

	@Override
	public void distributeAttributes(Player player, List<Integer> attributes) {
		player.setBaseDexterity(attributes.remove(attributes.size() - 1));
		player.setBaseStrength(attributes.remove(attributes.size() - 1));
		player.setBaseConstitution(attributes.remove(attributes.size() - 1));
		player.setBaseIntelligence(attributes.remove(attributes.size() - 1));
		player.setBaseWisdom(attributes.remove(attributes.size() - 1));
		player.setBaseCharisma(attributes.remove(attributes.size() - 1));
	}

}
