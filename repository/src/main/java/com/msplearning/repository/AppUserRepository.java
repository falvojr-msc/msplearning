package com.msplearning.repository;

import java.util.List;

import com.msplearning.entity.AppUser;
import com.msplearning.entity.AppUserId;
import com.msplearning.repository.generic.GenericRepository;
import com.msplearning.repository.jpa.AppUserRepositoryJpa;

/**
 * Interface of {@link AppUserRepositoryJpa}.
 * 
 * @author Venilton Falvo Junior (veniltonjr)
 */
public interface AppUserRepository extends GenericRepository<AppUser, AppUserId> {

	List<AppUser> findManagedAppUsersFrom(Long appId, Long userId);

}
