package com.msplearning.repository.jpa;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.msplearning.entity.User;
import com.msplearning.repository.UserRepository;
import com.msplearning.repository.jpa.generic.GenericRepositoryJpa;

/**
 * The UserRepositoryJpa class provides the persistence operations of entity
 * {@link User}.
 * 
 * @author Venilton Falvo Junior (veniltonjr)
 */
@Repository("userDaoJpa")
public class UserRepositoryJpa extends GenericRepositoryJpa<User, Long> implements UserRepository {

	@SuppressWarnings("unchecked")
	@Override
	public boolean authenticate(String username, String password) {
		String jpql = "FROM User WHERE username = :username AND password = :password";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("username", username);
		params.put("password", password);
		List<User> users = (List<User>) this.findByJPQL(jpql, params);
		return !users.isEmpty();
	}

	@SuppressWarnings("unchecked")
	@Override
	public User findByUsername(String username) {
		String jpql = "FROM User WHERE username = :username";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("username", username);
		List<User> users = (List<User>) this.findByJPQL(jpql, params);
		return users.isEmpty() ? null : users.get(0);
	}
}
