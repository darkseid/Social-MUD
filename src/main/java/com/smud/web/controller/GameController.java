package com.smud.web.controller;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.smud.service.CommandsService;
import com.smud.web.model.command.CommandResponse;

@Controller
@RequestMapping("/game/")
public class GameController {

	@Autowired
	private CommandsService commandsService;
	
	@RequestMapping("index.do")
	public ModelAndView hi(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("index");
		return modelAndView;
	}
	
	@RequestMapping("command.do")
	public @ResponseBody CommandResponse command(@RequestParam(value="command") String command) {
		CommandResponse commandResponse = commandsService.parseCommand(command);
		return commandResponse;
	}
	
	public void setCommandsService(CommandsService commandsService) {
		this.commandsService = commandsService;
	}
	
}
