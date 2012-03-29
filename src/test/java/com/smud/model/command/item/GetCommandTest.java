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

public class GetCommandTest {

	private GetCommand command;
	
	@Before
	public void setUp() {
		command = new GetCommand();
	}
	
	@Test
	public void testNullParameter() {
		Player player = new Player();
		CommandResponse result = command.execute(player, null);
		Response responseResult = result.getResponses().get(0);
		Assert.assertEquals("Get what?!?!", responseResult.getText());
	}
	
	@Test
	public void testEmptyParameter() {
		Player player = new Player();
		CommandResponse result = command.execute(player, " ");
		Response responseResult = result.getResponses().get(0);
		Assert.assertEquals("Get what?!?!", responseResult.getText());
	}
	
	@Test
	public void testItemNotFound() {
		Room room = new Room();
		Player player = new Player();
		player.enters(room);
		CommandResponse result = command.execute(player, "item");
		Response responseResult = result.getResponses().get(0);
		Assert.assertEquals("There is no item here.", responseResult.getText());
	}

	@Test
	public void testGetItemAddsItemToPlayerInventory() {
		Keywords keywords = new Keywords();
		keywords.setWords(Arrays.asList("item"));
		Item item = new Item();
		item.setId(1);
		item.setKeywords(keywords);
		
		Room room = new Room();
		room.addItem(item);
		
		Player player = new Player();
		player.setInventory(new Inventory());
		player.enters(room);
		
		Properties textProperties = new Properties();
		textProperties.put("item.item.name", "item name");
		command.setTextProperties(textProperties);
		command.execute(player, "item");
		
		List<Item> inventoryItems = player.getInventory().getItems();
		Assert.assertSame(item, inventoryItems.get(0));
	}
	
	@Test
	public void testGetItemRemovesItemFromRoom() {
		Keywords keywords = new Keywords();
		keywords.setWords(Arrays.asList("item"));
		Item item = new Item();
		item.setId(1);
		item.setKeywords(keywords);
		
		Room room = new Room();
		room.addItem(item);
		
		Player player = new Player();
		player.setInventory(new Inventory());
		player.enters(room);
		
		Properties textProperties = new Properties();
		textProperties.put("item.item.name", "item name");
		command.setTextProperties(textProperties);
		command.execute(player, "item");
		
		List<Item> roomItems = room.getItems();
		Assert.assertEquals(0, roomItems.size());
	}
}
