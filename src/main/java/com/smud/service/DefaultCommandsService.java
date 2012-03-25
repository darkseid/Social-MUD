package com.smud.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

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
		
		Input commandInput = extractCommandAndParameters(input);
		
		for (Command command : commands) {
			if (command.getCommandName().toLowerCase().startsWith(commandInput.getCommandName().toLowerCase())){
				return command.execute(player, commandInput.getParameters());
			}
		}
		
		
		repo.updates(player);
		
		return null;
	}
	
	private Input extractCommandAndParameters(String inputCommand) {
		return Input.create(inputCommand);
	}

	public void setCommands(List<Command> commands) {
		this.commands = commands;
	}

}
