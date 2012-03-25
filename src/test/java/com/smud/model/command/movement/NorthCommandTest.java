package com.smud.model.command.movement;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.smud.model.Direction;
import com.smud.model.Room;
import com.smud.model.character.Player;
import com.smud.model.command.movement.NorthCommand;

public class NorthCommandTest {

	private NorthCommand command;
	
	@Before
	public void setUp() {
		this.command = new NorthCommand();
	}
	
	@Test
	public void testGetDestinationRoom() {
		Room destinationRoom = new Room();
		destinationRoom.setId(2);
		
		Room sourceRoom = new Room();
		sourceRoom.setId(1);
		sourceRoom.addRoomExit(Direction.NORTH, destinationRoom);
		
		Player player = new Player();
		player.enters(sourceRoom);
		Room result = command.getDestinationRoom(player);
		
		Assert.assertEquals(destinationRoom, result);
	}
}
