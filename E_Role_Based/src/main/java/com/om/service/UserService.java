package com.om.service;

import org.springframework.stereotype.Service;

import com.om.entity.User;

public interface UserService {

	public User saveUser(User user);
	
	public void removeSessionMessage();
}
