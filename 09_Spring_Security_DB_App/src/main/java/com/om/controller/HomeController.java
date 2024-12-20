package com.om.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("/profile")
	public String profile() {
		return "profile";
	}
	
	@GetMapping("/about")
	public String about() {
		return "about";
	}
	
	@GetMapping("/signin")
	public String signin() {
		return "login";
	}
	
	@GetMapping("/invalid")
	public String invalid() {
		return "error";
	}
	
	@GetMapping("/userlogout")
	public String userlogout() {
		return "logout";
	}
	
	
}
