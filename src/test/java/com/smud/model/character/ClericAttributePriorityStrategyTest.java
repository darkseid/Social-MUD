package com.smud.model.character;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

public class ClericAttributePriorityStrategyTest {

	private ClericAttributePriorityStrategy strategy;
	
	@Before
	public void setUp() {
		strategy = new ClericAttributePriorityStrategy();
	}
	
	@Test
	public void testDistributeAttributes() throws Exception {
		Player player = new Player();
		List<Integer> attributes = new ArrayList<Integer>();
		attributes.add(12);
		attributes.add(13);
		attributes.add(14);
		attributes.add(15);
		attributes.add(16);
		attributes.add(17);
		
		strategy.distributeAttributes(player, attributes);
		
		Assert.assertEquals(17, player.getWisdom());
		Assert.assertEquals(16, player.getIntelligence());
		Assert.assertEquals(15, player.getStrength());
		Assert.assertEquals(14, player.getDexterity());
		Assert.assertEquals(13, player.getConstitution());
		Assert.assertEquals(12, player.getCharisma());
	}
}
