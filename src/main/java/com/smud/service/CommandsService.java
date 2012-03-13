package com.smud.service;

import com.smud.model.Player;
import com.smud.model.command.CommandResponse;

public interface CommandsService {

	CommandResponse parseCommand(Player player, String command);

}
