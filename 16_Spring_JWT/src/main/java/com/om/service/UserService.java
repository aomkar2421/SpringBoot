package com.om.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.om.model.User;

@Service
public class UserService {

	private List<User> store = new ArrayList<>();
	
	public UserService() {
		store.add(new User(UUID.randomUUID().toString(), "Omkar Jagtap", "omkar@gmail.com"));
	}
	
}
