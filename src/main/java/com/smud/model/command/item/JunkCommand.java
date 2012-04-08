package com.smud.model.command.item;

import java.text.MessageFormat;
import java.util.Properties;

import javax.annotation.Resource;

import com.smud.model.Color;
import com.smud.model.Inventory;
import com.smud.model.Item;
import com.smud.model.character.Player;
import com.smud.model.command.Command;
import com.smud.model.command.CommandResponse;
import com.smud.model.command.Response;

public class JunkCommand implements Command {

	private static final String COMMAND_NAME = "junk";
	
	private static final int TARGET_ITEM_KEYWORD = 0;
	
	@Resource(name="textProperties")
	private Properties textProperties;
	
	@Override
	public CommandResponse execute(Player player, String parameters) {
		CommandResponse commandResponse = new CommandResponse();
		if (parameters == null || parameters.trim().isEmpty()) {
			commandResponse.addResponse(new Response("Junk what?!?!", Color.WHITE));
		} else {
			String inputKeyword = parameters.split(" ")[TARGET_ITEM_KEYWORD];
			Inventory inventory = player.getInventory();
			Item itemToJunk = inventory.findItem(inputKeyword);
			if (itemToJunk != null) {
				inventory.removeItem(itemToJunk);
				String itemName = textProperties.getProperty("item." + itemToJunk.getCode() + ".name");
				String messageToPlayer = MessageFormat.format("You junk {0}. It vanishes in a puff of smoke.", itemName);
				commandResponse.addResponse(new Response(messageToPlayer, Color.WHITE));
				String messageToOthers = MessageFormat.format("{0} junks {1}. It vanishes in a puff of smoke.", player.getName(), itemName);
				player.getCurrentRoom().sendToOtherCharacters(new Response(messageToOthers, Color.WHITE), player);
			} else {
				String itemNotFoundMessage = MessageFormat.format("You don''t have a {0}", inputKeyword);
				commandResponse.addResponse(new Response(itemNotFoundMessage, Color.WHITE));
			}
		}
		return commandResponse;
	}

	@Override
	public String getCommandName() {
		return COMMAND_NAME;
	}
	
	public void setTextProperties(Properties textProperties) {
		this.textProperties = textProperties;
	}

}
