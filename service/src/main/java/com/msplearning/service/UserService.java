package com.msplearning.service;

import com.msplearning.entity.User;

/**
 * Interface of {@link UserService}.
 * 
 * @author Venilton Falvo Junior (veniltonjr)
 */
public interface UserService {

	boolean authenticate(String username, String password);

	User findByUsername(String username);

}
