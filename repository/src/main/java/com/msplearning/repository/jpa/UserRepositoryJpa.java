package com.msplearning.repository.jpa;

import java.util.List;

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
		String jpql = "FROM User WHERE username = ? AND password = ?";
		List<User> users = (List<User>) this.findByJPQL(jpql, username, password);
		return !users.isEmpty();
	}

	@SuppressWarnings("unchecked")
	@Override
	public User findByUsername(String username) {
		String jpql = "FROM User WHERE username = ?";
		List<User> users = (List<User>) this.findByJPQL(jpql, username);
		return users.isEmpty() ? null : users.get(0);
	}
}
