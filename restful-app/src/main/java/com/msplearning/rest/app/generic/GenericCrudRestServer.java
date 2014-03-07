package com.msplearning.rest.app.generic;

import java.io.Serializable;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.msplearning.entity.common.BusinessException;
import com.msplearning.entity.common.Response;
import com.msplearning.entity.common.Status;
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
public abstract class GenericCrudRestServer<T extends Serializable, K extends Serializable> {

	@POST
	public Response insert(T entity) {
		try {
			this.getService().insert(entity);
			return new Response(Status.OK, entity);
		} catch (BusinessException businessException) {
			return new Response(Status.OK, businessException);
		}
	}

	@PUT
	public Response update(T entity) {
		try {
			this.getService().update(entity);
			return new Response(Status.OK, entity);
		} catch (BusinessException businessException) {
			return new Response(Status.OK, businessException);
		}
	}

	@GET
	public Response getAll() {
		try {
			return new Response(Status.OK, this.getService().getAll());
		} catch (BusinessException businessException) {
			return new Response(Status.OK, businessException);
		}
	}

	@Path("{id}")
	@GET
	public Response getById(@PathParam("id") K id) {
		try {
			return new Response(Status.OK, this.getService().getById(id));
		} catch (BusinessException businessException) {
			return new Response(Status.OK, businessException);
		}
	}

	@Path("{id}")
	@DELETE
	public Response delete(@PathParam("id") K id) {
		try {
			this.getService().delete(id);
			return new Response(Status.OK);
		} catch (BusinessException businessException) {
			return new Response(Status.OK, businessException);
		}
	}

	protected abstract GenericCrudService<T, K> getService();

}
