package com.smud.model.command.informative;

import java.text.MessageFormat;
import java.util.Properties;

import javax.annotation.Resource;

import com.smud.model.Color;
import com.smud.model.character.Player;
import com.smud.model.command.Command;
import com.smud.model.command.CommandResponse;
import com.smud.model.command.Response;
import com.smud.model.item.Equipment;
import com.smud.model.item.Item;
import com.smud.model.item.ItemWearSlot;

public class EquipmentCommand implements Command {

	private static final String COMMAND_NAME = "equipment";
	
	@Resource(name="textProperties")
	private Properties textProperties;
	
	@Override
	public CommandResponse execute(Player player, String parameters) {
		CommandResponse commandResponse = new CommandResponse();
		commandResponse.addResponse(new Response("*** Equipment ***", Color.DARK_GREEN));
		Equipment equipment = player.getEquipment();
		for (ItemWearSlot itemWearSlot : ItemWearSlot.values()) {
			Item item = equipment.getItem(itemWearSlot);
			if (item != null) {
				commandResponse.addResponse(createEquipmentItemResponse(item, itemWearSlot));
			}
		}
		return commandResponse;
	}

	@Override
	public String getCommandName() {
		return COMMAND_NAME;
	}
	

	private Response createEquipmentItemResponse(Item item, ItemWearSlot itemWearSlot) {
		String slotDescription = textProperties.getProperty("equipment." + itemWearSlot.name() + ".description");
		String itemName = textProperties.getProperty("item." + item.getCode() + ".name");
		return new Response(MessageFormat.format("[color=DARK_GREEN]{0}[/color] {1}", slotDescription, itemName), Color.WHITE);
	}

}
