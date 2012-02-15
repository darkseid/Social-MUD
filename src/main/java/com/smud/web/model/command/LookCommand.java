package com.smud.web.model.command;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.smud.service.RoomService;

public class LookCommand implements Command {
	
	@Autowired
	private RoomService roomService;

	@Override
	public CommandResponse execute(Map<String, String> parameters) {
		
		
		CommandResponse commandResponse = new CommandResponse();
		commandResponse.addResponse("LookCommand executed");
		return commandResponse;
	}

}
