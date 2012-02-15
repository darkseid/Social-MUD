package com.smud.service;

import java.util.HashMap;
import java.util.Map;

import com.smud.web.model.command.Command;
import com.smud.web.model.command.CommandResponse;

public class DefaultCommandsService implements CommandsService {

	private Map<String, Command> commands;
	
	@Override
	public CommandResponse parseCommand(String inputCommand) {
		
		// TODO parse the input command to get the command itself and the parameters
		HashMap<String, String> parameters = new HashMap<String, String>();
		
		Command command = commands.get(inputCommand);
		if (command != null) {
			return command.execute(parameters);
		} else {
			return null;
		}
	}
	
	public void setCommands(Map<String, Command> commands) {
		this.commands = commands;
	}

}
