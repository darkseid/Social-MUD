package com.smud.model;

public enum Direction {

	NORTH("n"),
	SOUTH("s"),
	EAST("e"),
	WEST("w");
	
	private Direction(String shrinkedCommand) {
		this.shrinkedCommand = shrinkedCommand;
	}

	private String shrinkedCommand;
	
	public String getShrinkedCommand() {
		return shrinkedCommand;
	}
}
