package com.smud.model.command;

import com.smud.model.Player;

public interface Command {

	CommandResponse execute(Player player, String parameters);
}
