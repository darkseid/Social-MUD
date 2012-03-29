package com.smud.model.command.informative;

import java.util.ArrayList;
import java.util.List;

import com.smud.model.Color;
import com.smud.model.character.Player;
import com.smud.model.command.Command;
import com.smud.model.command.CommandResponse;
import com.smud.model.command.Response;

public class CommandsCommand implements Command {

	private static final String COMMAND_NAME = "commands";

	private List<Command> commands = new ArrayList<Command>();

	@Override
	public CommandResponse execute(Player player, String parameters) {
		CommandResponse commandResponse = new CommandResponse();
		Response response = new Response("Available commands:", Color.WHITE);
		commandResponse.addResponse(response);
		
		for (Command command : commands) {
			Response itemResponse = new Response(command.getCommandName(), Color.WHITE);
			commandResponse.addResponse(itemResponse);
		}
		
		return commandResponse;
	}
	
	@Override
	public String getCommandName() {
		return COMMAND_NAME;
	}

	public void setCommands(List<Command> commands) {
		this.commands = commands;
	}

}
