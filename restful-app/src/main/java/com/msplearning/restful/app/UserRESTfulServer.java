package com.msplearning.restful.app;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.msplearning.entity.User;
import com.msplearning.entity.common.BusinessException;
import com.msplearning.entity.common.Response;
import com.msplearning.entity.common.Status;
import com.msplearning.service.UserService;

/**
 * The UserRESTfulServer class provides the RESTful services of the generic
 * entity {@link User}.
 * 
 * @author Venilton Falvo Junior (veniltonjr)
 */
@Component
@Path("/user")
public class UserRESTfulServer {

	@Autowired
	private UserService userService;

	@Path("auth")
	@POST
	public Response authenticate(User user) {
		try {
			this.userService.authenticate(user.getUsername(), user.getPassword());
			return new Response(Status.OK);
		} catch (BusinessException businessException) {
			return new Response(Status.OK);
		}
	}

	@Path("{username}")
	@GET
	public Response findByUsername(@PathParam("username") String username) {
		try {
			this.userService.findByUsername(username);
			return new Response(Status.OK);
		} catch (BusinessException businessException) {
			return new Response(Status.OK, businessException);
		}
	}
}
