package com.msplearning.restful.app;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.msplearning.entity.User;
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

	private final Logger logger = LoggerFactory.getLogger(UserRESTfulServer.class);

	@Autowired
	private UserService userService;

	@Path("auth")
	@POST
	public boolean authenticate(User user) {
		boolean isAuth = false;
		try {
			isAuth = this.userService.authenticate(user.getUsername(), user.getPassword());
		} catch (Exception exception) {
			this.logger.error("An error occurred while trying to authenticate a User", exception);
		}
		return isAuth;
	}

	@Path("{username}")
	@GET
	public User findByUsername(@PathParam("username") String username) {
		User user = null;
		try {
			user = this.userService.findByUsername(username);
		} catch (Exception exception) {
			this.logger.error("An error occurred while trying to find a User by username", exception);
		}
		return user;
	}
}
