package com.msplearning.rest.app;

import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.msplearning.entity.Discipline;
import com.msplearning.rest.app.generic.GenericCrudRestService;
import com.msplearning.service.DisciplineService;
import com.msplearning.service.generic.GenericCrudService;

/**
 * The DisciplineRestService class provides the RESTful services of entity {@link Discipline}.
 * 
 * @author Venilton Falvo Junior (veniltonjr)
 */
@Component
@Path("/discipline")
public class DisciplineRestService extends GenericCrudRestService<Discipline, Long> {

	@Autowired
	private DisciplineService disciplineService;

	@Override
	protected GenericCrudService<Discipline, Long> getService() {
		return this.disciplineService;
	}

}
