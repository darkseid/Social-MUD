package com.smud.web.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.smud.model.User;
import com.smud.model.character.CharacterClass;
import com.smud.service.UserService;

@Controller
public class AuthenticationController {

	@Autowired
	private UserService userService;
	
	@RequestMapping("login.do")
	public ModelAndView login() {
		return new ModelAndView("login");
	}
	
	@RequestMapping(value="authenticate.do", method=RequestMethod.POST)
	public ModelAndView authenticate(HttpServletRequest request, @RequestParam(value="user") String userName, @RequestParam(value="password") String password) {
		
		User user = userService.findUser(userName);
		
		if (user != null && user.getPassword().equals(password)) {
			HttpSession session = request.getSession();
			session.setAttribute("authenticated_user", user);
			
			return new ModelAndView("redirect:/game/index.do");
		} else {
			ArrayList<FieldError> errors = new ArrayList<FieldError>();
			errors.add(new FieldError("user", "password", "Wrong username / password"));
			request.setAttribute("errors", errors);
			return login();
		}
	}
	
	@RequestMapping(value="newUser.do", method=RequestMethod.POST)
	public ModelAndView addUser(HttpServletRequest request, 
			@RequestParam String userName, 
			@RequestParam String password, 
			@RequestParam String className) {
		CharacterClass characterClass = null;
		try {
			characterClass = CharacterClass.valueOf(className);
		} catch (Exception e) {
			ModelAndView modelAndView = register();
			ArrayList<FieldError> errors = new ArrayList<FieldError>();
			errors.add(new FieldError("user", "className", "Invalid class"));
			modelAndView.addObject("errors", errors);
			return modelAndView;
		}
		User user = userService.addUser(userName, password, characterClass);
		request.getSession().setAttribute("authenticated_user", user);
		return new ModelAndView("redirect:/game/index.do");
	}
	
	@RequestMapping(value="register.do", method=RequestMethod.GET)
	public ModelAndView register() {
		ModelAndView modelAndView = new ModelAndView("register");
		modelAndView.addObject("classes", CharacterClass.values());
		return modelAndView;
	}
}
