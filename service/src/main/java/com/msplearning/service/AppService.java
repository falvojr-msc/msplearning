package com.msplearning.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.msplearning.entity.App;
import com.msplearning.entity.AppFeature;
import com.msplearning.repository.AppRepository;
import com.msplearning.repository.generic.GenericRepository;
import com.msplearning.service.generic.GenericCrudService;

/**
 * The StudentServiceJpa class provides the business operations of entity
 * {@link App}.
 * 
 * @author Venilton Falvo Junior (veniltonjr)
 */
@Service("appService")
public class AppService extends GenericCrudService<App, Long> {

	@Autowired
	private AppRepository appRepository;

	@Override
	protected GenericRepository<App, Long> getRepository() {
		return this.appRepository;
	}
	
	@Override
	public void insert(App entity) {
		entity.setDateCreation(new Date());
		for (AppFeature appFeature : entity.getAppFeatures()) {
			appFeature.getId().setApp(entity);
		}
		super.insert(entity);
	}
}
