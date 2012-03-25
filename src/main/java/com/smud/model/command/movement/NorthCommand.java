package com.smud.model.command.movement;

import com.smud.model.Direction;
import com.smud.model.Room;
import com.smud.model.character.Player;

public class NorthCommand extends MovementCommand {

	private static final String COMMAND_NAME = "north";
	
	@Override
	protected Room getDestinationRoom(Player player) {
		Room room = player.getCurrentRoom();
		return room.getRoomExit(Direction.NORTH);
	}
	
	@Override
	public String getCommandName() {
		return COMMAND_NAME;
	}

}
