package com.smud.model.command.communication;

import java.text.MessageFormat;
import java.util.List;

import com.smud.model.Color;
import com.smud.model.Room;
import com.smud.model.Zone;
import com.smud.model.character.Player;
import com.smud.model.command.Command;
import com.smud.model.command.CommandResponse;
import com.smud.model.command.Response;

public class ShoutCommand implements Command {

	private static final String COMMAND_NAME = "shout";

	@Override
	public CommandResponse execute(Player player, String parameters) {
		//TODO change for property key
		String messageToPlayer = MessageFormat.format("You shout: {0}", parameters);
		String messageToOthers = MessageFormat.format("{0} shouts: {1}", player.getName(), parameters);
		
		Room room = player.getCurrentRoom();
		Zone zone = room.getZone();
		List<Room> zoneRooms = zone.getRooms();
		for (Room zoneRoom : zoneRooms) {
			zoneRoom.sendToOtherCharacters(new Response(messageToOthers, Color.DARK_GREEN), player);
		}
		
		CommandResponse commandResponse = new CommandResponse();
		commandResponse.addResponse(new Response(messageToPlayer, Color.DARK_GREEN));
		return commandResponse;
	}

	@Override
	public String getCommandName() {
		return COMMAND_NAME;
	}

}
