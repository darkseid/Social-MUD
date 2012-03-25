package com.smud.model.command;

import com.smud.model.character.Player;

public interface Command {

	CommandResponse execute(Player player, String parameters);

	String getCommandName();
}
