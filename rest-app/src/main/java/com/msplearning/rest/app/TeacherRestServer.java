package com.msplearning.rest.app;

import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.msplearning.entity.Teacher;
import com.msplearning.rest.app.generic.GenericCrudRestServer;
import com.msplearning.service.TeacherService;
import com.msplearning.service.generic.GenericCrudService;

/**
 * The TeacherRESTfulServer class provides the RESTful services of entity
 * {@link Teacher}.
 * 
 * @author Venilton Falvo Junior (veniltonjr)
 */
@Component
@Path("/teacher")
public class TeacherRestServer extends GenericCrudRestServer<Teacher, Long> {

	@Autowired
	private TeacherService teacherService;

	@Override
	protected GenericCrudService<Teacher, Long> getService() {
		return this.teacherService;
	}

}
