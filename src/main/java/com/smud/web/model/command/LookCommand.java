package com.smud.web.model.command;

import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.annotation.Resource;

import com.smud.model.Direction;
import com.smud.model.Player;
import com.smud.model.Room;
import com.smud.web.model.Color;

public class LookCommand implements Command {
	
	private static final String ROOM_EXITS_LEFT_BRACKET = "[ ";
	private static final String ROOM_EXITS_RIGHT_BRACKET = "]";
	private static final String DIRECTION_SEPARATOR = " ";
	
	@Resource(name="roomProperties")
	private Properties roomsProperties;
	
	@Override
	public CommandResponse execute(Player player, Map<String, String> parameters) {
		Room room = player.getInRoom();
		CommandResponse commandResponse = new CommandResponse();
		commandResponse.addResponse(new Response(roomsProperties.getProperty("room." + room.getId() + ".title"), Color.CYAN));
		commandResponse.addResponse(new Response(roomsProperties.getProperty("room." + room.getId() + ".description"), Color.WHITE));
		commandResponse.addResponse(new Response(createExitsResponse(room), Color.DARK_GREEN));
		return commandResponse;
	}

	public void setRoomsProperties(Properties roomsProperties) {
		this.roomsProperties = roomsProperties;
	}
	
	private String createExitsResponse(Room room) {
		StringBuilder builder = new StringBuilder();
		builder.append(ROOM_EXITS_LEFT_BRACKET);
		Set<Direction> directions = room.getRoomExits().keySet();
		for (Direction direction : directions) {
			builder.append(direction.getShrinkedCommand());
			builder.append(DIRECTION_SEPARATOR);
		}
		builder.append(ROOM_EXITS_RIGHT_BRACKET);
		return builder.toString();
	}

}
