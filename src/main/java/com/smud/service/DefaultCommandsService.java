package com.smud.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.smud.model.Input;
import com.smud.model.character.Player;
import com.smud.model.command.Command;
import com.smud.model.command.CommandResponse;

public class DefaultCommandsService implements CommandsService {

	private List<Command> commands = new ArrayList<Command>();
	
	@Autowired
	private RedisRepository repo;
	
	@Override
	public CommandResponse parseCommand(Player player, String input) {
		CommandResponse commandResponse = null;
		Input commandInput = extractCommandAndParameters(input);
		
		if (!commandInput.getCommandName().isEmpty()){
			for (Command command : commands) {
				if (command.getCommandName().toLowerCase().startsWith(commandInput.getCommandName().toLowerCase())){
					commandResponse = command.execute(player, commandInput.getParameters());
					break;
				}
			}
		}
		
		// TODO Treat the case of empty command or unknown command
		// It's currently causing an error in smud.js when parsing the response.
		
		repo.updates(player);
		return commandResponse;
	}
	
	private Input extractCommandAndParameters(String inputCommand) {
		return Input.create(inputCommand);
	}

	public void setCommands(List<Command> commands) {
		this.commands = commands;
	}

}
