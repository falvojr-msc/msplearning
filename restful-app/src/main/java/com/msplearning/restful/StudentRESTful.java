
package com.msplearning.restful;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

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
    public Student insert(Student student) {
    	Student studentResponse = null;
    	try {
    		this.studentServiceJpa.insert(student);
    		studentResponse = student;
		} catch (Exception exception) { }
    	
    	return studentResponse;
    }
    
    @GET
    @Path("/get")
    public String get() {

    	return "Foi!";
    }
}
