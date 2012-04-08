package com.smud.model.command.movement;

import java.text.MessageFormat;

import org.springframework.beans.factory.annotation.Autowired;

import com.smud.model.Color;
import com.smud.model.Direction;
import com.smud.model.Room;
import com.smud.model.character.Player;
import com.smud.model.command.Command;
import com.smud.model.command.CommandResponse;
import com.smud.model.command.Response;
import com.smud.model.command.informative.LookCommand;

public abstract class MovementCommand implements Command {

	@Autowired
	private LookCommand lookCommand;
	
	@Override
	public CommandResponse execute(Player player, String parameters) {
		Room currentRoom = player.getCurrentRoom();
		Direction direction = getDirection();
		Room destinationRoom = currentRoom.getRoomExit(direction);
		if (destinationRoom != null) {
			String playerMovedMessage = MessageFormat.format("{0} goes to the {1}.", player.getName(), direction.name().toLowerCase());
			currentRoom.sendToOtherCharacters(new Response(playerMovedMessage, Color.WHITE), player);
			player.enters(destinationRoom);
			String playerArrivedMessage = MessageFormat.format("{0} arrived.", player.getName());
			destinationRoom.sendToOtherCharacters(new Response(playerArrivedMessage, Color.WHITE), player);
			return lookCommand.execute(player, parameters);
		} else {
			CommandResponse commandResponse = new CommandResponse();
			//TODO change for property key
			commandResponse.addResponse(new Response("Nothing in this direction.", Color.WHITE));
			return commandResponse;
		}
	}
	
	protected abstract Direction getDirection();

	public void setLookCommand(LookCommand lookCommand) {
		this.lookCommand = lookCommand;
	}
}
