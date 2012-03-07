package com.smud.web.model.command.movement;

import org.springframework.beans.factory.annotation.Autowired;

import com.smud.model.Direction;
import com.smud.model.Player;
import com.smud.model.Room;
import com.smud.web.model.Color;
import com.smud.web.model.command.Command;
import com.smud.web.model.command.CommandResponse;
import com.smud.web.model.command.LookCommand;
import com.smud.web.model.command.Response;

public class NorthCommand implements Command {

	@Autowired
	private LookCommand lookCommand;
	
	@Override
	public CommandResponse execute(Player player, String parameters) {
		Room room = player.getInRoom();
		Room northRoom = room.getRoomExit(Direction.NORTH);
		
		if (northRoom != null) {
			room.removePlayer(player);
			northRoom.addPlayer(player);
			player.setInRoom(northRoom);
			return lookCommand.execute(player, parameters);
		} else {
			CommandResponse commandResponse = new CommandResponse();
			commandResponse.addResponse(new Response("Ouch!", Color.WHITE));
			return commandResponse;
		}
	}
	
	public void setLookCommand(LookCommand lookCommand) {
		this.lookCommand = lookCommand;
	}

}
