package com.msplearning.rest.app;

import java.util.List;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.msplearning.entity.Lesson;
import com.msplearning.entity.common.BusinessException;
import com.msplearning.entity.common.Response;
import com.msplearning.entity.common.Status;
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

	@Path("/discipline")
	@POST
	public Response<List<Lesson>> findByDiscipline(Long idDiscipline) {
		try {
			return new Response<List<Lesson>>(Status.OK, this.lessonService.findByDiscipline(idDiscipline));
		} catch (BusinessException businessException) {
			return new Response<List<Lesson>>(Status.OK, businessException);
		}
	}
}
