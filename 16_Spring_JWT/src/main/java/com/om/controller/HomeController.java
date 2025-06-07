package com.om.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
public class HomeController {

	@RequestMapping("/users")
	public String getUsers() {
		System.out.println("Getting Users");
		return "Users";
	}
	
}
