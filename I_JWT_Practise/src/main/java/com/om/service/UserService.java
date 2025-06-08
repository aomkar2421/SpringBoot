package com.om.service;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.om.dto.UserRequest;
import com.om.entity.User;

import io.jsonwebtoken.security.InvalidKeyException;

public interface UserService {

	public User saveUser(User user);
	
	public String loginUser(UserRequest userRequest) throws InvalidKeyException, NoSuchAlgorithmException;
	
	public void removeSessionMessage();
	
	public List<User> getAllUsers();
}
