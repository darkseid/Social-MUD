package com.smud.model.command.communication;

import java.text.MessageFormat;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;

import com.smud.model.Color;
import com.smud.model.User;
import com.smud.model.character.Player;
import com.smud.model.command.Command;
import com.smud.model.command.CommandResponse;
import com.smud.model.command.Response;
import com.smud.service.UserService;

public class GossipCommand implements Command {

	private static final String COMMAND_NAME = "gossip";
	
	@Autowired
	private UserService userService;

	@Override
	public CommandResponse execute(Player player, String parameters) {
		CommandResponse commandResponse = new CommandResponse();
		commandResponse.addResponse(new Response("You gossip, '" + parameters + "'", Color.DARK_YELLOW));
		
		Collection<User> loggedUsers = userService.getAllLoggedUsers();
		String messageToOthers = MessageFormat.format("{0} gossips, ''{1}''", player.getName(), parameters);
		for (User user : loggedUsers) {
			Player targetPlayer = user.getPlayer();
			if (!targetPlayer.equals(player)) {
				targetPlayer.addResponse(new Response(messageToOthers, Color.DARK_YELLOW));
			}
		}
		return commandResponse;
	}

	@Override
	public String getCommandName() {
		return COMMAND_NAME;
	}

}
