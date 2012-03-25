package com.smud.model.command.movement;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.smud.model.Color;
import com.smud.model.Direction;
import com.smud.model.Room;
import com.smud.model.character.Player;
import com.smud.model.command.CommandResponse;
import com.smud.model.command.Response;
import com.smud.model.command.informative.LookCommand;
import com.smud.model.command.movement.MovementCommand;
import com.smud.model.command.movement.NorthCommand;

public class MovementCommandTest {

	private MovementCommand command;
	private LookCommand lookCommandMock;
	
	@Before
	public void setUp() {
		lookCommandMock = Mockito.mock(LookCommand.class);
		command = new NorthCommand();
		command.setLookCommand(lookCommandMock);
	}
	
	@Test
	public void testChangedPlayerInRoom() throws Exception {
		Room destinationRoom = new Room();
		
		Room sourceRoom = new Room();
		sourceRoom.addRoomExit(Direction.NORTH, destinationRoom);
		
		Player player = new Player();
		player.enters(sourceRoom);
		
		command.execute(player, null);
		
		Assert.assertEquals(destinationRoom, player.getCurrentRoom());
	}
	
	@Test
	public void testRemovedPlayerFromSourceRoom() throws Exception {
		Room destinationRoom = new Room();
		destinationRoom.setId(2);
		
		Room sourceRoom = new Room();
		sourceRoom.setId(1);
		sourceRoom.addRoomExit(Direction.NORTH, destinationRoom);
		
		Player player = new Player();
		player.enters(sourceRoom);
		
		command.execute(player, null);
		
		Assert.assertEquals(0, sourceRoom.getCharacters().size());
	}
	
	@Test
	public void testAddedPlayerToDestinationRoom() throws Exception {
		Room destinationRoom = new Room();
		destinationRoom.setId(2);
		
		Room sourceRoom = new Room();
		sourceRoom.setId(1);
		sourceRoom.addRoomExit(Direction.NORTH, destinationRoom);
		
		Player player = new Player();
		player.enters(sourceRoom);
		
		command.execute(player, null);
		
		Assert.assertEquals(1, destinationRoom.getCharacters().size());
	}
	
	@Test
	public void testExecuteLookCommand() throws Exception {
		Room destinationRoom = new Room();
		destinationRoom.setId(2);
		
		Room sourceRoom = new Room();
		sourceRoom.setId(1);
		sourceRoom.addRoomExit(Direction.NORTH, destinationRoom);
		
		Player player = new Player();
		player.enters(sourceRoom);
		
		command.execute(player, null);
		
		Mockito.verify(lookCommandMock).execute(player, null);
	}
	
	@Test
	public void testNoRoomToMove() throws Exception {
		Room sourceRoom = new Room();
		sourceRoom.setId(1);
		
		Player player = new Player();
		player.enters(sourceRoom);
		
		CommandResponse result = command.execute(player, null);
		
		Assert.assertEquals(new Response("Nothing in this direction.", Color.WHITE), result.getResponses().get(0));
	}
}
