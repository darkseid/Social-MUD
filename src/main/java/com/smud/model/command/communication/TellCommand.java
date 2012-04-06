package com.smud.model.command.communication;

import java.text.MessageFormat;

import org.springframework.beans.factory.annotation.Autowired;

import com.smud.model.Color;
import com.smud.model.Input;
import com.smud.model.User;
import com.smud.model.character.Player;
import com.smud.model.command.Command;
import com.smud.model.command.CommandResponse;
import com.smud.model.command.Response;
import com.smud.service.UserService;

public class TellCommand implements Command {

	private static final String COMMAND_NAME = "tell";
	
	@Autowired
	private UserService userService;
	
	@Override
	public CommandResponse execute(Player player, String parameters) {
		CommandResponse commandResponse = new CommandResponse();
		Input input = Input.create(parameters);
		User targetUser = userService.findLoggedUser(input.getCommandName());
		if (targetUser == null) {
			commandResponse.addResponse(new Response("Nobody here with that name.", Color.WHITE));
		} else if (player.equals(targetUser.getPlayer())) {
			commandResponse.addResponse(new Response("Talking to yourself ?!?", Color.WHITE));
		} else {
			Player targetPlayer = targetUser.getPlayer();
			String messageToPlayer = MessageFormat.format("You tell {0}, ''{1}''", targetPlayer.getName(), input.getParameters());
			String messageToTarget = MessageFormat.format("{0} tells you, ''{1}''", player.getName(), input.getParameters());
			commandResponse.addResponse(new Response(messageToPlayer, Color.BLUE));
			targetPlayer.addResponse(new Response(messageToTarget, Color.BLUE));
		}
		return commandResponse;
	}

	@Override
	public String getCommandName() {
		return COMMAND_NAME;
	}

}
