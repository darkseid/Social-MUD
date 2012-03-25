package com.smud.model.command.communication;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.smud.model.Color;
import com.smud.model.Room;
import com.smud.model.Zone;
import com.smud.model.character.Player;
import com.smud.model.command.CommandResponse;
import com.smud.model.command.Response;

public class ShoutCommandTest {

	private ShoutCommand command;
	
	@Before
	public void setUp() {
		command = new ShoutCommand();
	}
	
	@Test
	public void testReturnMessageToPlayer() {
		Zone zone = new Zone();
		
		Room room = new Room();
		room.setZone(zone);
		
		Player player = new Player();
		player.enters(room);
		String parameters = "message";
		
		CommandResponse result = command.execute(player, parameters);
		
		CommandResponse expectedResult = new CommandResponse();
		expectedResult.addResponse(new Response("You shout: message", Color.DARK_GREEN));
		
		Assert.assertEquals(expectedResult, result);
	}
	
	@Test
	public void testSendMessageToOtherPlayers() {
		Zone zone = new Zone();
		
		Room room = new Room();
		room.setZone(zone);
		
		Room otherRoom = new Room();
		otherRoom.setZone(zone);
		
		List<Room> zoneRooms = new ArrayList<Room>();
		zoneRooms.add(room);
		zoneRooms.add(otherRoom);
		zone.setRooms(zoneRooms);
		
		Player player = new Player();
		player.setName("player1");
		player.enters(room);
		
		Player otherPlayer = new Player();
		otherPlayer.setName("player2");
		otherPlayer.enters(otherRoom);
		
		String parameters = "message";
		
		command.execute(player, parameters);
		
		Response result = otherPlayer.getResponse();
		Assert.assertEquals(new Response("player1 shouts: message", Color.DARK_GREEN), result);
	}
}
