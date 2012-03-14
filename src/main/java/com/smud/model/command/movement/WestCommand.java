package com.smud.model.command.movement;

import com.smud.model.Direction;
import com.smud.model.Player;
import com.smud.model.Room;

public class WestCommand extends MovementCommand {

	private static final String COMMAND_NAME = "west";

	@Override
	protected Room getDestinationRoom(Player player) {
		Room room = player.getInRoom();
		return room.getRoomExit(Direction.WEST);
	}
	
	@Override
	public String getCommandName() {
		return COMMAND_NAME;
	}
}
