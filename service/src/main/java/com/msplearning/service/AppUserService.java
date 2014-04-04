package com.msplearning.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.msplearning.entity.AppUser;
import com.msplearning.entity.AppUserId;
import com.msplearning.repository.AppUserRepository;
import com.msplearning.repository.generic.GenericRepository;
import com.msplearning.service.generic.GenericCrudService;

/**
 * The AppUserService class provides the business operations of entity {@link AppUser}.
 * 
 * @author Venilton Falvo Junior (veniltonjr)
 */
@Service("appUserService")
public class AppUserService extends GenericCrudService<AppUser, AppUserId> {

	@Autowired
	private AppUserRepository appUserRepository;

	@Override
	protected GenericRepository<AppUser, AppUserId> getRepository() {
		return this.appUserRepository;
	}
}
