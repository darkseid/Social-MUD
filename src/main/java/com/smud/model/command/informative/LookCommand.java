package com.smud.model.command.informative;

import java.util.List;
import java.util.Properties;
import java.util.Set;

import javax.annotation.Resource;

import com.smud.model.Color;
import com.smud.model.Direction;
import com.smud.model.Item;
import com.smud.model.Room;
import com.smud.model.character.Character;
import com.smud.model.character.Player;
import com.smud.model.command.Command;
import com.smud.model.command.CommandResponse;
import com.smud.model.command.Response;

public class LookCommand implements Command {
	
	private static final String COMMAND_NAME = "look";
	private static final String ROOM_EXITS_LEFT_BRACKET = "[ ";
	private static final String ROOM_EXITS_RIGHT_BRACKET = "]";
	private static final String DIRECTION_SEPARATOR = " ";
	
	@Resource(name="textProperties")
	private Properties textProperties;
	
	@Override
	public CommandResponse execute(Player player, String parameters) {
		Room room = player.getCurrentRoom();
		CommandResponse commandResponse = new CommandResponse();
		commandResponse.addResponse(new Response(textProperties.getProperty("room." + room.getId() + ".title"), Color.CYAN));
		commandResponse.addResponse(new Response(textProperties.getProperty("room." + room.getId() + ".description"), Color.WHITE));
		commandResponse.addResponse(new Response(createExitsResponse(room), Color.DARK_GREEN));
		showItems(player, commandResponse, room.getItems());
		showCharacters(player, commandResponse, room.getMonsters());
		showCharacters(player, commandResponse, room.getPlayers());
		return commandResponse;
	}

	private void showCharacters(Player player, CommandResponse commandResponse, List<? extends Character> charactersInRoom) {
		for (Character characterInRoom : charactersInRoom) {
			if (!player.equals(characterInRoom)) {
				String characterRoomDescription = characterInRoom.getDescriptionToRoom();
				commandResponse.addResponse(new Response(characterRoomDescription, Color.DARK_YELLOW));
			}
		}
	}
	
	private void showItems(Player player, CommandResponse commandResponse, List<Item> itemsInRoom) {
		for (Item item : itemsInRoom) {
			commandResponse.addResponse(new Response(textProperties.getProperty("item." + item.getId() + ".room.description"), Color.DARK_GREEN));
		}
	}
	
	@Override
	public String getCommandName() {
		return COMMAND_NAME;
	}

	public void setTextProperties(Properties textProperties) {
		this.textProperties = textProperties;
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
