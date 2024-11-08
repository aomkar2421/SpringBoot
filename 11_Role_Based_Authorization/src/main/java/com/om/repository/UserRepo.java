package com.om.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.om.entity.User;

public interface UserRepo extends JpaRepository<User, Integer> {
	
	public User findByEmail(String email);

}
