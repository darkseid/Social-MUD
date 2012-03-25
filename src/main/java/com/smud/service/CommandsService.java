package com.smud.service;

import com.smud.model.character.Player;
import com.smud.model.command.CommandResponse;

public interface CommandsService {

	CommandResponse parseCommand(Player player, String command);

}
