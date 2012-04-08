package com.smud.model.command.movement;

import com.smud.model.Direction;

public class NorthCommand extends MovementCommand {

	private static final String COMMAND_NAME = "north";
	
	@Override
	protected Direction getDirection() {
		return Direction.NORTH;
	}
	
	@Override
	public String getCommandName() {
		return COMMAND_NAME;
	}

}
