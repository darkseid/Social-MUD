package com.smud.service;

import com.smud.web.model.command.CommandResponse;

public interface CommandsService {

	CommandResponse parseCommand(String command);

}
