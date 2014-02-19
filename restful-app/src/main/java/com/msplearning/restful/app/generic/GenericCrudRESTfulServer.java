package com.msplearning.restful.app.generic;

import java.io.Serializable;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import com.msplearning.entity.util.BusinessException;
import com.msplearning.service.generic.GenericCrudService;

/**
 * BaseCrudController.
 * 
 * @param <T>
 *            tipo entidade
 * @param <K>
 *            tipo de chave
 * 
 * @author Venilton Falvo Junior (veniltonjr)
 */
public abstract class GenericCrudRESTfulServer<T extends Serializable, K extends Serializable> {

	@POST
	public Response insert(T entity) {
		try {
			this.getService().insert(entity);
			return Response.ok(entity).build();
		} catch (BusinessException businessException) {
			return Response.serverError().entity(businessException.getMessage()).build();
		}
	}

	@PUT
	public Response update(T entity) {
		try {
			this.getService().update(entity);
			return Response.ok(entity).build();
		} catch (BusinessException businessException) {
			return Response.serverError().entity(businessException.getMessage()).build();
		}
	}

	@GET
	public Response getAll() {
		try {
			return Response.ok(this.getService().getAll()).build();
		} catch (BusinessException businessException) {
			return Response.serverError().entity(businessException.getMessage()).build();
		}
	}

	@Path("{id}")
	@GET
	public Response getById(@PathParam("id") K id) {
		try {
			return Response.ok(this.getService().getById(id)).build();
		} catch (BusinessException businessException) {
			return Response.serverError().entity(businessException.getMessage()).build();
		}
	}

	@Path("{id}")
	@DELETE
	public Response delete(@PathParam("id") K id) {
		try {
			this.getService().delete(id);
			return Response.ok().build();
		} catch (BusinessException businessException) {
			return Response.serverError().entity(businessException.getMessage()).build();
		}
	}

	protected abstract GenericCrudService<T, K> getService();

}
