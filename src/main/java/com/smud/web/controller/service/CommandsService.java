package com.smud.web.controller.service;

import com.smud.web.model.command.CommandResponse;

public interface CommandsService {

	CommandResponse parseCommand(String command);

}
