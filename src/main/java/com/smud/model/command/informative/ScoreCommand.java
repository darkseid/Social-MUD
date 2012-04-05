package com.smud.model.command.informative;

import java.text.MessageFormat;
import java.util.Properties;

import javax.annotation.Resource;

import com.smud.model.Color;
import com.smud.model.character.Player;
import com.smud.model.command.Command;
import com.smud.model.command.CommandResponse;
import com.smud.model.command.Response;

public class ScoreCommand implements Command {

	private static final String COMMAND_NAME = "score";
	
	@Resource(name="textProperties")
	private Properties textProperties;
	
	@Override
	public CommandResponse execute(Player player, String parameters) {
		CommandResponse commandResponse = new CommandResponse();
		commandResponse.addResponse(new Response(MessageFormat.format(textProperties.getProperty("score.personal.info.text"), player.getName(), player.getTitle()), Color.WHITE));
		commandResponse.addResponse(new Response(MessageFormat.format(textProperties.getProperty("score.class.text"), player.getPlayerClass().name(), player.getLevel()), Color.WHITE));
		commandResponse.addResponse(new Response(MessageFormat.format(textProperties.getProperty("score.attributes.text"), player.getStrength(), player.getDexterity(), player.getConstitution(), player.getIntelligence(), player.getWisdom(), player.getCharisma()), Color.WHITE));
		return commandResponse;
	}

	@Override
	public String getCommandName() {
		return COMMAND_NAME;
	}

}
