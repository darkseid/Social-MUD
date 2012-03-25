package com.smud.model.command;

import junit.framework.Assert;
import junit.framework.TestCase;

import com.smud.model.Color;
import com.smud.model.character.Player;

public class PlayerTest extends TestCase {

	
	public void test1() {
		
		Player player = new Player();
		
		player.addResponse(new Response("Oi", Color.WHITE));
		player.addResponse(new Response("Rafael", Color.WHITE));
		
		
		Assert.assertEquals(new Response("Oi", Color.WHITE), player.getResponse());
		Assert.assertEquals(new Response("Rafael", Color.WHITE), player.getResponse());	
	}
	
	public void test2() {
		
		Player player = new Player();
		
		player.addResponse(new Response("Oi", Color.WHITE));
		
		
		Assert.assertEquals(new Response("Oi", Color.WHITE), player.getResponse());
		Assert.assertEquals(null, player.getResponse());	
	}
	
	
}
