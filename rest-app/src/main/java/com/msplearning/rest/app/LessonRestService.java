package com.msplearning.rest.app;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.msplearning.entity.Lesson;
import com.msplearning.rest.app.generic.GenericCrudRestService;
import com.msplearning.service.LessonService;
import com.msplearning.service.generic.GenericCrudService;

/**
 * The LessonRestService class provides the RESTful services of entity {@link Lesson}.
 *
 * @author Venilton Falvo Junior (veniltonjr)
 */
@Component
@Path("/lesson")
public class LessonRestService extends GenericCrudRestService<Lesson, Long> {

	@Autowired
	private LessonService lessonService;

	@Override
	protected GenericCrudService<Lesson, Long> getService() {
		return this.lessonService;
	}

	@GET
	@Path("/discipline/{idDiscipline}")
	public List<Lesson> findByDiscipline(@PathParam("idDiscipline") Long idDiscipline) {
		return this.lessonService.findByDiscipline(idDiscipline);
	}
}
