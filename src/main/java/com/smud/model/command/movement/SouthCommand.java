package com.smud.model.command.movement;

import com.smud.model.Direction;

public class SouthCommand extends MovementCommand {

	private static final String COMMAND_NAME = "south";
	
	@Override
	public String getCommandName() {
		return COMMAND_NAME;
	}

	@Override
	protected Direction getDirection() {
		return Direction.SOUTH;
	}

}
