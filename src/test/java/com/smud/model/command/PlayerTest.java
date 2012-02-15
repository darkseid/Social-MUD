package com.smud.model.command;

import com.smud.model.Player;

import junit.framework.Assert;
import junit.framework.TestCase;

public class PlayerTest extends TestCase {

	
	public void test1() {
		
		Player player = new Player();
		
		player.addMessage("Oi");
		player.addMessage("Rafael");
		
		
		Assert.assertEquals("Oi", player.getMessage());
		Assert.assertEquals("Rafael", player.getMessage());	
	}
	
	public void test2() {
		
		Player player = new Player();
		
		player.addMessage("Oi");
		
		
		Assert.assertEquals("Oi", player.getMessage());
		Assert.assertEquals(null, player.getMessage());	
	}
	
	
}
