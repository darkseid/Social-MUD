package com.smud.service;

import java.util.HashMap;
import java.util.Map;

import com.smud.model.Input;
import com.smud.model.Player;
import com.smud.web.model.command.Command;
import com.smud.web.model.command.CommandResponse;

public class DefaultCommandsService implements CommandsService {

	private Map<String, Command> commands;
	
	@Override
	public CommandResponse parseCommand(Player player, String input) {
		
		// TODO parse the input command to get the command itself and the parameters
		HashMap<String, String> parameters = new HashMap<String, String>();
		
		Input commandInput = extractCommandAndParameters(input);
		
		Command command = commands.get(commandInput.getCommandName());
		if (command != null) {
			return command.execute(player, commandInput.getParameters());
		} else {
			return null;
		}
	}
	
	private Input extractCommandAndParameters(String inputCommand) {
		return Input.create(inputCommand);
	}

	public void setCommands(Map<String, Command> commands) {
		this.commands = commands;
	}

}
