package com.msplearning.restful;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.msplearning.entity.Student;
import com.msplearning.service.StudentService;

/**
 * The StudentRESTful class provides the RESTful services of entity
 * {@link Student}.
 * 
 * @author Venilton Falvo Junior (veniltonjr)
 */
@Component
@Path("/student")
public class StudentRESTful {

	@Autowired
	private StudentService studentServiceJpa;

	@POST
	@Path("/insert")
	public Long insert(Student student) {
		Long id = null;
		try {
			this.studentServiceJpa.insert(student);
			id = student.getId();
		} catch (Exception exception) {
			// TODO: Student insert log.
		}
		return id;
	}

	@POST
	@Path("/update")
	public void update(Student student) {
		try {
			this.studentServiceJpa.update(student);
		} catch (Exception exception) {
			// TODO: Student update log.
		}
	}

	@DELETE
	@Path("/delete/{id}")
	public void delete(@PathParam("id") Long idStudent) {
		try {
			this.studentServiceJpa.delete(idStudent);
		} catch (Exception exception) {
			// TODO: Student delete log.
		}
	}
	
	@GET
	@Path("/find/{id}")
	public Student findById(@PathParam("id") Long idStudent) {
		Student student = null;
		try {
			student = this.studentServiceJpa.findById(idStudent);
		} catch (Exception exception) {
			// TODO: Student find log.
		}
		return student;
	}
}
