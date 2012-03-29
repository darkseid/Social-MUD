package com.smud.model.command.item;

import java.text.MessageFormat;
import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;

import com.smud.model.Color;
import com.smud.model.Inventory;
import com.smud.model.Item;
import com.smud.model.Room;
import com.smud.model.character.Player;
import com.smud.model.command.Command;
import com.smud.model.command.CommandResponse;
import com.smud.model.command.Response;

public class DropCommand implements Command {

	private static final String COMMAND_NAME = "drop";

	private static final int TARGET_ITEM_KEYWORD = 0;
	
	@Resource(name="textProperties")
	private Properties textProperties;

	@Override
	public CommandResponse execute(Player player, String parameters) {
		CommandResponse commandResponse = new CommandResponse();
		if (parameters == null || parameters.trim().isEmpty()) {
			commandResponse.addResponse(new Response("Drop what?!?!", Color.WHITE));
		} else {
			String inputKeyword = parameters.split(" ")[TARGET_ITEM_KEYWORD];
			Inventory inventory = player.getInventory();
			Item itemToDrop = findItemInInventory(inputKeyword, inventory);
			if (itemToDrop != null) {
				Room currentRoom = player.getCurrentRoom();
				dropItem(player, commandResponse, currentRoom, itemToDrop);
			} else {
				sendItemNotFoundMessage(commandResponse, inputKeyword);
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
	
	private Item findItemInInventory(String inputKeyword, Inventory inventory) {
		Item itemToDrop = null;
		List<Item> roomItems = inventory.getItems();
		for (Item item : roomItems) {
			if (item.getKeywords().match(inputKeyword)) {
				itemToDrop = item;
				break;
			}
		}
		return itemToDrop;
	}

	private void dropItem(Player player, CommandResponse commandResponse, Room currentRoom, Item itemToDrop) {
		Inventory inventory = player.getInventory();
		inventory.removeItem(itemToDrop);
		
		currentRoom.addItem(itemToDrop);
		
		String successMessageToPlayer = MessageFormat.format("You dropped {0}.", textProperties.getProperty("item." + itemToDrop.getId() + ".name"));
		commandResponse.addResponse(new Response(successMessageToPlayer, Color.WHITE));
		
		String successMessageToRoom = MessageFormat.format("{0} dropped {1}.", player.getName(), textProperties.getProperty("item." + itemToDrop.getId() + ".name"));
		currentRoom.sendToOtherCharacters(new Response(successMessageToRoom, Color.WHITE), player);
	}
	
	private void sendItemNotFoundMessage(CommandResponse commandResponse, String inputKeyword) {
		String itemNotFoundMessage = MessageFormat.format("There is no {0} here.", inputKeyword);
		commandResponse.addResponse(new Response(itemNotFoundMessage, Color.WHITE));
	}

}
