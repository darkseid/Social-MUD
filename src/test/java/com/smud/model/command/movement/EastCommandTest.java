package com.smud.model.command.movement;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.smud.model.Direction;

public class EastCommandTest {

	private EastCommand command;
	
	@Before
	public void setUp() {
		this.command = new EastCommand();
	}
	
	@Test
	public void testGetDestinationRoom() {
		Direction result = command.getDirection();
		Assert.assertEquals(Direction.EAST, result);
	}
}
