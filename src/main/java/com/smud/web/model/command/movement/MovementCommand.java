package com.smud.web.model.command.movement;

import org.springframework.beans.factory.annotation.Autowired;

import com.smud.model.Player;
import com.smud.model.Room;
import com.smud.web.model.Color;
import com.smud.web.model.command.Command;
import com.smud.web.model.command.CommandResponse;
import com.smud.web.model.command.LookCommand;
import com.smud.web.model.command.Response;

public abstract class MovementCommand implements Command {

	@Autowired
	private LookCommand lookCommand;
	
	@Override
	public CommandResponse execute(Player player, String parameters) {
		Room destinationRoom = getDestinationRoom(player);
		
		if (destinationRoom != null) {
			Room room = player.getInRoom();
			room.removePlayer(player);
			player.setInRoom(destinationRoom);
			return lookCommand.execute(player, parameters);
		} else {
			CommandResponse commandResponse = new CommandResponse();
			//TODO change for property key
			commandResponse.addResponse(new Response("Nothing in this direction.", Color.WHITE));
			return commandResponse;
		}
	}
	
	protected abstract Room getDestinationRoom(Player player);

	public void setLookCommand(LookCommand lookCommand) {
		this.lookCommand = lookCommand;
	}
}
