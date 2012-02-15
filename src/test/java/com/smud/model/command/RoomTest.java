package com.smud.model.command;

import junit.framework.Assert;
import junit.framework.TestCase;

import com.smud.model.Player;
import com.smud.model.Room;

public class RoomTest extends TestCase {

	public void test1() {
		
		Room room = new Room();
		
		Player p1 = new Player();
		Player p2 = new Player();

		
		p1.setInRoom(room);
		p2.setInRoom(room);
		
		room.broadcast("Teste");
		
		Assert.assertEquals("Teste", p1.getMessage());
		Assert.assertEquals("Teste", p2.getMessage());
	}
}
