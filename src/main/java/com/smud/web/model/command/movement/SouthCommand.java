package com.smud.web.model.command.movement;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.smud.model.Direction;
import com.smud.model.Player;
import com.smud.model.Room;
import com.smud.web.model.Color;
import com.smud.web.model.command.Command;
import com.smud.web.model.command.CommandResponse;
import com.smud.web.model.command.LookCommand;
import com.smud.web.model.command.Response;

public class SouthCommand implements Command {

	@Autowired
	private LookCommand lookCommand;
	
	@Override
	public CommandResponse execute(Player player, Map<String, String> parameters) {
		Room room = player.getInRoom();
		Room southRoom = room.getRoomExit(Direction.SOUTH);
		
		if (southRoom != null) {
			room.removePlayer(player);
			southRoom.addPlayer(player);
			player.setInRoom(southRoom);
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
