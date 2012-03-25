package com.smud.model.command.admin;

import com.smud.model.Color;
import com.smud.model.Room;
import com.smud.model.character.Player;
import com.smud.model.command.Command;
import com.smud.model.command.CommandResponse;
import com.smud.model.command.Response;

//TODO make this an immortal level command
public class ResetCommand implements Command {

	private static final String COMMAND_NAME = "reset";
	
	@Override
	public CommandResponse execute(Player player, String parameters) {
		Room inRoom = player.getCurrentRoom();
		inRoom.reset();
		CommandResponse commandResponse = new CommandResponse();
		commandResponse.addResponse(new Response("OK", Color.WHITE));
		return commandResponse;
	}

	@Override
	public String getCommandName() {
		return COMMAND_NAME;
	}

}
