package com.om.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.om.entity.User;
import com.om.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
	
	@Autowired
	private UserService userService;
	@GetMapping("/")
	String index() {
		return "index";
	}
	
	@GetMapping("/register")
	String register() {
		return "register";
	}
	
	@GetMapping("/signin")
	String login() {
		return "login";
	}
	
	@GetMapping("/about")
	String about() {
		return "about";
	}
	
	@GetMapping("/contact")
	String contact() {
		return "contact";
	}
	
	@Bean
	BCryptPasswordEncoder PasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@PostMapping("/saveUser")
	public String saveUser(@ModelAttribute User user, HttpSession session) {
		
		System.out.println(user);
		user.setPassword(PasswordEncoder().encode(user.getPassword()));
		user.setRole("ROLE_USER");
		User newUser = userService.saveUser(user);
		
		if (newUser != null) {
//			System.out.println("Succesful");
			session.setAttribute("msg", "Registration Succesful");
		}else {
//			System.out.println("Something went wrong");
			session.setAttribute("msg", "Something Went Wrong");
		}
		
		return "redirect:/register";
	}
	
}
