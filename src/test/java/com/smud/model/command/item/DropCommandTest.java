package com.smud.model.command.item;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.smud.model.Inventory;
import com.smud.model.Item;
import com.smud.model.Keywords;
import com.smud.model.Room;
import com.smud.model.character.Player;
import com.smud.model.command.CommandResponse;
import com.smud.model.command.Response;

public class DropCommandTest {

	private DropCommand command;
	
	@Before
	public void setUp() {
		command = new DropCommand();
	}
	
	@Test
	public void testNullParameter() {
		Player player = new Player();
		CommandResponse result = command.execute(player, null);
		Response responseResult = result.getResponses().get(0);
		Assert.assertEquals("Drop what?!?!", responseResult.getText());
	}
	
	@Test
	public void testEmptyParameter() {
		Player player = new Player();
		CommandResponse result = command.execute(player, " ");
		Response responseResult = result.getResponses().get(0);
		Assert.assertEquals("Drop what?!?!", responseResult.getText());
	}
	
	@Test
	public void testItemNotFound() {
		Player player = new Player();
		player.setInventory(new Inventory());

		Room room = new Room();
		player.enters(room);
		
		CommandResponse result = command.execute(player, "item");
		Response responseResult = result.getResponses().get(0);
		Assert.assertEquals("There is no item here.", responseResult.getText());
	}

	@Test
	public void testDropItemRemovesItemFromPlayerInventory() {
		Keywords keywords = new Keywords();
		keywords.setWords(Arrays.asList("item"));
		Item item = new Item();
		item.setId(1);
		item.setKeywords(keywords);
		
		Player player = new Player();
		Inventory inventory = new Inventory();
		inventory.addItem(item);
		player.setInventory(inventory);

		Room room = new Room();
		player.enters(room);
		
		Properties textProperties = new Properties();
		textProperties.put("item.1.name", "item name");
		command.setTextProperties(textProperties);
		command.execute(player, "item");
		
		List<Item> inventoryItems = player.getInventory().getItems();
		Assert.assertEquals(0, inventoryItems.size());
	}
	
	@Test
	public void testDropItemAddsItemToRoom() {
		Keywords keywords = new Keywords();
		keywords.setWords(Arrays.asList("item"));
		Item item = new Item();
		item.setId(1);
		item.setKeywords(keywords);
		
		Player player = new Player();
		Inventory inventory = new Inventory();
		inventory.addItem(item);
		player.setInventory(inventory);

		Room room = new Room();
		player.enters(room);
		
		Properties textProperties = new Properties();
		textProperties.put("item.1.name", "item name");
		command.setTextProperties(textProperties);
		command.execute(player, "item");
		
		List<Item> roomItems = room.getItems();
		Assert.assertSame(item, roomItems.get(0));
	}
}
