package com.smud.web.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class GameController {

	@RequestMapping("index.do")
	public ModelAndView hi() {
		return new ModelAndView("index");
	}
	
}
