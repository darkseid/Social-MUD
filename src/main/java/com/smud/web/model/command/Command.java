package com.smud.web.model.command;

import java.util.Map;

import com.smud.model.Player;

public interface Command {

	CommandResponse execute(Player player, Map<String, String> parameters);
}
