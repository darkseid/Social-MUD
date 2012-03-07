package com.smud.model;

import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.Test;

public class InputTest {

	@Test
	public void testCreateLookCommandWithoutParams() {
		
		Input input = Input.create("look");
		String expected = "look";
		
		Assert.assertEquals(expected, input.getCommandName());
		Assert.assertEquals("", input.getParameters());
		
	}
	
	@Test
	public void testCreateLookCommandWithOneParam() {
		
		Input input = Input.create("look door");
		String expected = "look";
		String expectedParameters = "door";
		
		Assert.assertEquals(expected, input.getCommandName());
		Assert.assertEquals(expectedParameters, input.getParameters());
	}

}
