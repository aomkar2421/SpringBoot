package com.om.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.om.entity.User;
import com.om.repository.UserRepo;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private UserRepo userRepo;
	
	@GetMapping("/profile")
	public String userProfile() {
		return "admin_profile";
	}

	@GetMapping("/home")
	String home() {
		return "admin_home";
	}
	
	@ModelAttribute
	public void commonUser(Principal p, Model m) {
		if (p != null) {
			String email = p.getName();
			User user = userRepo.findByEmail(email);
			m.addAttribute("user", user);
		}
	}

}
