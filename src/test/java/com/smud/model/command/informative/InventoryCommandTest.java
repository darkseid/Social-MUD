package com.smud.model.command.informative;

import java.util.Arrays;
import java.util.Properties;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.smud.model.Color;
import com.smud.model.Inventory;
import com.smud.model.Item;
import com.smud.model.Keywords;
import com.smud.model.character.Player;
import com.smud.model.command.CommandResponse;
import com.smud.model.command.Response;

public class InventoryCommandTest {

	private static final String INVENTORY_DESCRIPTION = "You are carrying:";
	private static final String INVENTORY_EMPTY = "Nothing.";
	
	private InventoryCommand command;
	
	@Before
	public void setUp() {
		this.command = new InventoryCommand();
	}
	
	@Test
	public void testExecuteWithEmptyInventory() throws Exception {
		Player player = new Player();
		player.setInventory(new Inventory());

		CommandResponse expectedResult = new CommandResponse();
		expectedResult.addResponse(new Response(INVENTORY_DESCRIPTION, Color.WHITE));
		expectedResult.addResponse(new Response(INVENTORY_EMPTY, Color.WHITE));
		
		CommandResponse result = this.command.execute(player, null);
		Assert.assertEquals(expectedResult, result);
	}
	
	@Test
	public void testExecuteWithNonEmptyInventory() throws Exception {
		Keywords keywords = new Keywords();
		keywords.setWords(Arrays.asList("item"));
		Item item = new Item();
		item.setCode(1);
		item.setKeywords(keywords);
		
		Player player = new Player();
		Inventory inventory = new Inventory();
		inventory.addItem(item);
		player.setInventory(inventory);

		Properties textProperties = new Properties();
		textProperties.put("item.1.name", "item name");
		command.setTextProperties(textProperties);
		
		CommandResponse expectedResult = new CommandResponse();
		expectedResult.addResponse(new Response(INVENTORY_DESCRIPTION, Color.WHITE));
		expectedResult.addResponse(new Response("item name", Color.WHITE));
		
		CommandResponse result = this.command.execute(player, null);
		Assert.assertEquals(expectedResult, result);
	}
	
}
