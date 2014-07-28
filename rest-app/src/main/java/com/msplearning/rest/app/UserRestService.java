package com.msplearning.rest.app;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.msplearning.entity.User;
import com.msplearning.entity.common.BusinessException;
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

	@POST
	@Path("auth/")
	public User authenticate(User credential) throws BusinessException {
		return this.userService.authenticate(credential.getEmail(), credential.getPassword());
	}

	@GET
	@Path("{email}")
	public User verifyEmail(@PathParam("email") String email) throws BusinessException {
		return this.userService.findByEmail(email);
	}
}
