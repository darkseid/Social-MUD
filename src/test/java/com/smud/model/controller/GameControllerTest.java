package com.smud.model.controller;

import junit.framework.TestCase;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.servlet.DispatcherServlet;

import com.smud.model.Player;
import com.smud.model.Room;
import com.smud.web.MockWebApplication;
import com.smud.web.MockWebApplicationContextLoader;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:servlet-context.xml", "classpath:commands.xml" },
	loader=MockWebApplicationContextLoader.class)
@MockWebApplication(name="game-controller")
public class GameControllerTest extends TestCase {

	@Autowired
	private DispatcherServlet servlet;
	
	@Test
	public void testLookCommandWithoutArgs() throws Exception {
		
		MockHttpServletRequest request = new MockHttpServletRequest("GET", "/game/command.do");
		
		Room room = new Room();
		
		Player player = new Player();
		player.setId(1);
		player.setName("mock");
		player.setInRoom(room);
		
		
		
		request.getSession().setAttribute("authenticated_user", player);
		
		request.addParameter("command", "look");
		
		MockHttpServletResponse response = new MockHttpServletResponse();
		servlet.service(request, response);
		String results = response.getContentAsString().trim();

		Assert.assertEquals("{\"responses\":[\"LookCommand executed\"]}", results);
	}
}
