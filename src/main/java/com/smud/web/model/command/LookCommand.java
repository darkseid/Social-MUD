package com.smud.web.model.command;

import org.springframework.beans.factory.annotation.Autowired;

import com.smud.model.Player;
import com.smud.model.Room;
import com.smud.service.RoomService;
import com.smud.web.model.Color;

public class LookCommand implements Command {
	
	@Autowired
	private RoomService roomService;

	@Override
	public CommandResponse execute(Player player, String parameters) {
		Room room = player.getInRoom();
		CommandResponse commandResponse = new CommandResponse();
		commandResponse.addResponse(new Response(room.getTitle(), Color.CYAN));
		commandResponse.addResponse(new Response(room.getDescription(), Color.WHITE));
		return commandResponse;
	}

}
