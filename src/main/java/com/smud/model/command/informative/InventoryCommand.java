package com.smud.model.command.informative;

import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;

import com.smud.model.Color;
import com.smud.model.Inventory;
import com.smud.model.Item;
import com.smud.model.character.Player;
import com.smud.model.command.Command;
import com.smud.model.command.CommandResponse;
import com.smud.model.command.Response;

public class InventoryCommand implements Command {

	private static final String COMMAND_NAME = "inventory";
	
	@Resource(name="textProperties")
	private Properties textProperties;

	@Override
	public CommandResponse execute(Player player, String parameters) {
		CommandResponse commandResponse = new CommandResponse();
		Response response = new Response("You are carrying:", Color.WHITE);
		commandResponse.addResponse(response);
		
		Inventory inventory = player.getInventory();
		List<Item> inventoryItems = inventory.getItems();
		
		if (inventoryItems.size() > 0) {
			for (Item item : inventoryItems) {
				String itemName = textProperties.getProperty("item." + item.getCode() + ".name");
				Response itemResponse = new Response(itemName, Color.WHITE);
				commandResponse.addResponse(itemResponse);
			}
		} else {
			Response nothingMessage = new Response("Nothing.", Color.WHITE);
			commandResponse.addResponse(nothingMessage);
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
