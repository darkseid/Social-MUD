package com.smud.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.smud.model.User;
import com.smud.model.character.Player;
import com.smud.model.command.CommandResponse;
import com.smud.model.command.Response;
import com.smud.service.CommandsService;

@Controller
@RequestMapping("/game/")
public class GameController {

	@Autowired
	private CommandsService commandsService;
	
	private Logger LOGGER = Logger.getLogger(GameController.class);
	
	@RequestMapping("index.do")
	public ModelAndView hi(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("index");
		return modelAndView;
	}
	
	@RequestMapping("command.do")
	public @ResponseBody CommandResponse command(HttpServletRequest request, @RequestParam(value="command") String command) {
		User user = (User) request.getSession().getAttribute("authenticated_user");
		Player player = user.getPlayer();
		
		CommandResponse commandResponse = commandsService.parseCommand(player, command);
		LOGGER.info(commandResponse);
		return commandResponse;
	}
	
	@RequestMapping("retrieveMessages.do")
	public @ResponseBody CommandResponse retrieveMessage(HttpServletRequest request) {
		User user =  (User) request.getSession().getAttribute("authenticated_user");
		
		Player player = user.getPlayer();
		
		CommandResponse commandResponse = new CommandResponse();
		Response response = player.getResponse();
		//TODO add a time limit to consuming the queue
		while (response != null) {
			commandResponse.addResponse(response);
			response = player.getResponse();
		}
		return commandResponse;
	}
	
	public void setCommandsService(CommandsService commandsService) {
		this.commandsService = commandsService;
	}
	
}
