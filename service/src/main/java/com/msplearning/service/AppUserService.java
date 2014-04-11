package com.msplearning.service;

import java.util.Date;
import java.util.List;

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

	@Override
	public void insert(AppUser entity) {
		entity.setDateRequest(new Date());
		super.insert(entity);
	}

	public List<AppUser> findManagedAppUsersFrom(Long appId, Long userId) {
		return appUserRepository.findManagedAppUsersFrom(appId, userId);
	}
}
