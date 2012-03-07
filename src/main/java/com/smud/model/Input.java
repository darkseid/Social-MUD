package com.smud.model;


public class Input {

	private String commandName;
	private String parameters;

	public String getCommandName() {
		return commandName;
	}

	public String getParameters() {
		return parameters;
	}

	public Input(String commandName, String parameters) {
		super();
		this.commandName = commandName;
		this.parameters = parameters;
	}

	
	public static Input create(String input) {
		
		int firstBlank = input.indexOf(" ");
		
		if ( firstBlank == -1 ) {
			return new Input(input, "");
		}
		
		String commandName = input.substring(0, firstBlank);
		String parameters = input.substring(firstBlank + 1, input.length());
		
		return new Input(commandName, parameters);
	}
	
}
