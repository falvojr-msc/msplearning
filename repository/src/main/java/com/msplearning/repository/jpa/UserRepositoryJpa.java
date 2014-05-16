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
@Repository("userRepository")
public class UserRepositoryJpa extends GenericRepositoryJpa<User, Long> implements UserRepository {

	@SuppressWarnings("unchecked")
	@Override
	public User authenticate(String email, String password) {
		String jpql = "FROM User WHERE email = :email AND password = :password";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("email", email);
		params.put("password", password);
		List<User> users = (List<User>) this.findByJPQL(jpql, params);
		return this.firstOrDefault(users);
	}

	@SuppressWarnings("unchecked")
	@Override
	public User findByEmail(String email) {
		String jpql = "FROM User WHERE email = :email";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("email", email);
		List<User> users = (List<User>) this.findByJPQL(jpql, params);
		return this.firstOrDefault(users);
	}

	protected User firstOrDefault(List<User> users) {
		return users.isEmpty() ? null : users.get(0);
	}
}
