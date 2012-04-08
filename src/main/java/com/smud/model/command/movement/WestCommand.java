package com.smud.model.command.movement;

import com.smud.model.Direction;

public class WestCommand extends MovementCommand {

	private static final String COMMAND_NAME = "west";

	@Override
	protected Direction getDirection() {
		return Direction.WEST;
	}
	
	@Override
	public String getCommandName() {
		return COMMAND_NAME;
	}
}
