package com.om.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.om.repository.UserDao;

//@Component
@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	public String saveUser() {
		boolean flag = userDao.saveUser();
		
		if (flag) {
			return "succesfull";
		}else {
			return "failed";
		}
		
	}
	
}
