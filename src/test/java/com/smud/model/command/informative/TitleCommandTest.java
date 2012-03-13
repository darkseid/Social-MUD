package com.smud.model.command.informative;

import java.text.MessageFormat;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.smud.model.Color;
import com.smud.model.Player;
import com.smud.model.command.CommandResponse;
import com.smud.model.command.Response;

public class TitleCommandTest {

	private TitleCommand command;
	
	@Before
	public void setUp() {
		this.command = new TitleCommand();
	}
	
	@Test
	public void testChangePlayerTitle() {
		Player player = new Player();
		player.setId(1);
		player.setName("Player");
		this.command.execute(player, "Title");
		
		Assert.assertEquals("Title", player.getTitle());
	}
	
	@Test
	public void testExecuteResponse() {
		Player player = new Player();
		player.setId(1);
		player.setName("Player");
		CommandResponse result = this.command.execute(player, "Title");
		
		String responseText = MessageFormat.format("You are now {0} {1}", player.getName(), player.getTitle());
		Assert.assertEquals(result.getResponses().get(0), new Response(responseText, Color.WHITE));
	}
}
