package com.msplearning.rest.app;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

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

	@GET
	@Path("/app/{idApp}")
	public List<Discipline> findByApp(@PathParam("idApp") Long idApp) {
		return this.disciplineService.findByApp(idApp);
	}
}
