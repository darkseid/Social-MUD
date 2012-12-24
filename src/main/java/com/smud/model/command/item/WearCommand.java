package com.smud.model.command.item;

import java.text.MessageFormat;
import java.util.Properties;

import javax.annotation.Resource;

import com.smud.model.Color;
import com.smud.model.character.Player;
import com.smud.model.command.Command;
import com.smud.model.command.CommandResponse;
import com.smud.model.command.Response;
import com.smud.model.item.Equipment;
import com.smud.model.item.Inventory;
import com.smud.model.item.Item;

public class WearCommand implements Command {

	private static final String COMMAND_NAME = "wear";
	
	private static final int TARGET_ITEM_KEYWORD = 0;
	
	@Resource(name="textProperties")
	private Properties textProperties;

	@Override
	public CommandResponse execute(Player player, String parameters) {
		CommandResponse commandResponse = new CommandResponse();
		if (parameters == null || parameters.trim().isEmpty()) {
			commandResponse.addResponse(new Response("Wear what?!?!", Color.WHITE));
		} else {
			String inputKeyword = parameters.split(" ")[TARGET_ITEM_KEYWORD];
			Inventory inventory = player.getInventory();
			Item itemToWear = inventory.findItem(inputKeyword);
			if (itemToWear != null) {
				wearItem(player, itemToWear, commandResponse);
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

	private void wearItem(Player player, Item item, CommandResponse commandResponse) {
		Equipment equipment = player.getEquipment();
		Inventory inventory = player.getInventory();
		if (!item.isWearable()) {
			commandResponse.addResponse(new Response("You can't wear that!", Color.WHITE));
		} else if (!equipment.hasSlotAvailable(item.getItemWearPosition())) {
			commandResponse.addResponse(new Response("You are already wearing something else!", Color.WHITE));
		} else {
			inventory.removeItem(item);
			equipment.addItem(item.getItemWearPosition(), item);
			sendSuccessMessage(commandResponse, item);
		}
	}
	
	private void sendItemNotFoundMessage(CommandResponse commandResponse, String inputKeyword) {
		String itemNotFoundMessage = MessageFormat.format("You don`t seem to have any {0}.", inputKeyword);
		commandResponse.addResponse(new Response(itemNotFoundMessage, Color.WHITE));
	}
	
	private void sendSuccessMessage(CommandResponse commandResponse, Item item) {
		String itemNotFoundMessage = MessageFormat.format("You wear {0}.", textProperties.getProperty("item." + item.getCode() + ".name"));
		commandResponse.addResponse(new Response(itemNotFoundMessage, Color.WHITE));
	}

}
