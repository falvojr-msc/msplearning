package com.msplearning.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.msplearning.entity.User;
import com.msplearning.repository.UserRepository;

/**
 * The UserServiceJpa class provides the business operations of entity
 * {@link User}.
 * 
 * @author Venilton Falvo Junior (veniltonjr)
 */
@Service("userService")
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public boolean authenticate(String username, String password) {
		return this.userRepository.authenticate(username, password);
	}

	public User findByUsername(String username) {
		return this.userRepository.findByUsername(username);
	}

}
