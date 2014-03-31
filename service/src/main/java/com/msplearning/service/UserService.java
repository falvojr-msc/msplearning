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

	public void authenticate(String username, String password) {
		if (!this.userRepository.authenticate(username, password)) {
			throw new BusinessException(super.getMessage("project.messages.mi0001"));
		}
	}

	public void findByUsername(String username) {
		if (this.userRepository.findByUsername(username) == null) {
			throw new BusinessException(super.getMessage("project.messages.mi0002"));
		}
	}

}