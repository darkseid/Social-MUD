package com.smud.model.command.communication;

import java.text.MessageFormat;

import com.smud.model.Color;
import com.smud.model.Room;
import com.smud.model.character.Player;
import com.smud.model.command.Command;
import com.smud.model.command.CommandResponse;
import com.smud.model.command.Response;

public class SayCommand implements Command {

	private static final String COMMAND_NAME = "say";

	@Override
	public CommandResponse execute(Player player, String parameters) {
		Room room = player.getCurrentRoom();
		//TODO change for property key
		String messageToPlayer = MessageFormat.format("You say: {0}", parameters);
		String messageToOthers = MessageFormat.format("{0} says: {1}", player.getName(), parameters);
		room.sendToOtherCharacters(new Response(messageToOthers, Color.WHITE), player);
		
		CommandResponse commandResponse = new CommandResponse();
		commandResponse.addResponse(new Response(messageToPlayer, Color.WHITE));
		return commandResponse;
	}

	@Override
	public String getCommandName() {
		return COMMAND_NAME;
	}

}
