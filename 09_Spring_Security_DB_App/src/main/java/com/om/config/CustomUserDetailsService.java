package com.om.config;

import org.hibernate.annotations.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.om.entity.Employee;
import com.om.repository.EmpRepo;

@Component
public class CustomUserDetailsService implements UserDetailsService{

	
	
	@Autowired
	private EmpRepo empRepo;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		Employee emp = empRepo.findByEmail(email);
		
		if (emp == null) {
			throw new UsernameNotFoundException("User name not found");
		}else {
			return new CustomUser(emp);
		}
		
	}

}
