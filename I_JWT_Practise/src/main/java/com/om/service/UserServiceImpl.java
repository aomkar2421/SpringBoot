package com.om.service;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.om.dto.UserRequest;
import com.om.entity.User;
import com.om.repository.UserRepo;

import io.jsonwebtoken.security.InvalidKeyException;
import jakarta.servlet.http.HttpSession;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtService jwtService;

	@Override
	public User saveUser(User user) {
		
		String email = user.getEmail();
		User checkUser = userRepo.findByEmail(email);
		if (checkUser != null) {
			return null;
		}
		
		User newUser = userRepo.save(user);
		return user;
	}

	@Override
	public void removeSessionMessage() {
		HttpSession session = ( (ServletRequestAttributes)RequestContextHolder.getRequestAttributes() ).getRequest().getSession();
		session.removeAttribute("msg");
	}

	@Override
	public String loginUser(UserRequest userRequest) throws InvalidKeyException, NoSuchAlgorithmException {
		
		Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userRequest.getEmail(), userRequest.getPassword()));
		
		if (authenticate.isAuthenticated()) {
//			String token = UUID.randomUUID().toString();
			return jwtService.generateToken(userRequest.getEmail());
		}
		
		return null;
	}
	
	
	@Override
	public List<User> getAllUsers(){
		return userRepo.findAll();
	}

}
