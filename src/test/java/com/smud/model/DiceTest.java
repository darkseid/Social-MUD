package com.smud.model;

import java.util.Random;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class DiceTest {

	private Dice dice;
	
	@Before
	public void setUp() {
		this.dice = new Dice();
	}
	
	@Test
	public void testRollOneDice() throws Exception {
		Random random = Mockito.mock(Random.class);
		Mockito.when(random.nextInt(6)).thenReturn(3);
		dice.setRandom(random);
		
		int result = dice.roll(6);
		Assert.assertEquals(4, result);
	}
	
	@Test
	public void testRollThreeDice() throws Exception {
		Random random = Mockito.mock(Random.class);
		Mockito.when(random.nextInt(6)).thenReturn(3, 2, 5);
		dice.setRandom(random);
		
		int[] result = dice.roll(3, 6);
		Assert.assertArrayEquals(new int[] {4, 3, 6}, result);
	}
}
