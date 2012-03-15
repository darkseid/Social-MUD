package com.smud.model.command;

import junit.framework.Assert;
import junit.framework.TestCase;

import com.smud.model.Color;
import com.smud.model.Player;
import com.smud.model.Room;

public class RoomTest extends TestCase {

	public void test1() {
		
		Room room = new Room();
		
		Player p1 = new Player();
		Player p2 = new Player();

		
		p1.setInRoom(room);
		p2.setInRoom(room);
		
		room.broadcast(new Response("Teste", Color.WHITE));
		
		Assert.assertEquals(new Response("Teste", Color.WHITE), p1.getResponse());
		Assert.assertEquals(new Response("Teste", Color.WHITE), p2.getResponse());
	}
}
