package com.smud.model.command.item;

import java.text.MessageFormat;
import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;

import com.smud.model.Color;
import com.smud.model.Room;
import com.smud.model.character.Player;
import com.smud.model.command.Command;
import com.smud.model.command.CommandResponse;
import com.smud.model.command.Response;
import com.smud.model.item.Inventory;
import com.smud.model.item.Item;

public class GetCommand implements Command {

	private static final String COMMAND_NAME = "get";

	private static final int TARGET_ITEM_KEYWORD = 0;
	
	@Resource(name="textProperties")
	private Properties textProperties;

	@Override
	public CommandResponse execute(Player player, String parameters) {
		CommandResponse commandResponse = new CommandResponse();
		if (parameters == null || parameters.trim().isEmpty()) {
			commandResponse.addResponse(new Response("Get what?!?!", Color.WHITE));
		} else {
			String inputKeyword = parameters.split(" ")[TARGET_ITEM_KEYWORD];
			Room currentRoom = player.getCurrentRoom();
			Item itemToGet = findItemInRoom(inputKeyword, currentRoom);
			if (itemToGet != null) {
				getItem(player, commandResponse, currentRoom, itemToGet);
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
	
	private Item findItemInRoom(String inputKeyword, Room currentRoom) {
		Item itemToGet = null;
		List<Item> roomItems = currentRoom.getItems();
		for (Item item : roomItems) {
			if (item.getKeywords().match(inputKeyword)) {
				itemToGet = item;
				break;
			}
		}
		return itemToGet;
	}

	private void getItem(Player player, CommandResponse commandResponse, Room currentRoom, Item itemToGet) {
		Inventory inventory = player.getInventory();
		currentRoom.removeItem(itemToGet);
		inventory.addItem(itemToGet);
		String successMessageToPlayer = MessageFormat.format("You get {0}.", textProperties.getProperty("item." + itemToGet.getCode() + ".name"));
		commandResponse.addResponse(new Response(successMessageToPlayer, Color.WHITE));
		String successMessageToRoom = MessageFormat.format("{0} gets {1}.", player.getName(), textProperties.getProperty("item." + itemToGet.getCode() + ".name"));
		currentRoom.sendToOtherCharacters(new Response(successMessageToRoom, Color.WHITE), player);
	}
	
	private void sendItemNotFoundMessage(CommandResponse commandResponse, String inputKeyword) {
		String itemNotFoundMessage = MessageFormat.format("There is no {0} here.", inputKeyword);
		commandResponse.addResponse(new Response(itemNotFoundMessage, Color.WHITE));
	}

}
