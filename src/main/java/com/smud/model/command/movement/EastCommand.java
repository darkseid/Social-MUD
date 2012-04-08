package com.smud.model.command.movement;

import com.smud.model.Direction;

public class EastCommand extends MovementCommand {

	private static final String COMMAND_NAME = "east";
	
	@Override
	protected Direction getDirection() {
		return Direction.EAST;
	}

	@Override
	public String getCommandName() {
		return COMMAND_NAME;
	}
}
