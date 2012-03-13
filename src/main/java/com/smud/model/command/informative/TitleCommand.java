package com.smud.model.command.informative;

import java.text.MessageFormat;

import com.smud.model.Color;
import com.smud.model.Player;
import com.smud.model.command.Command;
import com.smud.model.command.CommandResponse;
import com.smud.model.command.Response;

public class TitleCommand implements Command {

	@Override
	public CommandResponse execute(Player player, String parameters) {
		player.setTitle(parameters);
		CommandResponse commandResponse = new CommandResponse();
		String responseText = MessageFormat.format("You are now {0} {1}", player.getName(), player.getTitle());
		commandResponse.addResponse(new Response(responseText, Color.WHITE));
		return commandResponse;
	}

}
