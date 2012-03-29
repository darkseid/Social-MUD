package com.smud.model.command.informative;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.smud.model.Color;
import com.smud.model.character.Player;
import com.smud.model.command.Command;
import com.smud.model.command.CommandResponse;
import com.smud.model.command.Response;

public class CommandsCommandTest {

	private static final String COMMANDS_DESCRIPTION = "Available commands:";
	
	private CommandsCommand command;
	
	@Before
	public void setUp() {
		command = new CommandsCommand();
		
		List<Command> commands = new ArrayList<Command>();
		commands.add(command);
		command.setCommands(commands);
	}
	
	@Test
	public void testExecute() throws Exception {
		Player player = new Player();

		CommandResponse expectedResult = new CommandResponse();
		expectedResult.addResponse(new Response(COMMANDS_DESCRIPTION, Color.WHITE));
		expectedResult.addResponse(new Response(command.getCommandName(), Color.WHITE));
		
		CommandResponse result = command.execute(player, null);
		Assert.assertEquals(expectedResult, result);
	}
	
}
