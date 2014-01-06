package com.msplearning.service.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.msplearning.entity.User;
import com.msplearning.repository.UserRepository;
import com.msplearning.service.UserService;

/**
 * The UserServiceJpa class provides the business operations of entity {@link User}.
 * 
 * @author Venilton Falvo Junior (veniltonjr)
 */
@Service("userServiceJpa")
public class UserServiceJpa implements UserService {

	@Autowired
	private UserRepository userRepositoryJpa;
	
	@Override
	public boolean authenticate(String username, String password) {
		return this.userRepositoryJpa.authenticate(username, password);
	}

	@Override
	public User findByUsername(String username) {
		return this.userRepositoryJpa.findByUsername(username);
	}
}
