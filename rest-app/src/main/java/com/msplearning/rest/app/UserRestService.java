package com.msplearning.rest.app;

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
 * The UserRestService class provides the RESTful services of the generic entity {@link User}.
 * 
 * @author Venilton Falvo Junior (veniltonjr)
 */
@Component
@Path("/user")
public class UserRestService {

	@Autowired
	private UserService userService;

	@Path("auth")
	@POST
	public Response<User> authenticate(User user) {
		try {
			return new Response<User>(Status.OK, this.userService.authenticate(user.getUsername(), user.getPassword()));
		} catch (BusinessException businessException) {
			return new Response<User>(Status.OK, businessException);
		}
	}

	@Path("{username}")
	@GET
	public Response<Void> verifyUsername(@PathParam("username") String username) {
		try {
			this.userService.verifyUsername(username);
			return new Response<Void>(Status.OK);
		} catch (BusinessException businessException) {
			return new Response<Void>(Status.OK, businessException);
		}
	}
}
