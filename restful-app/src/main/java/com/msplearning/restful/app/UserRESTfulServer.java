package com.msplearning.restful.app;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.msplearning.entity.User;
import com.msplearning.service.UserService;
import com.msplearning.service.exception.ServiceException;

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
	public boolean authenticate(User user) throws ServiceException {
		try {
			return this.userService.authenticate(user.getUsername(), user.getPassword());
		} catch (Exception e) {
			return false;
		}
	}

	@Path("{username}")
	@GET
	public User findByUsername(@PathParam("username") String username) throws ServiceException {
		try {
			return this.userService.findByUsername(username);
		} catch (Exception e) {
			return null;
		}
	}
}
