package com.smud.model.command.movement;

import com.smud.model.Direction;
import com.smud.model.Player;
import com.smud.model.Room;

public class EastCommand extends MovementCommand {

	private static final String COMMAND_NAME = "east";
	
	@Override
	protected Room getDestinationRoom(Player player) {
		Room room = player.getInRoom();
		return room.getRoomExit(Direction.EAST);
	}

	@Override
	public String getCommandName() {
		return COMMAND_NAME;
	}
}
