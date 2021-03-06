package com.smud.model.command;

import junit.framework.Assert;
import junit.framework.TestCase;

import com.smud.model.Color;
import com.smud.model.Room;
import com.smud.model.character.Player;

public class RoomTest extends TestCase {

	public void test1() {
		
		Room room = new Room();
		
		Player p1 = new Player();
		Player p2 = new Player();

		
		p1.enters(room);
		p2.enters(room);
		
		room.broadcast(new Response("Teste", Color.WHITE));
		
		Assert.assertEquals(new Response("Teste", Color.WHITE), p1.getResponse());
		Assert.assertEquals(new Response("Teste", Color.WHITE), p2.getResponse());
	}
}
