package com.om.controller;

import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.om.dto.UserRequest;
import com.om.service.UserService;

import io.jsonwebtoken.security.InvalidKeyException;

@org.springframework.web.bind.annotation.RestController
public class RestController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/")
	ResponseEntity<?> index() {
		return new ResponseEntity<>("WELCOME JWT Practise", HttpStatus.OK);
	}

	
	@GetMapping("/all")
	ResponseEntity<?> allUsers() {
		return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
	}

	
	@GetMapping("/register")
	ResponseEntity<?> register() {
		return new ResponseEntity<>("WELCOME JWT Practise register", HttpStatus.OK);
	}

	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody UserRequest userRequest) throws InvalidKeyException, NoSuchAlgorithmException {
		
		String token = userService.loginUser(userRequest);
		
		if (token == null) {
			return new ResponseEntity<>("error occured", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(token, HttpStatus.OK);

	}
	
}
