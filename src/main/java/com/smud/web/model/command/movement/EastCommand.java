package com.smud.web.model.command.movement;

import com.smud.model.Direction;
import com.smud.model.Player;
import com.smud.model.Room;

public class EastCommand extends MovementCommand {

	@Override
	protected Room getDestinationRoom(Player player) {
		Room room = player.getInRoom();
		return room.getRoomExit(Direction.EAST);
	}
}
