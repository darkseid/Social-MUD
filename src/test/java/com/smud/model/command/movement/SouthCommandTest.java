package com.smud.model.command.movement;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.smud.model.Direction;
import com.smud.model.Room;
import com.smud.model.character.Player;
import com.smud.model.command.movement.SouthCommand;

public class SouthCommandTest {

	private SouthCommand command;
	
	@Before
	public void setUp() {
		this.command = new SouthCommand();
	}
	
	@Test
	public void testGetDestinationRoom() {
		Room destinationRoom = new Room();
		destinationRoom.setId(2);
		
		Room sourceRoom = new Room();
		sourceRoom.setId(1);
		sourceRoom.addRoomExit(Direction.SOUTH, destinationRoom);
		
		Player player = new Player();
		player.enters(sourceRoom);
		Room result = command.getDestinationRoom(player);
		
		Assert.assertEquals(destinationRoom, result);
	}
	
}
