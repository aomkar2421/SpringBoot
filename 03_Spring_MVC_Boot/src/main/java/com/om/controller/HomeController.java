package com.om.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@RequestMapping("/home")
	String homePage() {
		return "home";
	}
	
	
	@RequestMapping("/about")
	String aboutPage() {
		return "about";
	}

}
