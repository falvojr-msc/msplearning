package com.msplearning.rest.app;

import java.util.List;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.msplearning.entity.Slide;
import com.msplearning.entity.common.BusinessException;
import com.msplearning.entity.common.Response;
import com.msplearning.entity.common.Status;
import com.msplearning.rest.app.generic.GenericCrudRestService;
import com.msplearning.service.SlideService;
import com.msplearning.service.generic.GenericCrudService;

/**
 * The SlideRestService class provides the RESTful services of entity {@link Slide}.
 * 
 * @author Venilton Falvo Junior (veniltonjr)
 */
@Component
@Path("/slide")
public class SlideRestService extends GenericCrudRestService<Slide, Long> {

	@Autowired
	private SlideService slideService;

	@Override
	protected GenericCrudService<Slide, Long> getService() {
		return this.slideService;
	}

	@Path("/lesson")
	@POST
	public Response<List<Slide>> findByLesson(Long idLesson) {
		try {
			return new Response<List<Slide>>(Status.OK, this.slideService.findByLesson(idLesson));
		} catch (BusinessException businessException) {
			return new Response<List<Slide>>(Status.OK, businessException);
		}
	}
}
