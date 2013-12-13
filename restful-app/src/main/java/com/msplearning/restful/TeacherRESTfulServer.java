package com.msplearning.restful;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.msplearning.entity.Teacher;
import com.msplearning.service.TeacherService;

/**
 * The TeacherRESTfulServer class provides the RESTful services of entity
 * {@link Teacher}.
 * 
 * @author Venilton Falvo Junior (veniltonjr)
 */
@Component
@Path("/teacher")
public class TeacherRESTfulServer {

	private final Logger logger = LoggerFactory.getLogger(TeacherRESTfulServer.class);
	
	@Autowired
	private TeacherService teacherServiceJpa;

	@POST
	@Path("/insert")
	public Long insert(Teacher teacher) {
		Long id = null;
		try {
			this.teacherServiceJpa.insert(teacher);
			id = teacher.getId();
		} catch (Exception exception) {
			logger.error("An error occurred while trying to insert a Teacher", exception);
		}
		return id;
	}

	@POST
	@Path("/update")
	public void update(Teacher teacher) {
		try {
			this.teacherServiceJpa.update(teacher);
		} catch (Exception exception) {
			logger.error("An error occurred while trying to update a Teacher", exception);
		}
	}

	@DELETE
	@Path("/delete/{id}")
	public void delete(@PathParam("id") Long idTeacher) {
		try {
			this.teacherServiceJpa.delete(idTeacher);
		} catch (Exception exception) {
			logger.error("An error occurred while trying to delete a Teacher", exception);
		}
	}
	
	@GET
	@Path("/find/{id}")
	public Teacher findById(@PathParam("id") Long idTeacher) {
		Teacher teacher = null;
		try {
			teacher = this.teacherServiceJpa.findById(idTeacher);
		} catch (Exception exception) {
			logger.error("An error occurred while trying to find a Teacher", exception);
		}
		return teacher;
	}
}
