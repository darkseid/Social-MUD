package com.smud.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.smud.model.Player;
import com.smud.service.PlayerService;

@Controller
public class AuthenticationController {

	@Autowired
	private PlayerService playerService;
	
	@RequestMapping("login.do")
	public ModelAndView login() {
		return new ModelAndView("login");
	}
	
	@RequestMapping(value="authenticate.do", method=RequestMethod.POST)
	public ModelAndView authenticate(HttpServletRequest request, String user) {
		Player player = playerService.findPlayer(user);
		if (player != null) {
			HttpSession session = request.getSession();
			session.setAttribute("authenticated_user", player);
			return new ModelAndView("redirect:/game/index.do");
		} else {
			return login();
		}
	}
}
