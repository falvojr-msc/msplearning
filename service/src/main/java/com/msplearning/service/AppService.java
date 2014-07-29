package com.msplearning.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.msplearning.entity.App;
import com.msplearning.entity.AppFeature;
import com.msplearning.entity.AppUser;
import com.msplearning.entity.AppUserId;
import com.msplearning.repository.AppRepository;
import com.msplearning.repository.generic.GenericRepository;
import com.msplearning.service.generic.GenericCrudService;

/**
 * The AppService class provides the business operations of entity {@link App}.
 *
 * @author Venilton Falvo Junior (veniltonjr)
 */
@Service("appService")
public class AppService extends GenericCrudService<App, Long> {

	@Autowired
	private AppUserService appUserService;

	@Autowired
	private AppRepository appRepository;

	@Override
	protected GenericRepository<App, Long> getRepository() {
		return this.appRepository;
	}

	public void insert(App entity, Long idUser) {
		// Configuration and persistence of App:
		entity.setDateCreation(new Date());
		for (AppFeature appFeature : entity.getAppFeatures()) {
			appFeature.getId().setApp(entity);
		}
		super.insert(entity);
		for (AppFeature appFeature : entity.getAppFeatures()) {
			appFeature.getId().setApp(new App(entity.getId()));
		}
		// Configuration and persistence of App Administrator:
		AppUser appUser = new AppUser();
		appUser.setId(new AppUserId(entity.getId(), idUser));
		appUser.setAdmin(true);
		appUser.setActive(true);
		this.appUserService.insert(appUser);
	}

	public List<App> findByUser(Long idUser) {
		return this.appUserService.findAppsByUser(idUser);
	}
}
