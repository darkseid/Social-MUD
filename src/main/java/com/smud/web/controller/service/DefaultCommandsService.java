package com.smud.web.controller.service;

import java.util.Map;

import com.smud.web.model.command.Command;
import com.smud.web.model.command.CommandResponse;

public class DefaultCommandsService implements CommandsService {

	private Map<String, Command> commands;
	
	@Override
	public CommandResponse parseCommand(String inputCommand) {
		Command command = commands.get(inputCommand);
		if (command != null) {
			return command.execute();
		} else {
			return null;
		}
	}
	
	public void setCommands(Map<String, Command> commands) {
		this.commands = commands;
	}

}
