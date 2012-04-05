package com.smud.model.character;

import java.util.List;

public class MageAttributePriorityStrategy implements AttributePriorityStrategy {

	@Override
	public void distributeAttributes(Player player, List<Integer> attributes) {
		player.setIntelligence(attributes.remove(attributes.size() - 1));
		player.setWisdom(attributes.remove(attributes.size() - 1));
		player.setDexterity(attributes.remove(attributes.size() - 1));
		player.setStrength(attributes.remove(attributes.size() - 1));
		player.setConstitution(attributes.remove(attributes.size() - 1));
		player.setCharisma(attributes.remove(attributes.size() - 1));
	}

}
