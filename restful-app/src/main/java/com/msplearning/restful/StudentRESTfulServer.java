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

import com.msplearning.entity.Student;
import com.msplearning.service.StudentService;

/**
 * The StudentRESTfulServer class provides the RESTful services of entity
 * {@link Student}.
 * 
 * @author Venilton Falvo Junior (veniltonjr)
 */
@Component
@Path("/student")
public class StudentRESTfulServer {

	private final Logger logger = LoggerFactory.getLogger(StudentRESTfulServer.class);
	
	@Autowired
	private StudentService studentServiceJpa;

	@POST
	@Path("/insert")
	public Long insert(Student student) {
		Long id = null;
		try {
			this.studentServiceJpa.insert(student);
			logger.info("Foi");
			id = student.getId();
		} catch (Exception exception) {
			logger.error("An error occurred while trying to insert a Student", exception);
		}
		return id;
	}

	@POST
	@Path("/update")
	public void update(Student student) {
		try {
			this.studentServiceJpa.update(student);
		} catch (Exception exception) {
			logger.error("An error occurred while trying to update a Student", exception);
		}
	}

	@DELETE
	@Path("/delete/{id}")
	public void delete(@PathParam("id") Long idStudent) {
		try {
			this.studentServiceJpa.delete(idStudent);
		} catch (Exception exception) {
			logger.error("An error occurred while trying to delete a Student", exception);
		}
	}
	
	@GET
	@Path("/find/{id}")
	public Student findById(@PathParam("id") Long idStudent) {
		Student student = null;
		try {
			student = this.studentServiceJpa.findById(idStudent);
		} catch (Exception exception) {
			logger.error("An error occurred while trying to find a Student", exception);
		}
		return student;
	}
}
