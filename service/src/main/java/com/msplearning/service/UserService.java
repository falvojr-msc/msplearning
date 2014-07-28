package com.msplearning.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.msplearning.entity.User;
import com.msplearning.entity.common.BusinessException;
import com.msplearning.repository.UserRepository;
import com.msplearning.service.generic.BaseService;

/**
 * The UserService class provides the business operations of entity {@link User}.
 *
 * @author Venilton Falvo Junior (veniltonjr)
 */
@Service("userService")
public class UserService extends BaseService {

	@Autowired
	private UserRepository userRepository;

	public User authenticate(String email, String password) throws BusinessException {
		User user = this.userRepository.authenticate(email, password);
		if (user == null) {
			throw new BusinessException(super.getMessage("R.message.001"));
		}
		return user;
	}

	public User findByEmail(String email) throws BusinessException {
		User user = this.userRepository.findByEmail(email);
		if (user == null) {
			throw new BusinessException(super.getMessage("R.message.002"));
		}
		return user;
	}
}