package com.smud.model.command.communication;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.smud.model.Color;
import com.smud.model.Room;
import com.smud.model.character.Player;
import com.smud.model.command.CommandResponse;
import com.smud.model.command.Response;

public class SayCommandTest {

	private SayCommand command;
	
	@Before
	public void setUp() {
		command = new SayCommand();
	}
	
	@Test
	public void testReturnMessageToPlayer() {
		Player player = new Player();
		player.enters(new Room());
		String parameters = "message";
		
		CommandResponse result = command.execute(player, parameters);
		
		CommandResponse expectedResult = new CommandResponse();
		expectedResult.addResponse(new Response("You say: message", Color.WHITE));
		
		Assert.assertEquals(expectedResult, result);
	}
	
	@Test
	public void testSendMessageToOtherPlayers() {
		Room room = new Room();
		
		Player player = new Player();
		player.setName("player1");
		player.enters(room);
		
		Player otherPlayer = new Player();
		otherPlayer.setName("player2");
		otherPlayer.enters(room);
		
		String parameters = "message";
		
		command.execute(player, parameters);
		
		Response result = otherPlayer.getResponse();
		Assert.assertEquals(new Response("player1 says: message", Color.WHITE), result);
	}
}
