package com.smud.web.model.command.movement;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.smud.model.Direction;
import com.smud.model.Player;
import com.smud.model.Room;

public class EastCommandTest {

	private EastCommand command;
	
	@Before
	public void setUp() {
		this.command = new EastCommand();
	}
	
	@Test
	public void testGetDestinationRoom() {
		Room destinationRoom = new Room();
		destinationRoom.setId(2);
		
		Room sourceRoom = new Room();
		sourceRoom.setId(1);
		sourceRoom.addRoomExit(Direction.EAST, destinationRoom);
		
		Player player = new Player();
		player.setInRoom(sourceRoom);
		Room result = command.getDestinationRoom(player);
		
		Assert.assertEquals(destinationRoom, result);
	}
}