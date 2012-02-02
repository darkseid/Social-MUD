package com.smud.web.model.command;

public class LookCommand implements Command {

	@Override
	public CommandResponse execute() {
		CommandResponse commandResponse = new CommandResponse();
		commandResponse.addResponse("LookCommand executed");
		return commandResponse;
	}

}
