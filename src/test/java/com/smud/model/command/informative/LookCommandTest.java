package com.smud.model.command.informative;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.smud.model.Color;
import com.smud.model.Direction;
import com.smud.model.Player;
import com.smud.model.Room;
import com.smud.model.command.CommandResponse;
import com.smud.model.command.Response;
import com.smud.model.command.informative.LookCommand;

public class LookCommandTest {

	private static final int ROOM_ID = 1;
	private static final String ROOM_DESCRIPTION = "RoomDescription";
	private static final String ROOM_TITLE = "RoomTitle";
	
	private LookCommand command;
	
	@Before
	public void setUp() {
		this.command = new LookCommand();
	}
	
	@Test
	public void testExecuteWithoutParameters() throws Exception {
		Room southRoom = new Room();
		Map<Direction, Room> roomExits = new HashMap<Direction, Room>();
		roomExits.put(Direction.SOUTH, southRoom);
		
		Room room = new Room();
		room.setId(ROOM_ID);
		room.setRoomExits(roomExits);
		
		Player player = new Player();
		player.setInRoom(room);
		
		Properties roomsProperties = new Properties();
		roomsProperties.put("room."+ ROOM_ID + ".title", ROOM_TITLE);
		roomsProperties.put("room."+ ROOM_ID + ".description", ROOM_DESCRIPTION);
		this.command.setRoomsProperties(roomsProperties);
		
		CommandResponse expectedResult = new CommandResponse();
		expectedResult.addResponse(new Response(ROOM_TITLE, Color.CYAN));
		expectedResult.addResponse(new Response(ROOM_DESCRIPTION, Color.WHITE));
		expectedResult.addResponse(new Response("[ s ]", Color.DARK_GREEN));
		
		CommandResponse result = this.command.execute(player, null);
		Assert.assertEquals(expectedResult, result);
	}
	
}
