package com.smud.model.character;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.smud.model.Dice;

public class AttributeGenerator {

	private static final int NUMBER_OF_ATTRIBUTES = 6;
	
	@Autowired
	private Dice dice;
	
	private Map<CharacterClass, AttributePriorityStrategy> attributePriorityStrategies = new HashMap<CharacterClass, AttributePriorityStrategy>();
	
	public void generateAttributes(Player player) {
		List<Integer> randomAttributes = generateRandomAttributes();
		Collections.sort(randomAttributes);
		AttributePriorityStrategy strategy = attributePriorityStrategies.get(player.getCharacterClass());
		strategy.distributeAttributes(player, randomAttributes);
	}
	
	public void setDice(Dice dice) {
		this.dice = dice;
	}
	
	public void setAttributePriorityStrategies(Map<CharacterClass, AttributePriorityStrategy> attributePriorityStrategies) {
		this.attributePriorityStrategies = attributePriorityStrategies;
	}
	
	private List<Integer> generateRandomAttributes() {
		List<Integer> attributes = new ArrayList<Integer>();
		for (int i = 0; i < NUMBER_OF_ATTRIBUTES; i++) {
			attributes.add(generateAttribute());
		}
		return attributes;
	}

	private Integer generateAttribute() {
		int[] diceResults = dice.roll(4, 6);
		int attribute = 0;
		int smallestResult = Integer.MAX_VALUE;
		for (int result : diceResults) {
			attribute += result;
			if (result < smallestResult) {
				smallestResult = result;
			}
		}
		attribute -= smallestResult;
		return attribute;
	}
}
