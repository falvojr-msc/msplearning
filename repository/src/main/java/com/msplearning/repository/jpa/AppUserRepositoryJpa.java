package com.msplearning.repository.jpa;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.msplearning.entity.App;
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

	@Override
	@SuppressWarnings("unchecked")
	public List<AppUser> findAccessRequests(AppUserId id) {
		String jpql = "FROM AppUser WHERE id.app.id = :appId AND id.user.id != :userId";
		HashMap<String, Object> parans = new HashMap<String, Object>();
		parans.put("appId", id.getApp().getId());
		parans.put("userId", id.getUser().getId());
		return (List<AppUser>) this.findByJPQL(jpql, parans);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<App> findAppsByUser(Long idUser) {
		String jpql = "SELECT appUser.id.app FROM AppUser appUser WHERE appUser.id.user.id = :userId";
		HashMap<String, Object> parans = new HashMap<String, Object>();
		parans.put("userId", idUser);
		return (List<App>) this.findByJPQL(jpql, parans);
	}

}
