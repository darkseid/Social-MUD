package com.smud.model.character;

import java.util.List;
import java.util.Random;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.smud.model.Dice;

public class AttributeGeneratorTest {

	private AttributeGenerator generator;
	
	@Before
	public void setUp() {
		generator = new AttributeGenerator();
	}
	
	@Test
	public void testGenerateSingleAttribute() throws Exception {
		Dice diceMock = Mockito.mock(Dice.class);
		Mockito.when(diceMock.roll(4, 6)).thenReturn(new int[] {3, 4, 5, 6});
		
		generator.setDice(diceMock);
		
		int result = generator.generateSingleAttribute();
		
		Assert.assertEquals(15, result);
	}
	
	@Test
	public void testGenerateRandomAttributes() throws Exception {
		Dice dice = new Dice();
		dice.setRandom(new Random());
		generator.setDice(dice);
		
		List<Integer> result = generator.generateRandomAttributes();
		Assert.assertEquals(6, result.size());
	}
}
