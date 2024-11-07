package com.om.service;

import com.om.entity.User;

public interface UserService {
	
	public User saveUser(User user);
	
	public void removeSessionMessage();

}
