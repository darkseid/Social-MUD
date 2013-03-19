package com.smud.service;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smud.model.Color;
import com.smud.model.Input;
import com.smud.model.character.Player;
import com.smud.model.command.Command;
import com.smud.model.command.CommandResponse;
import com.smud.model.command.Response;
import com.smud.service.data.PlayerRepository;

@Service
public class DefaultCommandsService implements CommandsService {

	private List<Command> commands = new ArrayList<Command>();
	
	@Autowired
	private PlayerRepository playerRepository;
	
	@Resource(name="textProperties")
	private Properties textProperties;
	
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
		
		if (commandResponse == null) {
			commandResponse = new CommandResponse();
		}
		
		sendPrompt(player, commandResponse);
		
		// TODO Treat the case of empty command or unknown command
		// It's currently causing an error in smud.js when parsing the response.
		
		playerRepository.updatePlayer(player);
		return commandResponse;
	}

	@Override
	public void sendPrompt(Player player, CommandResponse commandResponse) {
		commandResponse.addResponse(new Response("", Color.WHITE));
		String prompt = MessageFormat.format(textProperties.getProperty("player.prompt"), player.getHitPoints(), player.getManaPoints(), player.getMovementPoints());
		commandResponse.addResponse(new Response(prompt, Color.WHITE));
	}
	
	private Input extractCommandAndParameters(String inputCommand) {
		return Input.create(inputCommand);
	}

	public void setCommands(List<Command> commands) {
		this.commands = commands;
	}

}
