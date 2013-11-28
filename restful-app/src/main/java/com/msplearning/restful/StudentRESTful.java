
package com.msplearning.restful;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.msplearning.entity.Student;
import com.msplearning.service.StudentService;

/**
 * The StudentRESTful class provides the RESTful services of entity {@link Student}.
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
    public Response insert(Student student) {
    	Response response;
    	try {
    		this.studentServiceJpa.insert(student);
    		response = Response.status(Status.OK).entity(student.getId()).build();
		} catch (Exception exception) {
			response = Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
    	return response;
    }
}
