//package com.om.controller;
//
//import java.security.Principal;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//
//import com.om.entity.User;
//import com.om.repository.UserRepo;
//import com.om.service.UserService;
//
//import jakarta.servlet.http.HttpSession;
//
//@Controller
//public class HomeController {
//
//	@Autowired
//	private UserService userService;
//
//	@Autowired
//	private UserRepo userRepo;
//
//	@GetMapping("/register")
//	String register() {
//		return "register";
//	}
//
//	@GetMapping("/signin")
//	String login() {
//		return "login";
//	}
//
//	@GetMapping("/user/profile")
//	String profile() {
//		return "profile";
//	}
//
//	@GetMapping("/user/home")
//	String home() {
//		return "home";
//	}
//
//	@ModelAttribute
//	public void commonUser(Principal p, Model m) {
//		if (p!=null) {
//			String email = p.getName();
//
//			User user = userRepo.findByEmail(email);
//			if (user != null) {
//				m.addAttribute("name", user.getName());
//				m.addAttribute("email", user.getEmail());
//				m.addAttribute("mobile", user.getMobileNo());
//				m.addAttribute("role", user.getRole());
//			}	
//		}
//	}
//
//	@Bean
//	BCryptPasswordEncoder PasswordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
//
//	@PostMapping("/saveUser")
//	public String saveUser(@ModelAttribute User user, HttpSession session) {
//
//		System.out.println(user);
//
//		user.setPassword(PasswordEncoder().encode(user.getPassword()));
//		user.setRole("ROLE_USER");
//		User newUser = userService.saveUser(user);
//
//		if (newUser != null) {
//			//			System.out.println("Succesful");
//			session.setAttribute("msg", "Registration Succesful");
//		}else {
//			//			System.out.println("Something went wrong");
//			session.setAttribute("msg", "User with this email already exists");
//		}
//
//		return "redirect:/register";
//	}
//
//}
