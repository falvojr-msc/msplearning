package com.msplearning.repository.jpa;

import org.springframework.stereotype.Repository;

import com.msplearning.entity.AppUser;
import com.msplearning.entity.AppUserId;
import com.msplearning.repository.AppUserRepository;
import com.msplearning.repository.jpa.generic.GenericRepositoryJpa;

/**
 * The AppUserRepositoryJpa class provides the persistence operations of entity {@link AppUser}.
 * 
 * @author Venilton Falvo Junior (veniltonjr)
 */
@Repository("appUserRepository")
public class AppUserRepositoryJpa extends GenericRepositoryJpa<AppUser, AppUserId> implements AppUserRepository {

}
