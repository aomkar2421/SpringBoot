package com.om.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.om.entity.User;
import com.om.repository.UserRepo;
import com.om.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepo userRepo;

	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("/signin")
	public String login() {
		return "login";
	}
	
	@GetMapping("/register")
	public String register() {
		return "register";
	}
	
	@ModelAttribute
	public void commonUser(Principal p, Model m) {
		if (p != null) {
			String email = p.getName();
			User user = userRepo.findByEmail(email);
			m.addAttribute("user", user);
		}
	}
	
//	@GetMapping("/user/profile")
//	public String profile(Principal p, Model m) {
//		
//		String email = p.getName();
//		User user = userRepo.findByEmail(email);
//		m.addAttribute("user", user);
//		
//		return "profile";
//	}
//	
//	@GetMapping("/user/home")
//	public String home() {
//		return "home";
//	}
	
	@PostMapping("/saveUser")
	public String saveUser(@ModelAttribute User user, HttpSession session, Model m, HttpServletRequest request) {
		
//		System.out.println(user);
		
		String url = request.getRequestURL().toString();		
		url = url.replace(request.getServletPath(), "");

		
		User u = userService.saveUser(user, url);
		
		if (u != null) {
			session.setAttribute("msg", "Registered Succesfully");
		}else {
			session.setAttribute("msg", "Something Went Wrong");
		}
		
		return "redirect:/register";
	}
	
	
	@GetMapping("/verify")
	public String verifyAccount(@Param("code") String code, Model m) {
		
		boolean f = userService.verifyAccount(code);
		
		if (f) {
			m.addAttribute("msg", "Your account is verified succesfully");
		}else {
			m.addAttribute("msg", "Verification code is oncorrect or already verified");
		}
		
		return "message";
	}
	
}
