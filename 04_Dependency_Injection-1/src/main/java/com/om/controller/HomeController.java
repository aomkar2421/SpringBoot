package com.om.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.om.service.UserService;

@Controller
public class HomeController {

//	@Autowired
//	private UserService userService;

	private UserService userService;
	public HomeController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("/")
	public String home() {
		return "home";
	}

	@RequestMapping("/createUser")
	public String registerUser() {
		String msg = userService.saveUser();
		System.out.println(msg);
		return "success";
	}

}
