package com.smud.model.command.fight;

import org.springframework.beans.factory.annotation.Autowired;

import com.smud.model.character.Character;
import com.smud.model.Color;
import com.smud.model.Input;
import com.smud.model.Room;
import com.smud.model.character.Player;
import com.smud.model.command.Command;
import com.smud.model.command.CommandResponse;
import com.smud.model.command.Response;
import com.smud.service.FightService;

public class KillCommand implements Command {

	private static final String COMMAND_NAME = "kill";

	@Autowired
	private FightService fightService;
	
	@Override
	public CommandResponse execute(Player player, String parameters) {
		Room room = player.getCurrentRoom();
		CommandResponse commandResponse = new CommandResponse();
		Input input = Input.create(parameters);
		Character victim = room.getCharacter(input.getCommandName(), player);
		if (victim == null) {
			commandResponse.addResponse(new Response("Nobody here with that name.", Color.WHITE));
		} else if (player.equals(victim)) {
			commandResponse.addResponse(new Response("Are you trying to kill yourself ?!?", Color.WHITE));
		} else if (victim.isPlayer()) {
			commandResponse.addResponse(new Response("You cannot kill another player (YET)!", Color.WHITE));
		} else {
			fightService.startFight(player, victim);
		}
		return commandResponse;
	}

	@Override
	public String getCommandName() {
		return COMMAND_NAME;
	}

	
}
